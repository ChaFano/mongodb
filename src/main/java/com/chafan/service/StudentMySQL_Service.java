package com.chafan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chafan.entity.Student;
import com.chafan.entity.Student1;

/**
 * @Auther: 茶凡
 * @ClassName StudentMySQL_Service
 * @date 2023/11/9 22:00
 * @Description TODO
 */
public interface StudentMySQL_Service extends IService<Student1> {

    Long batchSave(Long number);

    double getStudents1(int number);
}
