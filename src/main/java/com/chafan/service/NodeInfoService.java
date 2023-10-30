package com.chafan.service;

import com.chafan.entity.DbName;
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
    List<DbName> getAllDatabases();



}
