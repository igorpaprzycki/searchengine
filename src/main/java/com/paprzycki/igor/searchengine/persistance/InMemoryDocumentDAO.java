package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.Document;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryDocumentDAO implements DocumentDAO {
    private Map<String, Document> documentsTable;

    public InMemoryDocumentDAO() {
        documentsTable = new HashMap<>();
    }

    @Override
    public void insertDocument(Document document) {
        if (document != null) {
            documentsTable.put(document.getName(), document);
        }
    }

    @Override
    public int getNumberOfDocuments() {
        return documentsTable.size();
    }

    @Override
    public Document getDocument(String documentName) {
        return documentsTable.get(documentName);
    }

    @Override
    public int getNumberOfWordsInDocument(String documentName) {
        return documentsTable.get(documentName).getContent().trim().split("\\s").length;
    }

}
