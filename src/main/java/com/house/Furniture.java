package com.house;

import lombok.Getter;

@Getter

public class Furniture {
    private int id;
    private String name;
    private int price;
    private int category;
    private int width; // 가로
    private int depth; //세로
    private int height; // 높이

    Furniture(){}

    Furniture(int id, String name, int price, int category, int width, int depth, int height) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.width = width;
        this.depth = depth;
        this.height = height;
    }
}
