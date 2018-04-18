package com.paprzycki.igor.searchengine.core;

import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.model.TermIndex;
import com.paprzycki.igor.searchengine.persistance.IndexDAO;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class SimpleDocumentProcessor implements DocumentProcessor {
    private static final String SPACE = " ";
    private IndexDAO indexDAO;

    public SimpleDocumentProcessor(IndexDAO indexDAO) {
        this.indexDAO = indexDAO;
    }

    @Override
    public void processDocument(Document document) {
        String[] words = document.getContent()
                .trim()
                .toLowerCase()
                .split(SPACE);

        Map<String, TermIndex> indexes = indexDAO.getTermIndexes(Arrays.asList(words));

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
        indexDAO.updateTermIndex(indexes.values());
    }
}
