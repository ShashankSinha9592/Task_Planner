package org.example.Service.Sprint;

import org.example.Exception.SprintException;
import org.example.Model.Sprint;
import org.example.Model.User;
import org.example.Repository.SprintRepository;
import org.example.Service.Authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintServiceImpl implements SprintService{

    @Autowired
    SprintRepository sprintRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Override
    public Sprint registerSprint(Sprint sprint, String token) throws SprintException {

        User user = authenticationService.authenticateUser(token);

        user.getSprints().add(sprint);

        sprint.setUser(user);

        return sprintRepository.save(sprint);

    }

    @Override
    public Sprint removeSprint(Integer sprintId, String token) {

        User user = authenticationService.authenticateUser(token);

        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new SprintException("Sprint does not exists with sprint id : "+sprintId));

        if(sprint.getUser().getUserId()!=user.getUserId()) throw new SprintException("Sprint does not exists");

        sprintRepository.delete(sprint);

        return sprint;

    }

    @Override
    public List<Sprint> getAllSprint(String token) {

        User user = authenticationService.authenticateUser(token);

        List<Sprint> sprints = user.getSprints();

        if(sprints.isEmpty()) throw new SprintException("sprint list is empty");

        return sprints;

    }

    @Override
    public List<Sprint> getSprintByPageNumberAndCount(String token, Integer pageNumber, Integer size) {

        User user = authenticationService.authenticateUser(token);

        Pageable pageable = PageRequest.of(pageNumber-1, size);

        Page<Sprint> sprintPage = sprintRepository.getSprintByPageAndSize(user.getUserId(), pageable);

        List<Sprint> sprints = sprintPage.getContent();

        if(sprints.isEmpty()) throw new SprintException("Sprints not found");

        return sprints;

    }

    @Override
    public Sprint getSprintById(Integer sprintId, String token) {

        User user = authenticationService.authenticateUser(token);

        Sprint sprint = sprintRepository.findById(sprintId).orElseThrow(()-> new SprintException("Sprint does not exists with sprint id : "+sprintId));

        if(sprint.getUser().getUserId()!=user.getUserId()){
            throw new SprintException("Sprint does not exists");
        }

        return sprint;

    }


}
