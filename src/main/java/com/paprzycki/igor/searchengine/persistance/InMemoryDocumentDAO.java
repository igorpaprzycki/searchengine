package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.Document;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
        documentsTable.put(document.getName(), document);
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
    public Map<String, Integer> getNumberOfWordsInDocument(Collection<String> documentNames) {
        Map<String, Integer> resultsMap = new HashMap<>();
        documentNames.forEach((d) ->
                resultsMap.put(d, documentsTable.get(d).getContent().trim().split("\\s").length));

        return resultsMap;
    }

}
