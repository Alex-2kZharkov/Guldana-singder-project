package com.example.guldanasingersproject.Entities;

public class TotalAlbumsFields {
    public Integer count;
    public String name;

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public TotalAlbumsFields(Integer count, String name) {
        this.count = count;
        this.name = name;
    }
}
