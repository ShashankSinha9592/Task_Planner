package org.example.Service.User;

import org.example.Model.User;

public interface UserService {

    public User registerUser(User user);

    public User removeUser(String token);

    public User updateUser(User user, String tiken);

}
