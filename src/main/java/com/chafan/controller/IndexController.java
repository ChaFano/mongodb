package com.chafan.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chafan.entity.Book;
import com.chafan.entity.NodeInfo;
import com.chafan.util.RandomBookTitleGenerator;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.connection.ServerDescription;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoClientFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    RandomBookTitleGenerator titleGenerator;



    @GetMapping("/getOneBook")
    public Book getOneBook(){

        Query query = new Query(Criteria.where("name").is("js教程"));
        Book book = mongoTemplate.findOne(query,Book.class);
        return book;
    }

    @GetMapping("/getBookList")
    public List<Book> getBookList(){
        List<Book> bookList = mongoTemplate.findAll(Book.class,"book");
        return bookList;
    }

    @GetMapping("/insertBook")
    public boolean insertBook(){
        for (int i = 0; i <100 ; i++) {
            Book book = new Book(titleGenerator.generateRandomTitle());
            mongoTemplate.insert(book);
        }
        return true;
    }


    @GetMapping("/getReplicaSetInfo")
    public String getReplicaSetInfo() {
        Document result = mongoTemplate.executeCommand("{ replSetGetStatus: 1 }");
        List<Document> members = (List<Document>) result.get("members");
        List<NodeInfo> nodeInfos = new ArrayList<>();
        members.stream().forEach(e-> {
            nodeInfos.add(new NodeInfo(
                    e.get("_id").toString(),
                    e.get("name").toString(),
                    e.get("health").toString(),
                    e.get("state").toString(),
                    e.get("stateStr").toString()
            ));
        });

        return nodeInfos.toString();
    }



    @GetMapping("/getAllDatabases")
    public List<String> getAllDatabases() {

        Document result = mongoTemplate.executeCommand("{ listDatabases: 1 }");
        List<Document> databases = (List<Document>) result.get("databases");
        // 返回数据库名的列表
        return databases.stream().map(db -> db.getString("name")).collect(Collectors.toList());
    }


    @Autowired
    private MongoClient mongoClient; // 自动注入MongoClient

    /*
    * 动态切换数据库查询数据库中的数据
    * */
    @GetMapping("/performOperationInDatabase")
    public List<Book> performOperationInDatabase(String databaseName, String collectionName) {

        MongoTemplate dynamicMongoTemplate = new MongoTemplate(mongoClient, "book");
        // 使用动态MongoTemplate执行操作
        List<Book> results = dynamicMongoTemplate.findAll(Book.class, "book");

        // 返回结果
        return results;
    }


}
