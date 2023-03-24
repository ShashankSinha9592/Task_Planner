package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Sprint;
import org.example.Service.Sprint.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taskscheduler/sprint")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SprintController {

    @Autowired
    SprintService sprintService;

    @PostMapping
    public ResponseEntity<Sprint> registerSprint(@Valid @RequestBody Sprint sprint, @RequestHeader("Token") String token){

        Sprint registeredSprint = sprintService.registerSprint(sprint,token);

        return new ResponseEntity<>(registeredSprint, HttpStatus.CREATED);

    }

    @DeleteMapping("/{sprintId}")
    public ResponseEntity<Sprint> removeSprint(@PathVariable Integer sprintId,  @RequestHeader("Token") String token){

        Sprint removedSprint = sprintService.removeSprint(sprintId,token);

        return new ResponseEntity<>(removedSprint,HttpStatus.OK);

    }

    @GetMapping("/{pageNumber}/{size}")
    public ResponseEntity<List<Sprint>> getSprintByPageNumberAndCount(@RequestHeader("Token") String token, @PathVariable Integer pageNumber, @PathVariable Integer size){

        List<Sprint> sprints = sprintService.getSprintByPageNumberAndCount(token,pageNumber,size);

        return new ResponseEntity<>(sprints, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Sprint>> getAllSprint(@RequestHeader("Token") String token){

        List<Sprint> sprints = sprintService.getAllSprint(token);

        return new ResponseEntity<>(sprints, HttpStatus.OK);

    }

    @GetMapping("/{sprintId}")
    public ResponseEntity<Sprint> getSrintById(@RequestHeader("Token") String token, @PathVariable Integer sprintId){

        Sprint sprint = sprintService.getSprintById(sprintId,token);

        return new ResponseEntity<>(sprint, HttpStatus.OK);

    }


}
