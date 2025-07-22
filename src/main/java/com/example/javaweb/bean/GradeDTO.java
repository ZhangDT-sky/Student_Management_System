package com.example.javaweb.bean;

public class GradeDTO {
    private int gradeId;
    private String studentName;
    private String subject;
    private int score;
    private String examDate;

    public int getGradeId() {
        return gradeId;
    }
    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getExamDate() {
        return examDate;
    }
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
} 