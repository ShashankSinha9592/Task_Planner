package org.example.Service.Task;

import org.example.Exception.InvalidTokenException;
import org.example.Exception.SprintException;
import org.example.Model.Task;
import org.example.Model.TaskStatus;

import java.util.List;

public interface TaskService {

    public Task assignTaskToSprint(Task task, Integer sprintId, String token) throws SprintException;

    public Task changeStatusOfTask(Integer taskId, TaskStatus status, String token);

    public Task updateTask(Task task, String token);

    public List<Task> getTaskBySprintId(Integer sprintId, String token) throws SprintException, InvalidTokenException;

    public List<Task> getAllTask(String token);

    public Task removeTask(Integer taskId, String token);

}
