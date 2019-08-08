package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {

    private String id;
    private String title;
    private String insert_img;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date up_date;
    private String guru_id;
    private String status;

    public Article() {
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

    public String getInsert_img() {
        return insert_img;
    }

    public void setInsert_img(String insert_img) {
        this.insert_img = insert_img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUp_date() {
        return up_date;
    }

    public void setUp_date(Date up_date) {
        this.up_date = up_date;
    }

    public String getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(String guru_id) {
        this.guru_id = guru_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Article(String id, String title, String insert_img, String content, Date up_date, String guru_id, String status) {
        this.id = id;
        this.title = title;
        this.insert_img = insert_img;
        this.content = content;
        this.up_date = up_date;
        this.guru_id = guru_id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", insert_img='" + insert_img + '\'' +
                ", content='" + content + '\'' +
                ", up_date=" + up_date +
                ", guru_id='" + guru_id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
