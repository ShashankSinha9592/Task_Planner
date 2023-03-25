package org.example.Service.Task;

import org.example.DTO.PagingAndSortingDetails;
import org.example.Exception.InvalidTokenException;
import org.example.Exception.SprintException;
import org.example.Exception.TaskException;
import org.example.Model.*;
import org.example.Repository.SprintRepository;
import org.example.Repository.TaskRepository;
import org.example.Service.Authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    AuthenticationService authenticationService;


    @Override
    public Task assignTaskToSprint(Task task, Integer sprintId, String token) throws SprintException {

        User user = authenticationService.authenticateUser(token);

        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new SprintException("Sprint does not exists with sprint id : "+sprintId));

        if(sprint.getUser().getUserId()!=user.getUserId()){
            throw new SprintException("Sprint with id "+sprintId+" does not belong to user with id "+user.getUserId());
        }

        sprint.getTasks().add(task);

        task.setSprint(sprint);

        return taskRepository.save(task);


    }

    public Task changeStatusOfTask(Integer taskId, TaskStatus status, String token){

        User user = authenticationService.authenticateUser(token);

        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskException("Task does not exists with taskId : "+taskId));

        if(task.getSprint().getUser().getUserId()!=user.getUserId()) throw new TaskException("Task does not belong to user with id : "+user.getUserId());

        task.setTaskStatus(status);

        return taskRepository.save(task);

    }

    @Override
    public Task updateTask(Task task, String token) {

        User user = authenticationService.authenticateUser(token);

        Task availableTask = taskRepository.findById(task.getTaskId()).orElseThrow(()-> new TaskException("Task does not exists with task id : "+task.getTaskId()));

        if(task.getSprint().getUser().getUserId()!=user.getUserId()) throw new TaskException("Task does not exists");

        return taskRepository.save(task);

    }

    public List<Task> getTaskBySprintId(Integer sprintId, String token) throws SprintException, InvalidTokenException{

        User user = authenticationService.authenticateUser(token);

        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new SprintException("Sprint does not exists with sprint id : "+sprintId));

        if(sprint.getUser().getUserId()!=user.getUserId()) throw new SprintException("Sprint does not exists");

        List<Task> tasks = sprint.getTasks();

        if(tasks.isEmpty()) throw new TaskException("Tasks list is empty");

        return tasks;

    }


    public List<Task> getAllTask(String token){

        User user = authenticationService.authenticateUser(token);

        List<Task> tasks = new ArrayList<>();

        List<Sprint> sprints = user.getSprints();

        for(Sprint sprint : sprints){

            List<Task> availableTasks = sprint.getTasks();

            availableTasks.stream().forEach(task -> tasks.add(task));

        }

        if(tasks.isEmpty()) throw new TaskException("No Tasks found");

        return tasks;
    }

    @Override
    public List<Task> getAllTasksInSprintByPriority(String token, Integer sprintId, Priority priority) {
        User user = authenticationService.authenticateUser(token);

        List<Task> tasks = taskRepository.getTasksByPriorityAndSprint(priority,sprintId, user.getUserId());

        if(tasks.isEmpty()) throw new TaskException("Task not found");

        return tasks;

    }

    @Override
    public List<Task> getAllTasksByPriority(String token, Priority priority) {

        User user = authenticationService.authenticateUser(token);

        List<Task> tasks = taskRepository.getTasksByPriority(priority, user.getUserId());

        if(tasks.isEmpty()) throw new TaskException("Task not found");

        return tasks;

    }

    @Override
    public List<Task> getTasksByPageNumberAndNumberOfDataAndSortedByFieldInSprintAndByDirection(String token, PagingAndSortingDetails pagingAndSortingDetails) {

        User user = authenticationService.authenticateUser(token);

        String direction = pagingAndSortingDetails.getDirection().toUpperCase();

        Integer countOfData = pagingAndSortingDetails.getSize();

        Integer pageNumber = pagingAndSortingDetails.getPageNumber()-1;

        String fieldName = pagingAndSortingDetails.getField();

        Integer sprintid = pagingAndSortingDetails.getSprintId();

        Pageable pageable = PageRequest.of(pageNumber, countOfData, direction.equals("ASC")? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending());

        Page<Task> taskPage =  taskRepository.getTasksByPageNumberAndNumberOfDataAndSortedByFieldInSprint(user.getUserId(),sprintid,pageable);

        List<Task> tasks = taskPage.getContent();

        if(tasks.isEmpty()) throw new TaskException("No tasks found");

        return tasks;
    }

    @Override
    public List<Task> getTasksByPageNumberAndByDirectionAndNumberOfDataAndSortedByField(String token, PagingAndSortingDetails pagingAndSortingDetails) {

        User user = authenticationService.authenticateUser(token);

        String direction = pagingAndSortingDetails.getDirection().toUpperCase();

        Integer countOfData = pagingAndSortingDetails.getSize();

        Integer pageNumber = pagingAndSortingDetails.getPageNumber()-1;

        String fieldName = pagingAndSortingDetails.getField();

        Pageable pageable = PageRequest.of(pageNumber, countOfData, direction.equals("ASC")? Sort.by(fieldName).ascending() : Sort.by(fieldName).descending());

        Page<Task> taskPage =  taskRepository.getTasksByPageNumberAndNumberOfDataAndSortedByField(user.getUserId(),pageable);

        List<Task> tasks = taskPage.getContent();

        if(tasks.isEmpty()) throw new TaskException("No tasks found");

        return tasks;

    }

    @Override
    public List<Task> getSortedTaskByFieldInSprint(String field,String direction, Integer sprintId, String token) {

        User user = authenticationService.authenticateUser(token);
        direction = direction.toUpperCase();
        Sort sort = direction.equals("ASC")? Sort.by(field).ascending() : Sort.by(field).descending();

        Page<Task> taskPage = taskRepository.getSortedTaskInSprintAndByField(sprintId , user.getUserId(), sort);

        List<Task> sortedTasks = taskPage.getContent();

        if(sortedTasks.isEmpty()) throw new TaskException("Task not found");

        return sortedTasks;

    }

    @Override
    public Task removeTask(Integer taskId, String token) {

        User user = authenticationService.authenticateUser(token);

        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskException("Task does not exists"));

        if(task.getSprint().getUser().getUserId()!=user.getUserId()) throw new TaskException("Task does not exists");

        taskRepository.delete(task);

        return task;

    }
}
