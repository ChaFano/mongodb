package com.chafan.service.impl;

import com.chafan.entity.DbTree;
import com.chafan.entity.NodeInformation;
import com.chafan.service.NodeInfoService;
import com.mongodb.client.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${connection.url1}")
    public String url1;


    /**
     * 查询副本集各个节点的信息
     * 最重要的是 stateSt 字段用于查看是主节点还是从节点
     * 在模仿主节点宕机后 仲裁节点 选出的主节点 就可以查看
     *
     * @return
     */
    @Override
    public List<NodeInformation> getReplicaSetInfo() {

        Document result = mongoTemplate.executeCommand("{ replSetGetStatus: 1 }");

        List<Document> members = (List<Document>) result.get("members");

        List<NodeInformation> nodeInformations = new ArrayList<>();

        members.stream().forEach(e -> {
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
     *
     * @return
     */
    @Override
    public List<DbTree> getAllDatabases() {
        Document result = mongoTemplate.executeCommand("{ listDatabases: 1 }");

        List<Document> databases = (List<Document>) result.get("databases");
        // 返回数据库名的列表
        return databases.stream().map(db -> {

            DbTree tree = new DbTree();
            MongoDatabase database = mongoClient.getDatabase(db.getString("name"));
            tree.setTitle(db.getString("name"));

            MongoIterable<String> collections = database.listCollectionNames();

            List<DbTree> list = new ArrayList<>();
            for (String collection : collections) {
                DbTree dbTree = new DbTree();
                dbTree.setTitle(collection);
                list.add(dbTree);
            }
            tree.setChildren(list);
            return tree;
        }).collect(Collectors.toList());
    }


    /**
     * 获取每个节点自己创建的数据库
     * @return
     */
    @Override
    public List<DbTree> getNodeDatabases() {

        Document result = mongoTemplate.executeCommand("{ listDatabases: 1 }");
        List<Document> databases = (List<Document>) result.get("databases");

        // 返回数据库名的列表
        return databases.stream()
                .filter(db -> !"admin".equals(db.getString("name")))
                .filter(db -> !"local".equals(db.getString("name")))
                .filter(db -> !"config".equals(db.getString("name")))
                .map(db -> {

                    // 第一级
                    DbTree tree = new DbTree();

                    MongoDatabase database = mongoClient.getDatabase(db.getString("name"));
                    tree.setTitle(db.getString("name"));

                    MongoIterable<String> collections = database.listCollectionNames();

                    List<DbTree> list = new ArrayList<>();

                    for (String collection : collections) {
                        DbTree dbTree = new DbTree();
                        dbTree.setTitle("集合：" +collection);
                        list.add(dbTree);
                    }

                    tree.setChildren(list);
                    return tree;
                }).collect(Collectors.toList());
    }


    /**
     * 创建数据库和集合
     * @param databaseName
     * @param collectionName
     * @return
     */
    @Override
    public boolean createDbAndCollection(String databaseName, String collectionName) {

        try {
//          MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1:port");
            MongoClient mongoClient = MongoClients.create(url1);
            MongoTemplate template = new MongoTemplate(mongoClient, databaseName);
            template.createCollection(collectionName);


            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 删除集合
     * @param collectionName
     * @return
     */
    @Override
    public boolean deleteCollection(String collectionName) {

        try {
            mongoTemplate.dropCollection(collectionName);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
