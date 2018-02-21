package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.Document;

import java.util.Collection;
import java.util.Map;

public interface DocumentDAO {
    void insertDocument(Document document);

    int getNumberOfDocuments();

    Document getDocument(String documentName);

    Map<String, Integer> getNumberOfWordsInDocument(Collection<String> documentNames);
}
