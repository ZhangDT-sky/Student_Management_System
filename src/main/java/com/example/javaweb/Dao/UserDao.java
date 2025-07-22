package com.example.javaweb.Dao;

import com.example.javaweb.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDao extends CrudRepository<User,Integer> {
    // 使用JPQL查询
    @Query("SELECT u FROM User u WHERE u.userName = :userName")
    User getUserByUsername(@Param("userName") String userName);
}
