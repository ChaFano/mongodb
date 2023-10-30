package com.chafan.entity;

/**
 * @Auther: 茶凡
 * @ClassName Book
 * @date 2023/10/29 22:13
 * @Description TODO
 */


public class Book {
    private String name;

    public Book(String name) { this.name = name; }

    public Book() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                '}';
    }


}
