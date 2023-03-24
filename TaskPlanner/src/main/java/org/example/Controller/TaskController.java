package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.PagingAndSortingDetails;
import org.example.Model.Priority;
import org.example.Model.Task;
import org.example.Model.TaskStatus;
import org.example.Service.Task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskscheduler/task")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/{sprintId}")
    public ResponseEntity<Task> addTaskToSprint(@Valid @RequestBody Task task ,@PathVariable Integer sprintId, @RequestHeader("Token") String token){

        task.setTaskStatus(TaskStatus.PENDING);

        Task registeredTask = taskService.assignTaskToSprint(task, sprintId, token);

        return new ResponseEntity<>(registeredTask, HttpStatus.CREATED);

    }

    @PatchMapping("/{taskId}/{status}")
    public ResponseEntity<Task> changeStatusOfTask(@PathVariable Integer taskId, @PathVariable TaskStatus status, @RequestHeader("Token") String token){

        Task updatedTask = taskService.changeStatusOfTask(taskId,status,token);

        return new ResponseEntity<>(updatedTask, HttpStatus.OK);

    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@Valid @RequestBody Task task, @RequestHeader("Token") String token){

        Task updatedTask = taskService.updateTask(task,token);

        return new ResponseEntity<>(updatedTask, HttpStatus.ACCEPTED);

    }
    @GetMapping("/{sprintId}")
    public ResponseEntity<List<Task>> getTaskBySprintId(@PathVariable Integer sprintId, @RequestHeader("Token") String token){

        List<Task> tasks = taskService.getTaskBySprintId(sprintId,token);

        return new ResponseEntity<>(tasks,HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTask(@RequestHeader("Token") String token){

        List<Task> tasks = taskService.getAllTask(token);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Task> removeTask(@PathVariable Integer taskId, @RequestHeader("Token") String token){

        Task removedTask = taskService.removeTask(taskId,token);

        return new ResponseEntity<>(removedTask,HttpStatus.OK);

    }


    @PostMapping("/pagingandsortedtaskinsprint")
    public ResponseEntity<List<Task>> getTaskInSortedAndByFieldNameAndByDirectionAndByFieldInSprint(@RequestHeader("Token") String token, @RequestBody PagingAndSortingDetails pagingAndSortingDetails){

            List<Task> tasks = taskService.getTasksByPageNumberAndNumberOfDataAndSortedByFieldInSprintAndByDirection(token,pagingAndSortingDetails);

            return new ResponseEntity<>(tasks,HttpStatus.OK);
    }

    @PostMapping("/pagingandsortedtask")
    public ResponseEntity<List<Task>> getTaskInSortedAndByFieldNameAndByDirectionAndByField(@RequestHeader("Token") String token, @RequestBody PagingAndSortingDetails pagingAndSortingDetails){

        List<Task> tasks = taskService.getTasksByPageNumberAndByDirectionAndNumberOfDataAndSortedByField(token,pagingAndSortingDetails);

        return new ResponseEntity<>(tasks,HttpStatus.OK);
    }


    @GetMapping("/tasksinsprintbypriority/{sprintId}/{priority}")
    public ResponseEntity<List<Task>> getAllTasksInSprintByPriority(@RequestHeader("Token") String token, @PathVariable Integer sprintId, @PathVariable Priority priority){

        List<Task> tasks = taskService.getAllTasksInSprintByPriority(token, sprintId, priority);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

    @GetMapping("/tasksbypriority/{priority}")
    public ResponseEntity<List<Task>> getAllTasksByPriority(@RequestHeader("Token") String token, @PathVariable Priority priority){

        List<Task> tasks = taskService.getAllTasksByPriority(token, priority);

        return new ResponseEntity<>(tasks, HttpStatus.OK);

    }

}
