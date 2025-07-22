package com.example.javaweb.service;

import com.example.javaweb.Dao.GradeDao;
import com.example.javaweb.bean.Grade;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService implements IGradeService {
    @Autowired
    GradeDao gradeDao;

    @Override
    public List<Grade> getlist(){
        return (List<Grade>) gradeDao.findAll();
    }

    @Override
    public Grade getgrade(Integer id){
        return gradeDao.findById(id).orElseThrow(()->{
            return new RuntimeException("成绩不存在 参数异常！");
        });
    }

    @Override
    public Grade add(Grade grade){
        return gradeDao.save(grade);
    }

    @Override
    public Grade edit(Grade grade){
        return gradeDao.save(grade);
    }

    @Override
    public void delete(Integer id){
        gradeDao.deleteById(id);
    }
}
