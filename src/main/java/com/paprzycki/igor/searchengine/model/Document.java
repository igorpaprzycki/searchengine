package com.paprzycki.igor.searchengine.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

@Component
public class Document {
    private String name;
    private String content;

    public Document(@NotNull String name, @NotNull String content) {
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
