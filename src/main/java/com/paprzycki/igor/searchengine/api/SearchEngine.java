package com.paprzycki.igor.searchengine.api;

import com.paprzycki.igor.searchengine.model.Document;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface SearchEngine {
    Collection<String> search(String term);

    void addDocument(Document document);

    Document getDocument(String documentName);
}
