package com.chafan.controller;

import com.chafan.entity.Book;
import com.chafan.entity.NodeInformation;
import com.chafan.util.RandomBookTitleGenerator;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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

    @Autowired
    RandomBookTitleGenerator titleGenerator;


    /*
    *
    * 获取集合的单挑数据
    * */
    @GetMapping("/getOneBook")
    public Book getOneBook(){

        Query query = new Query(Criteria.where("name").is("js教程"));
        Book book = mongoTemplate.findOne(query,Book.class);
        return book;
    }

    /*
    * 获取集合的所有数据
    * */
    @GetMapping("/getBookList")
    public List<Book> getBookList(){
        List<Book> bookList = mongoTemplate.findAll(Book.class,"book");
        return bookList;
    }

    /*
    * 插入数据
    * */
    @GetMapping("/insertBook")
    public boolean insertBook(){
        for (int i = 0; i <100 ; i++) {
            Book book = new Book(titleGenerator.generateRandomTitle());
            mongoTemplate.insert(book);
        }
        return true;
    }


    @Autowired
    private MongoClient mongoClient; // 自动注入MongoClient

    /*
    * 动态切换数据库 查询数据库中的数据
    * 用于在 查询非 admin 数据库的其他信息
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
