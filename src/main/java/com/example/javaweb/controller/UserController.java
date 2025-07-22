package com.example.javaweb.controller;

import com.example.javaweb.bean.ResponseMessage;
import com.example.javaweb.bean.User;
import com.example.javaweb.service.IUserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;


    @GetMapping("/userlist")
    public  ResponseMessage<List<User>> getlist(){
        List<User> list=userService.getlist();
        return ResponseMessage.success(list);
    }

    @PostMapping
    public ResponseMessage<User> add(@Validated @RequestBody User user){
        User userNew=userService.add(user);
        return ResponseMessage.success(userNew);
    }

    @GetMapping("/{userId}")
    public ResponseMessage get(@PathVariable Integer userId){
        User user=userService.getUser(userId);
        return ResponseMessage.success(user);
    }

    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody User user){
        User userNew=userService.edit(user);
        return ResponseMessage.success(userNew);
    }

    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable Integer userId){
        userService.delete(userId);
        return ResponseMessage.success();
    }


}
