package com.example.guldanasingersproject.Entities;

import java.util.Date;

public class AlbumEntity {
    public Integer id;
    public String title;
    public Date date_released;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate_released(Date date_released) {
        this.date_released = date_released;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate_released() {
        return date_released;
    }

    public String getName() {
        return name;
    }

    public AlbumEntity(Integer id, String title, Date date_released, String name) {
        this.id = id;
        this.title = title;
        this.date_released = date_released;
        this.name = name;
    }

    public String name;
}
