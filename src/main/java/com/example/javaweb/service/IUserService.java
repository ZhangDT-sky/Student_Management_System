package com.example.javaweb.service;

import com.example.javaweb.bean.User;

import java.util.List;

public interface IUserService {

    User getUser(Integer userId);

    User edit(User user);

    void delete(Integer userId);

    List<User> getlist();

    User add(User user);

    User getUserByUsername(String userName);
}
