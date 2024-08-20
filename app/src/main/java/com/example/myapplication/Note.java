package com.example.myapplication;

import java.io.Serializable;

public class Note implements Serializable {
    private String id;
    private String content;

    public Note() {
    }

    public Note(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
