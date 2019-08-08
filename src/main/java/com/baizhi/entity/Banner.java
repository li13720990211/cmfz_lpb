package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {

    private String id;
    private String title;
    private String description;
    private String status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date up_date;
    private String img_path;

    public Banner(String id, String title, String description, String status, Date up_date, String img_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.up_date = up_date;
        this.img_path = img_path;
    }

    public Banner() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", up_date=" + up_date +
                ", img_path='" + img_path + '\'' +
                '}';
    }
}
