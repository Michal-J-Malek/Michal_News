package com.example.michalnews;

public class SourceDetails {
    String newsID;
    String name;
    String topic;

    public SourceDetails(String newsID, String name, String topic) {
        this.newsID = newsID;
        this.name = name;
        this.topic = topic;
    }

    public String getNewsID() {
        return newsID;
    }

    public void setNewsID(String newsID) {
        this.newsID = newsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
