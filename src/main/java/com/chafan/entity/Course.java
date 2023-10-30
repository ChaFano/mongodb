package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName Course
 * @date 2023/10/30 16:25
 * @Description TODO
 */
public class Course {

    private String subject;
    private String score;

    public Course(String subject, String score) {
        this.subject = subject;
        this.score = score;
    }

    public Course() {}


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "subject='" + subject + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
