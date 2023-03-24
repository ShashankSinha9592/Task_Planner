package org.example.Controller;

import jakarta.validation.Valid;
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


}
