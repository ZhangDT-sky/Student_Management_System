package com.example.javaweb.controller;

import com.example.javaweb.bean.Grade;
import com.example.javaweb.bean.GradeDTO;
import com.example.javaweb.bean.ResponseMessage;
import com.example.javaweb.bean.User;
import com.example.javaweb.service.IGradeService;
import com.example.javaweb.service.IUserService;
import com.example.javaweb.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.awt.color.ICC_ColorSpace;
import java.rmi.server.RemoteRef;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("grade")
public class GradeController {
    @Autowired
    IGradeService gradeService;

    @Autowired
    IUserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @GetMapping("/gradelist")
    public ResponseMessage<List<GradeDTO>> getGradeList(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        List<Grade> grades;
        if (user.getRole() == 0) { // 学生
            grades = gradeService.getlist().stream()
                .filter(g -> g.getStudentid() == user.getUserId())
                .toList();
        } else { // 老师和管理员都能看
            grades = gradeService.getlist();
        }
        List<GradeDTO> dtos = new ArrayList<>();
        for (Grade grade : grades) {
            GradeDTO dto = new GradeDTO();
            dto.setGradeId(grade.getId());
            User stu = null;
            try {
                stu = userService.getUser(grade.getStudentid());
            } catch (Exception e) {
                // 用户不存在，stu为null
            }
            dto.setStudentName(stu != null ? stu.getUserName() : "未知");
            dto.setSubject(grade.getCoursename());
            dto.setScore(grade.getStudentgrade());
            dto.setExamDate(grade.getCreateTime() != null ? grade.getCreateTime().toString() : "");
            dtos.add(dto);
        }
        return ResponseMessage.success(dtos);
    }

    @GetMapping("/{gradeId}")
    public ResponseMessage get(@PathVariable Integer gradeId){
        Grade grade=gradeService.getgrade(gradeId);
        return ResponseMessage.success(grade);
    }

    @PostMapping
    public ResponseMessage<Grade> add(HttpServletRequest request, @Validated @RequestBody Grade grade){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        // 只有老师能添加成绩
        if (user.getRole() != 1) {
            return ResponseMessage.fail("只有老师有权添加成绩");
        }
        // 校验studentid
        User stu = null;
        try {
            stu = userService.getUser(grade.getStudentid());
        } catch (Exception e) {
            return ResponseMessage.fail("成绩对应的学生不存在，无法添加！");
        }
        Grade gradeNew=gradeService.add(grade);
        return ResponseMessage.success(gradeNew);
    }

    @PutMapping
    public ResponseMessage edit(HttpServletRequest request, @Validated @RequestBody Grade grade){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        // 只有老师能修改成绩
        if (user.getRole() != 1) {
            return ResponseMessage.fail("只有老师有权修改成绩");
        }
        // 校验studentid
        User stu = null;
        try {
            stu = userService.getUser(grade.getStudentid());
        } catch (Exception e) {
            return ResponseMessage.fail("成绩对应的学生不存在，无法修改！");
        }
        Grade gradeNew=gradeService.edit(grade);
        return ResponseMessage.success(gradeNew);
    }

    @DeleteMapping("/{gradeId}")
    public ResponseMessage delete(HttpServletRequest request, @PathVariable Integer gradeId){
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        User user = userService.getUserByUsername(username);
        // 只有老师能删除成绩
        if (user.getRole() != 1) {
            return ResponseMessage.fail("只有老师有权删除成绩");
        }
        // 校验成绩id
        Grade grade = null;
        try {
            grade = gradeService.getgrade(gradeId);
        } catch (Exception e) {
            return ResponseMessage.fail("成绩不存在，无法删除！");
        }
        // 校验studentid
        User stu = null;
        try {
            stu = userService.getUser(grade.getStudentid());
        } catch (Exception e) {
            return ResponseMessage.fail("成绩对应的学生不存在，无法删除！");
        }
        gradeService.delete(gradeId);
        return ResponseMessage.success();
    }
}
