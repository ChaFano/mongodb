package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName Node
 * @date 2023/11/3 0:06
 * @Description TODO
 */
public class Node {


    private String name;
    private Long count;

    public Node(String name, Long count) {
        this.name = name;
        this.count = count;
    }

    public Node() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
