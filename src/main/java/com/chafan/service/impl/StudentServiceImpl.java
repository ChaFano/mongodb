package com.chafan.service.impl;

import com.chafan.entity.Student;
import com.chafan.service.StudentService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName StudentServiceImpl
 * @date 2023/10/30 16:36
 * @Description TODO
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * 动态切换数据库 查询数据库中的数据
     * 用于在 查询非 admin 数据库的其他信息
     *
     */
    @Override
    public List<Student> getStudents() {

        MongoTemplate template = new MongoTemplate(mongoClient, "db01");
        // 使用动态MongoTemplate执行操作
        List<Student> results = template.findAll(Student.class, "student");

        return results;
    }




    /**
     * 根据id 删除数据
     * @param id
     * @return
     */
    @Override
    public boolean deleteStudent(String id) {

        MongoTemplate MongoTemplateDel = new MongoTemplate(mongoClient, "db01");
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = MongoTemplateDel.remove(query, "student");

        return result.getDeletedCount()>0 ? true:false;
    }

    /**
     * 根据 ip port database collectionName 查询指定节点是数据
     * @param ip
     * @param port
     * @param databaseName
     * @param collectionName
     * @return
     */
    @Override
    public List<Object> queryByIPPortDatabase(String ip, int port, String databaseName, String collectionName) {
        MongoClient mongoClient = MongoClients.create("mongodb://" + ip + ":" + port);
        MongoTemplate template = new MongoTemplate(mongoClient, databaseName);
        return template.findAll(Object.class,collectionName);
    }

    /**
     * 根据数据库和集合名查询数据
     * @param dbName
     * @param collectionName
     * @return
     */
    @Override
    public List<Object> getDataByDbAndCollection(String dbName, String collectionName) {
        MongoTemplate template = new MongoTemplate(mongoClient,dbName);
        return template.findAll(Object.class, collectionName);
    }


}
