package com.example.javaweb.Dao;

import com.example.javaweb.bean.Grade;
import com.example.javaweb.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface GradeDao  extends CrudRepository<Grade,Integer> {

    // 使用JPQL查询
    @Query("SELECT g FROM Grade g WHERE g.studentid = :studentid")
    Optional<Grade> findsid(@Param("studentid") int studentid);
}
