package org.example.Service.Task;

import org.example.Exception.InvalidTokenException;
import org.example.Exception.SprintException;
import org.example.Exception.TaskException;
import org.example.Model.*;
import org.example.Repository.SprintRepository;
import org.example.Repository.TaskRepository;
import org.example.Service.Authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Task removeTask(Integer taskId, String token) {

        User user = authenticationService.authenticateUser(token);

        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskException("Task does not exists"));

        if(task.getSprint().getUser().getUserId()!=user.getUserId()) throw new TaskException("Task does not exists");

        taskRepository.delete(task);

        return task;

    }
}
