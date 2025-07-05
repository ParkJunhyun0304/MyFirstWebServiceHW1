package com.house;

public interface ICRUD {
    public Furniture add();
    public int update();
    public int delete();
    public void selectOne(int id);
}
