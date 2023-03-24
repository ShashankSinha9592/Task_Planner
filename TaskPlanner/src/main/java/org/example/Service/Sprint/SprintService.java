package org.example.Service.Sprint;

import org.example.Exception.SprintException;
import org.example.Model.Sprint;

public interface SprintService {

    public Sprint registerSprint(Sprint sprint, String token) throws SprintException;

    public Sprint removeSprint(Integer sprintId, String token);

}
