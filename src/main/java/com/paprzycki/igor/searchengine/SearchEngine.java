package com.paprzycki.igor.searchengine;

import com.paprzycki.igor.searchengine.core.UnsupportedDocumentException;
import com.paprzycki.igor.searchengine.core.UnsupportedSearchTermException;
import com.paprzycki.igor.searchengine.model.Document;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface SearchEngine {
    Collection<String> search(String term) throws UnsupportedSearchTermException;

    void addDocument(Document document) throws UnsupportedDocumentException;

    Document getDocument(String documentName);
}
