package com.chafan.controller;

import com.chafan.service.StudentService;
import com.chafan.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 茶凡
 * @ClassName StudentController
 * @date 2023/10/30 16:50
 * @Description TODO
 */

@RequestMapping("/api/student")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


    @GetMapping("/findStudentAll")
    public R findStudentAll(){
        return R.ok().setData(studentService.getStudents());
    }

    @GetMapping("/deleteById/{id}")
    public R deleteById(@PathVariable String id){
        System.out.println(id);
        return R.ok().setData(studentService.deleteStudent(id));
    }


    @GetMapping("/queryByIPPortDatabase")
    public R queryByIPPortDatabase(String ip, int port, String databaseName, String collectionName){
        return R.ok().setData(studentService.queryByIPPortDatabase(ip, port, databaseName, collectionName));
    }

    @GetMapping("/getDataByDbAndCollection")
    public R getDataByDbAndCollection(String dbName, String collectionName){
        return R.ok().setData(studentService.getDataByDbAndCollection(dbName, collectionName));
    }


    @GetMapping("/insertStudentRandom/{number}")
    public R insertStudentRandom(@PathVariable int number){
        return R.ok().setData(studentService.insertStudentRandom(number));
    }



}
