package com.example.javaweb.service;

import com.example.javaweb.Dao.UserDao;
import com.example.javaweb.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    @Autowired
    UserDao userDao;

     @Override
     public List<User> getlist() {
         return (List<User>) userDao.findAll();
     }

    @Override
    public User add(User user){
        return userDao.save(user);
    }
    @Override
    public User getUser(Integer userId){
        return userDao.findById(userId).orElseThrow(()->{
            return new RuntimeException("用户不存在 参数异常！");
        });
    }

    @Override
    public User edit(User user) {
        return userDao.save(user);
    }

    @Override
    public void delete(Integer userId) {
        userDao.deleteById(userId);
    }

    @Override
    public User getUserByUsername(String username){
        return userDao.getUserByUsername(username);

    }

}
