package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.Document;

public interface DocumentDAO {
    void insertDocument(Document document);

    int getNumberOfDocuments();
}
