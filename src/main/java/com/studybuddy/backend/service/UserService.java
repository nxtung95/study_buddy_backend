package com.studybuddy.backend.service;

import com.studybuddy.backend.entity.User;

public interface UserService {
    User login(String username, String password);

    boolean register(User user);

    boolean checkExistByEmail(String mail);
}
