package com.paprzycki.igor.searchengine.core;

import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.model.TermIndex;
import com.paprzycki.igor.searchengine.persistance.IndexDAO;
import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentProcessor implements Processor {
    private static final String SPACE = " ";
    private IndexDAO indexDAO;

    public DocumentProcessor(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }

    @Override
    public void processDocument(@NotNull Document document) {
        String[] words = document.getContent()
                .trim()
                .toLowerCase()
                .split(SPACE);

        Map<String, TermIndex> indexes = new HashMap<>();

        for (String word : words) {
            TermIndex termIndex = indexes.get(word);
            if (termIndex != null) {
                termIndex.addDocument(document);
            } else {
                termIndex = new TermIndex(word);
                termIndex.addDocument(document);
                indexes.put(word, termIndex);
            }
        }
    }
}
