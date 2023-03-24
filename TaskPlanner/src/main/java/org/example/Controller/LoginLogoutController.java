package org.example.Controller;

import jakarta.validation.Valid;
import org.example.DTO.LoginDetails;
import org.example.Service.LoginLogout.LoginLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskscheduler")
public class LoginLogoutController {

    @Autowired
    LoginLogoutService loginLogoutService;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody LoginDetails loginDetails){

        String token = loginLogoutService.loginUser(loginDetails);

        return new ResponseEntity<>(token, HttpStatus.ACCEPTED);

    }
    @GetMapping("/logout")
    public void logoutUser(@RequestHeader("Token") String token){

        loginLogoutService.logoutUser(token);

    }


}
