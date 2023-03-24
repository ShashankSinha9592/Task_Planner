package org.example.Service.Authentication;

import org.example.Exception.InvalidTokenException;
import org.example.Exception.UserException;
import org.example.Model.User;
import org.example.Model.UserCurrentSession;
import org.example.Repository.UserCurrentSessionRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    @Autowired
    UserCurrentSessionRepository userCurrentSessionRepository;

    @Autowired
    UserRepository userRepository;
    @Override
    public User authenticateUser(String token) throws InvalidTokenException {

        UserCurrentSession userCurrentSession = userCurrentSessionRepository.findByToken(token).orElseThrow(()-> new InvalidTokenException("Invalid Token"));

        return userRepository.findById(userCurrentSession.getUserSessionId()).orElseThrow(()-> new UserException("User does not exists"));

    }
}
