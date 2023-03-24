package org.example.Service.Sprint;

import org.example.Exception.SprintException;
import org.example.Model.Sprint;

import java.util.List;

public interface SprintService {

    public Sprint registerSprint(Sprint sprint, String token) throws SprintException;

    public Sprint removeSprint(Integer sprintId, String token);

    public List<Sprint> getAllSprint(String token);

    public List<Sprint> getSprintByPageNumberAndCount(String token, Integer pageNumber, Integer size);

    public Sprint getSprintById(Integer sprintId, String token);

}
