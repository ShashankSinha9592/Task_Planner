package org.example.Controller;

import jakarta.validation.Valid;
import org.example.Model.User;
import org.example.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskscheduler/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user){

        User registeredUser = userService.registerUser(user);

        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @RequestHeader("Token") String token){

        User updatedUser = userService.updateUser(user, token);

        return new ResponseEntity<>(updatedUser, HttpStatus.ACCEPTED);

    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser( @RequestHeader("Token") String token){

        User removedUser = userService.removeUser(token);

        return new ResponseEntity<>(removedUser, HttpStatus.ACCEPTED);

    }


}
