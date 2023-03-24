package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.Sprint;
import org.example.Service.Sprint.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskscheduler/sprint")
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

}
