package com.chafan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chafan.entity.Student;
import com.chafan.entity.Student1;
import com.chafan.mapper.StudentMySQL_Mapper;
import com.chafan.service.StudentMySQL_Service;
import com.chafan.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;

/**
 * @Auther: 茶凡
 * @ClassName StudentMySQL_ServiceImpl
 * @date 2023/11/9 22:02
 * @Description TODO
 */
@Service
public class StudentMySQL_ServiceImpl extends ServiceImpl<StudentMySQL_Mapper, Student1> implements StudentMySQL_Service {

    @Resource
    StudentMySQL_Mapper mapper;


    @Override
    public Long batchSave(Long number) {
        return null;
    }

    @Override
    public double getStudents1(int number) {

        Instant start = Instant.now();
        QueryWrapper<Student1> query = new QueryWrapper();
        query.last("LIMIT " + number);
        mapper.selectList(query);
        Instant finish = Instant.now(); // 记录查询结束时间
        Duration timeElapsed = Duration.between(start, finish);
        return timeElapsed.toMillis()/1000.0;

    }


}
