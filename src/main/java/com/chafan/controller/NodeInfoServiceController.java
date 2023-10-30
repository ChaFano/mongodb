package com.chafan.controller;

import com.chafan.service.NodeInfoService;
import com.chafan.util.R;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
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


    @GetMapping("/getCollectionNames")
    public MongoIterable<String> getCollectionNames(){
        MongoDatabase database = mongoClient.getDatabase("db01");
        return  database.listCollectionNames();
    }


}
