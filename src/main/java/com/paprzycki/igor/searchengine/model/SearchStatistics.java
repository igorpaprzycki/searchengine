package com.paprzycki.igor.searchengine.model;

import java.util.HashMap;
import java.util.Map;

public class SearchStatistics {
    private Map<String, Integer> termCountMap;
    private Map<String, Integer> numberOfWordsInDocumentMap;
    private int documentCount;
    private int numberOfAllDocuments;

    public SearchStatistics(int documentCount, int numberOfAllDocuments) {
        this.termCountMap = new HashMap<>();
        this.numberOfWordsInDocumentMap = new HashMap<>();
        this.documentCount = documentCount;
        this.numberOfAllDocuments = numberOfAllDocuments;
    }

    public void addDocumentStatistics(String documentName, int termCount, int documentWordsCount) {
        termCountMap.put(documentName, termCount);
        numberOfWordsInDocumentMap.put(documentName, documentWordsCount);
    }

    public int getTermCount(String name) {
        return termCountMap.get(name);
    }

    public int getDocumentCount() {
        return documentCount;
    }

    public int getNumberOfWordsInDocument(String name) {
        return numberOfWordsInDocumentMap.get(name);
    }

    public int getNumberOfAllDocuments() {
        return numberOfAllDocuments;
    }
}
