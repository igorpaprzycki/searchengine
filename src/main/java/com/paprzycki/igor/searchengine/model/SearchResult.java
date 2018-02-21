package com.paprzycki.igor.searchengine.model;

public class SearchResult implements Comparable<SearchResult> {
    private Double factor;
    private String documentName;

    public SearchResult(Double factor, String documentName) {
        this.factor = factor;
        this.documentName = documentName;
    }

    public Double getFactor() {
        return factor;
    }

    public String getDocumentName() {
        return documentName;
    }

    @Override
    public int compareTo(SearchResult o) {
        return o.getFactor().compareTo(factor);
    }
}

