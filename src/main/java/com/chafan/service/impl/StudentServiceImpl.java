package com.chafan.service.impl;

import com.chafan.entity.Node;
import com.chafan.entity.Student;
import com.chafan.service.StudentService;
import com.chafan.util.RandomStudent;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

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

    @Autowired
    RandomStudent randomStudent;

    private static final int THREAD_COUNT = 5; // 假设使用 5 个线程
    ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);


    /**
     * 动态切换数据库 查询数据库中的数据
     * 用于在 查询非 admin 数据库的其他信息
     */
    @Override
    public List<Student> getStudents() {

        MongoTemplate template = new MongoTemplate(mongoClient, "db01");
        // 使用动态MongoTemplate执行操作
        List<Student> results = template.findAll(Student.class, "student");

        return results;
    }

    @Override
    public CompletableFuture<List<Student>> getStudentsAsync() {
        return CompletableFuture.supplyAsync(() -> {
            MongoTemplate template = new MongoTemplate(mongoClient, "db01");
            return template.findAll(Student.class, "student");
        });
    }

    /**
     * 根据id 删除数据
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteStudent(String id) {

        MongoTemplate MongoTemplateDel = new MongoTemplate(mongoClient, "db01");
        Query query = new Query(Criteria.where("_id").is(id));
        DeleteResult result = MongoTemplateDel.remove(query, "student");

        return result.getDeletedCount() > 0 ? true : false;
    }

    /**
     * 根据 ip port database collectionName 查询指定节点是数据
     *
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
        return template.findAll(Object.class, collectionName);
    }

    /**
     * 随机插入文档数据 到指定数据库
     *
     * @param number
     * @return
     */
    @Override
    public String insertStudentRandom(int number) {

        Instant start = Instant.now(); // 记录查询开始时间
        MongoTemplate template = new MongoTemplate(mongoClient, "db01");
        for (int i = 0; i < number; i++) {
            Student student = randomStudent.generateStudent();
            template.insert(student, "student");
        }
        Instant finish = Instant.now(); // 记录查询结束时间
        Duration timeElapsed = Duration.between(start, finish); // 计算时间差
        return timeElapsed.toString();
    }


    /**
     * 批量插入数据
     *
     * @return
     */
    @Override
    public Long batchSave(Long number) {

        Instant start = Instant.now(); // 记录查询开始时间
        int Count = 10; // 分10个批次插入

        MongoTemplate template = new MongoTemplate(mongoClient, "db01");
        List<Student> studentList = randomStudent.generateStudent(number);

        int chunkSize = studentList.size() / Count; // 计算每个线程处理的数据量

        for (int i = 0; i < Count; i++) {

            int start1 = i * chunkSize;
            int end = (i == Count - 1) ? studentList.size() : (i + 1) * chunkSize;
            List<Student> sublist = studentList.subList(start1, end);  // 每次截取 number/Count 条插入
            template.insert(sublist, Student.class); // 不加多线程 32秒 加多线程 0.42s

        }
        Instant finish = Instant.now(); // 记录查询结束时间
        Duration timeElapsed = Duration.between(start, finish);

        return timeElapsed.getSeconds();

    }



    /**
     * 根据数据库和集合名查询数据
     *
     * @param dbName
     * @param collectionName
     * @return
     */
    @Override
    public List<Object> getDataByDbAndCollection(String dbName, String collectionName) {
        MongoTemplate template = new MongoTemplate(mongoClient, dbName);
        return template.findAll(Object.class, collectionName);
    }



    @Value("${connection.url1}")
    public String url1;
    @Value("${connection.url2}")
    public String url2;
    @Value("${connection.url3}")
    public String url3;
    @Value("${connection.url4}")
    public String url4;
    @Value("${connection.url5}")
    public String url5;
    @Value("${connection.url6}")
    public String url6;



    @Override
    public List<Node> getCount() {

        List<String> list = new ArrayList<>();
        list.add(url1);
        list.add(url2);
        list.add(url3);
        list.add(url4);
        list.add(url5);
        list.add(url6);

       List<Node> collect = list.stream().map(e -> {
            Node node = new Node();
            MongoClient mongoClient = MongoClients.create(url1);
            MongoTemplate template = new MongoTemplate(mongoClient, "db01");
            long number = template.getCollection("student").countDocuments();
            node.setName(e);
            node.setCount(number);
            return node;
        }).collect(Collectors.toList());

        return collect;
    }



}
