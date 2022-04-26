package com.example.michalnews;

public class NewsDetails {
    String picurl, time, title, author, url, desc;

    public NewsDetails(String picurl, String time, String title, String author, String url, String desc) {
        this.picurl = picurl;
        this.time = time;
        this.title = title;
        this.author = author;
        this.url = url;
        this.desc = desc;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
