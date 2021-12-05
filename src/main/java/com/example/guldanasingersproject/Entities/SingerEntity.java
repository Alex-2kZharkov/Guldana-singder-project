package com.example.guldanasingersproject.Entities;

public class SingerEntity {
    public Integer id;
    public String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SingerEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
