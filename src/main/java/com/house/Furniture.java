package com.house;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter

public class Furniture {
    private int id;
    private String name;
    private int price;
    private int category;
    private int width; // 가로
    private int depth; //세로
    private int height; // 높이

    String[] categories = {
            "침대", "매트리스 토퍼", "테이블/식탁/책상", "소파",
            "서랍/수납장", "행거/옷장", "거울", "화장대/콘솔",
            "유아동기구", "야외기구", "가벽/파티션"
    };

    public void setData(int price, int width, int depth, int height) {
        this.price = price;
        this.width = width;
        this.depth = depth;
        this.height = height;
    }

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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(name).append(" (").append(price).append("원)");
        builder.append("[").append(categories[category-1]).append("] [w:").append(width).append("mm]");
        builder.append("[d:").append(depth).append("mm]").append("][h:").append(height).append("mm]");
        return builder.toString();
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name).append(",").append(price).append(",").append(category).append(",")
                .append(width).append(",").append(depth).append(",").append(height);
        return sb.toString();
    }
}
