package com.paprzycki.igor.searchengine.model;

import org.springframework.stereotype.Component;

@Component
public class Document {
    private String name;
    private String content;

    public Document(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
