package org.example.Service.LoginLogout;

import org.example.Exception.LoginException;
import org.example.Exception.UserException;
import org.example.DTO.LoginDetails;
import org.example.Model.User;
import org.example.Model.UserCurrentSession;
import org.example.Repository.UserCurrentSessionRepository;
import org.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class LoginLogoutServiceImpl implements LoginLogoutService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCurrentSessionRepository userCurrentSessionRepository;

    public UserCurrentSession loginUser(LoginDetails loginDetails) throws LoginException{

        User user = userRepository.findByEmail(loginDetails.getEmail()).orElseThrow(()-> new UserException("Invalid email"));

        if(!user.getPassword().equals(loginDetails.getPassword())){
            throw new LoginException("Invalid Password");
        }

        return generateSession(user);

    }

    @Override
    public void logoutUser(String token) {

        UserCurrentSession userCurrentSession = userCurrentSessionRepository.findByToken(token).orElseThrow(()-> new LoginException("Invalid token"));

        userCurrentSessionRepository.delete(userCurrentSession);

    }

    private UserCurrentSession generateSession(User user){

        UserCurrentSession userCurrentSession = new UserCurrentSession();

        String token = UUID.randomUUID().toString().replaceAll("-","");

        userCurrentSession.setUserSessionId(user.getUserId());

        userCurrentSession.setToken(token);

        userCurrentSession.setTimeSpan(LocalDateTime.now());

        return userCurrentSessionRepository.save(userCurrentSession);

    }

}
