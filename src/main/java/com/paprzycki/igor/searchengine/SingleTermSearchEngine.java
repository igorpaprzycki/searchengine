package com.paprzycki.igor.searchengine;

import com.paprzycki.igor.searchengine.core.DocumentProcessor;
import com.paprzycki.igor.searchengine.core.UnsupportedDocumentException;
import com.paprzycki.igor.searchengine.core.UnsupportedSearchTermException;
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
    public Collection<String> search(String term) throws UnsupportedSearchTermException {
        validateSearchTerm(term);
        return calculateStatistics(term);
    }

    @Override
    public void addDocument(Document document) throws UnsupportedDocumentException {
        validateDocument(document);
        documentProcessor.processDocument(document);
        documentDAO.insertDocument(document);
    }

    @Override
    public Document getDocument(String documentName) {
        return documentDAO.getDocument(documentName);
    }

    private void validateSearchTerm(String term) throws UnsupportedSearchTermException {
        if (term == null || term.trim().split("\\s").length != 1) {
            throw new UnsupportedSearchTermException("Only single term search is allowed.");
        }
    }

    private void validateDocument(Document document) throws UnsupportedDocumentException {
        if (document != null
                && document.getName() != null
                && document.getContent() != null) {
            if (document.getContent().isEmpty() || document.getName().isEmpty()) {
                throw new UnsupportedDocumentException("Document name and content should not be blank.");
            }
        } else {
            throw new UnsupportedDocumentException("Nulls not allowed in document initialization");
        }
    }

    private List<String> calculateStatistics(String term) {

        List<SearchResult> unsortedSearchResult = new ArrayList<>();
        Map<String, TermIndex> indexes = indexDAO.getTermIndexes(Arrays.asList(term));

        Map<String, Integer> nameAndTermCountMap = indexes.get(term).getNameAndTermCountMap();

        int documentCount = nameAndTermCountMap.size();
        nameAndTermCountMap.forEach((name, count) ->
                unsortedSearchResult.add(new SearchResult(calculateTfIdfFactor(count, name, documentCount), name)));

        sort(unsortedSearchResult);

        List<String> results = new ArrayList<>();
        unsortedSearchResult.forEach((e) -> results.add(e.getDocumentName()));

        return results;
    }


    private Double calculateTfIdfFactor(double wordCount, String name, double documentCount) {
        int numberOfWordsInDocument = documentDAO.getNumberOfWordsInDocument(name);
        int numberOfAllDocuments = documentDAO.getNumberOfDocuments();
        return calculateTfFactor(wordCount, numberOfWordsInDocument)
                * calculateIdfFactor(numberOfAllDocuments, documentCount);
    }

    private double calculateTfFactor(double wordCount, double numberOfWordsInDocument) {
        return wordCount / numberOfWordsInDocument;
    }

    private double calculateIdfFactor(double numberOfAllDocuments, double numberOfDocumentsOfInterest) {
        return Math.log10(numberOfAllDocuments / numberOfDocumentsOfInterest);
    }
}
