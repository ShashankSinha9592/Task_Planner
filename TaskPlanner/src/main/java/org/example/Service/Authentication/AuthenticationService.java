package org.example.Service.Authentication;

import org.example.Exception.InvalidTokenException;
import org.example.Model.User;
import org.example.Model.UserCurrentSession;

public interface AuthenticationService {

    public User authenticateUser(String token) throws InvalidTokenException;

}
