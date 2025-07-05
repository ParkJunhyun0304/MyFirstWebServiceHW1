package com.house;

public interface ICRUD {
    public Furniture add();
    public int update(Furniture obj);
    public int delete(Furniture obj);
    public void selectOne(int id);
}
