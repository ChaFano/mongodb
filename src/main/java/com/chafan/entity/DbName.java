package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName DbName
 * @date 2023/10/30 11:35
 * @Description 数据库实体 用于封装有哪些数据的名称
 */
public class DbName {

    private String name;

    public DbName(String name) {
        this.name = name;
    }

    public DbName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DbName{" +
                "name='" + name + '\'' +
                '}';
    }
}
