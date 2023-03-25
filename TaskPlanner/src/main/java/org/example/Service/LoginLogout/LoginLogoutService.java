package org.example.Service.LoginLogout;

import org.example.Exception.LoginException;
import org.example.DTO.LoginDetails;
import org.example.Model.UserCurrentSession;

public interface LoginLogoutService {

    public UserCurrentSession loginUser(LoginDetails loginDetails) throws LoginException;

    public void logoutUser(String token);

}
