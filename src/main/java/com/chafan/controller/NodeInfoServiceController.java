package com.chafan.controller;

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

import java.util.List;
import java.util.Set;

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

}
