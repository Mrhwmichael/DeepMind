package com.example.deepmind.data;

import com.example.deepmind.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            if(username.equals("bupt") && password.equals("888888")){
                LoggedInUser adminUser =
                        new LoggedInUser(
                                java.util.UUID.randomUUID().toString(),
                                "Admin");

                return new Result.Success<>(adminUser);
            }
            else{
                return new Result.Error(new IOException("Error logging in"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
