package com.chafan.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @Auther: 茶凡
 * @ClassName Student1
 * @date 2023/11/9 22:01
 * @Description TODO
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "student")
public class Student1 {

    private BigInteger _id;
    private String name;
    private int age;
    private String gender;
    private String student_id;
    private String phone_number;
    private String grades;


}
