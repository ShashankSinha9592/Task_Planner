package org.example.Service.User;

import org.example.Exception.UserException;
import org.example.Model.User;
import org.example.Repository.UserRepository;
import org.example.Service.Authentication.AuthenticationService;
import org.example.Service.LoginLogout.LoginLogoutService;
import org.example.Service.UserNamePasswordValidator.UserNameAndPasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserNameAndPasswordValidationService userNameAndPasswordValidationService;

    @Autowired
    LoginLogoutService loginLogoutService;

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public User registerUser(User user) {

        Optional<User> optional = userRepository.findByEmail(user.getEmail());

        if(optional.isPresent()) throw new UserException("User already exists with email : "+user.getEmail());

        if(!userNameAndPasswordValidationService.validateName(user.getFirstName()) && !userNameAndPasswordValidationService.validateName(user.getFirstName())){
            throw new UserException("FirstName or LastName should not contains any digit or symbols");
        }

        if(!userNameAndPasswordValidationService.validatePassword(user.getPassword())) throw new UserException("Password must contain symbol, lowercase, uppercase and digit");

        return userRepository.save(user);

    }

    @Override
    public User removeUser(String token) {

        User user = authenticationService.authenticateUser(token);

        userRepository.delete(user);

        loginLogoutService.logoutUser(token);

        return user;

    }

    @Override
    public User updateUser(User user, String token) {

        User savedUser = authenticationService.authenticateUser(token);

        if(user.getEmail().equals(savedUser.getEmail())){

            if(!userNameAndPasswordValidationService.validateName(user.getFirstName()) && !userNameAndPasswordValidationService.validateName(user.getFirstName())){
                throw new UserException("FirstName or LastName should not contains any digit or symbols");
            }

            if(!userNameAndPasswordValidationService.validatePassword(user.getPassword())) throw new UserException("Password must contain symbol, lowercase, uppercase and digit");

            user.setSprints(savedUser.getSprints());

            return userRepository.save(user);
        }
        else{

            Optional<User> optional = userRepository.findByEmail(user.getEmail());

            if(optional.isPresent()) throw new UserException("User already exists with email : "+user.getEmail());

            if(!userNameAndPasswordValidationService.validateName(user.getFirstName()) && !userNameAndPasswordValidationService.validateName(user.getFirstName())){
                throw new UserException("FirstName or LastName should not contains any digit or symbols");
            }

            if(!userNameAndPasswordValidationService.validatePassword(user.getPassword())) throw new UserException("Password must contain symbol, lowercase, uppercase and digit");

            user.setSprints(savedUser.getSprints());

            return userRepository.save(user);
        }

    }
}
