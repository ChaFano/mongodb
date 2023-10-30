package com.chafan.service.impl;

import com.chafan.entity.DbName;
import com.chafan.entity.NodeInformation;
import com.chafan.service.NodeInfoService;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: 茶凡
 * @ClassName NodeInfoService
 * @date 2023/10/30 11:05
 * @Description TODO
 */

@Service
public class NodeInfoServiceImpl implements NodeInfoService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoClient mongoClient;

    /**
     * 查询副本集各个节点的信息
     * 最重要的是 stateSt 字段用于查看是主节点还是从节点
     * 在模仿主节点宕机后 仲裁节点 选出的主节点 就可以查看
     * @return
     */
    @Override
    public List<NodeInformation> getReplicaSetInfo() {

        Document result = mongoTemplate.executeCommand("{ replSetGetStatus: 1 }");

        List<Document> members = (List<Document>) result.get("members");

        List<NodeInformation> nodeInformations = new ArrayList<>();

        members.stream().forEach(e-> {
            nodeInformations.add(new NodeInformation(
                    e.get("_id").toString(),
                    e.get("name").toString(),
                    e.get("health").toString(),
                    e.get("state").toString(),
                    e.get("stateStr").toString()
            ));
        });
        return nodeInformations;

    }

    /**
     * 查询有哪些数据库 主节点跟新后可以查找变化
     * @return
     */
    @Override
    public List<DbName> getAllDatabases() {
        Document result = mongoTemplate.executeCommand("{ listDatabases: 1 }");

        List<Document> databases = (List<Document>) result.get("databases");
        // 返回数据库名的列表
        return databases.stream().map(db -> new DbName(db.getString("name"))).collect(Collectors.toList());
    }



}
