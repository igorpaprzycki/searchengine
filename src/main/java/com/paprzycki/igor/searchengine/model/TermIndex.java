package com.paprzycki.igor.searchengine.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TermIndex {
    private String term;
    private Map<String, Integer> nameAndTermCountMap;

    public TermIndex(String term) {
        this.term = term;
        nameAndTermCountMap = new HashMap<>();
    }

    public String getTerm() {
        return this.term;
    }

    public Map<String, Integer> getNameAndTermCountMap() {
        return nameAndTermCountMap;
    }

    public int getWordCount(String documentName) {
        return nameAndTermCountMap.getOrDefault(documentName, 0);
    }

    public void addDocument(Document document) {
        String documentName = document.getName();
        if (nameAndTermCountMap.containsKey(documentName)) {
            int count = nameAndTermCountMap.get(documentName);
            nameAndTermCountMap.put(document.getName(), ++count);
        } else {
            nameAndTermCountMap.put(document.getName(), 1);
        }
    }
}
