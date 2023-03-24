package org.example.Service.LoginLogout;

import org.example.Exception.LoginException;
import org.example.Model.LoginDetails;
import org.example.Model.UserCurrentSession;

public interface LoginLogoutService {

    public String loginUser(LoginDetails loginDetails) throws LoginException;

    public void logoutUser(String token);

}
