package com.chafan.entity;

import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName Student
 * @date 2023/10/30 16:24
 * @Description TODO
 */

public class Student {

    private String _id;
    private String name;
    private int age;
    private String gender;
    private String phone_number;
    private List<Course> grades;

    public Student(String _id, String name, int age, String gender, String phone_number, List<Course> grades) {
        this._id = _id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone_number = phone_number;
        this.grades = grades;
    }

    public Student() {}


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<Course> getGrades() {
        return grades;
    }

    public void setGrades(List<Course> grades) {
        this.grades = grades;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", grades=" + grades +
                '}';
    }
}
