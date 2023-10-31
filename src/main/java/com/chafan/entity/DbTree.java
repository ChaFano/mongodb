package com.chafan.entity;

import java.util.List;

/**
 * @Auther: 茶凡
 * @ClassName DbName
 * @date 2023/10/30 11:35
 * @Description 数据库实体 用于封装有哪些数据的名称
 */
public class DbTree {

    private String title;
    private boolean spread = true;
    private List<DbTree> children;

    public DbTree(String title, boolean spread, List<DbTree> children) {
        this.title = title;
        this.spread = spread;
        this.children = children;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public DbTree() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DbTree> getChildren() {
        return children;
    }

    public void setChildren(List<DbTree> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DbTree{" +
                "title='" + title + '\'' +
                ", spread=" + spread +
                ", children=" + children +
                '}';
    }
}
