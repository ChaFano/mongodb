package com.chafan.service;

import com.chafan.entity.Node;
import com.chafan.entity.Student;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @Auther: 茶凡
 * @ClassName StudentService
 * @date 2023/10/30 16:29
 * @Description
 */


public interface StudentService {

    /**
     * 获取全部学生的数据信息
     * @return
     */
    double getStudents(int number);

    /**
     * 根据 数据库名 和 集合名称 查询数据
     * @param dbName
     * @param collectionName
     * @return
     */
    List<Object> getDataByDbAndCollection(String dbName,String collectionName);

    /**
     * 根据 id 删除数据
     * @param id
     * @return
     */
    boolean deleteStudent(String id);

    /**
     * 根据 ip port database collection 查询指定节点是数据
     * @return
     */
    List<Object> queryByIPPortDatabase(String ip, int port, String databaseName, String collectionName);

    /**
     * 随机插入数据 到 student 集合中
     * @param number
     * @return
     */
    String insertStudentRandom(int number);


    /**
     * 批量的插入数据
     * @return
     */
    Long batchSave(Long number);

    /**
     * 查询每个节点的数据总数
     * @return
     */
    List<Node> getCount();


}
