package com.paprzycki.igor.searchengine.api;

import com.paprzycki.igor.searchengine.core.DocumentProcessor;
import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.model.SearchResult;
import com.paprzycki.igor.searchengine.model.TermIndex;
import com.paprzycki.igor.searchengine.persistance.DocumentDAO;
import com.paprzycki.igor.searchengine.persistance.IndexDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.sort;

@Service
public class SingleTermSearchEngine implements SearchEngine {
    @Autowired
    private IndexDAO indexDAO;

    @Autowired
    private DocumentDAO documentDAO;

    @Autowired
    private DocumentProcessor documentProcessor;

    @Override
    public Collection<String> search(String term) {
        return calculateStatistics(term);
    }

    @Override
    public void addDocument(Document document) {
        if (document != null) {
            documentProcessor.processDocument(document);
            documentDAO.insertDocument(document);
        }
    }

    @Override
    public Document getDocument(String documentName) {
        return documentDAO.getDocument(documentName);
    }

    private List<String> calculateStatistics(String term) {

        List<SearchResult> sortedSearchResult = new ArrayList<>();
        Map<String, TermIndex> indexes = indexDAO.getTermIndexes(Arrays.asList(term));

        Map<String, Integer> nameAndTermCountMap = indexes.get(term).getNameAndTermCountMap();

        int documentCount = nameAndTermCountMap.size();
        nameAndTermCountMap.forEach((name, count) ->
                sortedSearchResult.add(new SearchResult(calculateTfIdfFactor(count, name, documentCount), name)));

        sort(sortedSearchResult);

        List<String> results = new ArrayList<>();
        sortedSearchResult.forEach((e) -> results.add(e.getDocumentName()));

        return results;
    }


    private Double calculateTfIdfFactor(int wordCount, String name, int documentCount) {
        int numberOfWordsInDocument = documentDAO.getNumberOfWordsInDocument(name);
        int numberOfAllDocuments = documentDAO.getNumberOfDocuments();
        return calculateTfFactor(wordCount, numberOfWordsInDocument)
                * calculateIdfFactor(numberOfAllDocuments, documentCount);
    }

    private double calculateTfFactor(int wordCount, int numberOfWordsInDocument) {
        return (double) wordCount / (double) numberOfWordsInDocument;
    }

    private double calculateIdfFactor(int numberOfAllDocuments, int numberOfDocumentsOfInterest) {
        return Math.log10((double) numberOfAllDocuments / (double) numberOfDocumentsOfInterest);
    }
}
