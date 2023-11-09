package com.chafan.controller;

import com.chafan.entity.DbTree;
import com.chafan.service.NodeInfoService;
import com.chafan.util.R;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: 茶凡
 * @ClassName NodeInfoServiceController
 * @date 2023/10/30 11:16
 * @Description 副本集服务端配置相关的信息
 */

@RequestMapping("api/node")
@RestController
public class NodeInfoServiceController {

    @Autowired
    NodeInfoService nodeInfoService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoClient mongoClient;

    @Value("${connection.url1}")
    public String url1;
    @Value("${connection.url2}")
    public String url2;
    @Value("${connection.url4}")
    public String url4;
    @Value("${connection.url5}")
    public String url5;


    /**
     * 查询副本集有各个节点的信息
     * @return
     */
    @GetMapping("/getReplicaSetInfo")
    public R getReplicaSetInfo(){
        return R.ok().setData(nodeInfoService.getReplicaSetInfo());
    }

    /**
     * 获取所有数据库
     * @return
     */
    @GetMapping("/getAllDatabases")
    public R getAllDatabases(){
        return R.ok().setData(nodeInfoService.getAllDatabases());
    }


    /**
     * 获取所有集合名称
     * @return
     */
    @GetMapping("/getCollectionNames")
    public MongoIterable<String> getCollectionNames(){
        MongoDatabase database = mongoClient.getDatabase("db01");
        return  database.listCollectionNames();
    }

    /**
     * 创建数据库 和 集合 数据库已有的话就在该数据库下创建集合
     * @param databaseName
     * @param collectionName
     * @return
     */
    @PostMapping("/createDbAndCollection")
    public R createDbAndCollection(String databaseName, String collectionName){
        System.out.println(databaseName+ ":" + collectionName);
        return R.ok().setData(nodeInfoService.createDbAndCollection(databaseName, collectionName));
    }

    /**
     * 根据集合名删除 删除的是 admin 数据下的
     * @param collectionName
     * @return
     */
    @PostMapping("/deleteCollection")
    public R deleteCollection(String collectionName){
        return R.ok().setData(nodeInfoService.deleteCollection(collectionName));
    }


    /**
     * 获取每个节点自己创建的数据库
     * @return
     */
    @PostMapping("/getNodeDatabases")
    public R getNodeDatabases(){
        List<String> list = new ArrayList<>();
        list.add(url1);
        list.add(url2);
        list.add(url4);
        list.add(url5);

        List<DbTree> collect = list.stream().map(e -> {
            DbTree tree = new DbTree();
            tree.setTitle("节点：" + e.substring(e.length() - 5));
            tree.setChildren(nodeInfoService.getNodeDatabases());

            return tree;
        }).collect(Collectors.toList());


        return R.ok().setData(collect);
    }

}
