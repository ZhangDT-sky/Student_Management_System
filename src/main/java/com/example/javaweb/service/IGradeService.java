package com.example.javaweb.service;

import com.example.javaweb.bean.Grade;

import java.util.List;

public interface IGradeService {
    List<Grade> getlist();

    Grade getgrade(Integer studentid);

    Grade edit(Grade grade);

    void delete(Integer studentid);

    Grade add(Grade grade);
}
