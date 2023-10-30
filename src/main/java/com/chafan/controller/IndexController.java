package com.chafan.controller;

import com.chafan.entity.Book;
import com.chafan.entity.NodeInformation;
import com.chafan.entity.Student;
import com.chafan.util.RandomBookTitleGenerator;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Auther: 茶凡
 * @ClassName IndexController
 * @date 2023/10/29 22:08
 * @Description TODO
 */


@RequestMapping("/index")
@RestController
public class IndexController {

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/getCollectionNames")
    public List<Student> getCollectionNames() {

        MongoClient mongoClient = MongoClients.create("mongodb://" + "203.33.207.171" + ":" + "28011");

        MongoTemplate template = new MongoTemplate(mongoClient, "db01");

        return template.findAll(Student.class,"student");
    }






}
