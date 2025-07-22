package com.example.javaweb.controller;

import com.example.javaweb.bean.ResponseMessage;
import com.example.javaweb.bean.User;
import com.example.javaweb.service.IUserService;
import com.example.javaweb.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseMessage<Map<String, String>> login(@RequestBody User user) {
        User userNew = userService.getUserByUsername(user.getUserName());
        if (userNew == null || !userNew.getPassword().equals(user.getPassword())) {
            return ResponseMessage.fail("用户名或密码错误");
        }

        String token = jwtTokenUtil.generateToken(userNew.getUserName());

        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        result.put("username", userNew.getUserName());

        return ResponseMessage.success(result);
    }
}