package com.paprzycki.igor.searchengine.model;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
public class TermIndex {
    private String term;
    private Set<Document> documentList;

    public TermIndex(@NotNull String term) {
        this.term = term;
        documentList = new HashSet<>();
    }

    public String getTerm() {
        return this.term;
    }

    public Collection<Document> getDocumentList() {
        return documentList;
    }

    public void updateIndex(@NotNull Document document) {
        documentList.add(document);
    }
}
