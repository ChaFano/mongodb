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

    /**
     * 查询副本集数据库中的 student 集合的所有数据
     * @return
     */
    @GetMapping("/findStudentAll")
    public R findStudentAll(){
//        return R.ok().setData(studentService.getStudents());
        return R.ok().setData(studentService.getStudents(),
                Long.valueOf(studentService.getStudents().size()));
    }

    /**
     * 根据 id　删除　student　集合数据
     * @param id
     * @return
     */
    @GetMapping("/deleteById/{id}")
    public R deleteById(@PathVariable String id){
        return R.ok().setData(studentService.deleteStudent(id));
    }

    /**
     * 查询指定服务器的节点的数据
     * @param ip
     * @param port
     * @param databaseName
     * @param collectionName
     * @return
     */
    @GetMapping("/queryByIPPortDatabase")
    public R queryByIPPortDatabase(String ip, int port, String databaseName, String collectionName){
        return R.ok().setData(studentService.queryByIPPortDatabase(ip, port, databaseName, collectionName));
    }

    /**
     * 根据数据库名和集合名获取数据
     * @param dbName
     * @param collectionName
     * @return
     */
    @GetMapping("/getDataByDbAndCollection")
    public R getDataByDbAndCollection(String dbName, String collectionName){
        return R.ok().setData(studentService.getDataByDbAndCollection(dbName, collectionName));
    }

    /**
     * 随机生成指定数量的 student 数据
     * @param number
     * @return
     */
    @GetMapping("/insertStudentRandom/{number}")
    public R insertStudentRandom(@PathVariable int number){
        return R.ok().setData(studentService.insertStudentRandom(number));
    }



}
