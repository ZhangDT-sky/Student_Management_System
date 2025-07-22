package com.example.javaweb.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.annotation.EnableMBeanExport;

import java.time.LocalDateTime;

@Table(name = "grade")
@Entity
@DynamicUpdate
public class Grade {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "gradeid")
    private int id;

    @Column(name = "coursename")
    private String coursename;

    @Column(name = "studentid")
    private int studentid;

    @Column(name = "studentgrade")
    private int studentgrade;

    @Column(name = "creater_time", updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    @PrePersist
    protected void onCreate(){
        createTime = LocalDateTime.now();
        updateTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getStudentid() {
        return studentid;
    }

    public void setStudentid(int studentid) {
        this.studentid = studentid;
    }

    public int getStudentgrade() {
        return studentgrade;
    }

    public void setStudentgrade(int studentgrade) {
        this.studentgrade = studentgrade;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", coursename='" + coursename + '\'' +
                ", studentid=" + studentid +
                ", studentgrade=" + studentgrade +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}