package org.example.Service.LoginLogout;

import org.example.Exception.LoginException;
import org.example.DTO.LoginDetails;

public interface LoginLogoutService {

    public String loginUser(LoginDetails loginDetails) throws LoginException;

    public void logoutUser(String token);

}
