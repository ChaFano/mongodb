package com.chafan.service;

import com.chafan.entity.DbTree;
import com.chafan.entity.NodeInformation;


import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName NodeInfoService
 * @date 2023/10/30 11:04
 * @Description TODO
 */
public interface NodeInfoService {

    /**
     * 获取副本集各个节点的信息
     * @return
     */
    List<NodeInformation> getReplicaSetInfo();

    /**
     * 获取有哪些数据库
     * @return
     */
    List<DbTree> getAllDatabases();


    /**
     * 获取每个节点自己创建的数据库
     * @return
     */
    List<DbTree>  getNodeDatabases();


    /**
     * 创建数据库和集合
     * @param databaseName
     * @param collectionName
     * @return
     */
    boolean createDbAndCollection(String databaseName, String collectionName);

    /**
     * 删除集合
     * @param collectionName
     * @return
     */
    boolean deleteCollection(String collectionName);


}
