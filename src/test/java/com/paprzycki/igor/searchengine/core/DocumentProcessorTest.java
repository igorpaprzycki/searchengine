package com.paprzycki.igor.searchengine.core;

import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.model.TermIndex;
import com.paprzycki.igor.searchengine.persistance.InMemoryIndexDAO;
import com.paprzycki.igor.searchengine.persistance.IndexDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentProcessorTest {

    private Processor processor;
    private IndexDAO indexDAO;
    private Map<String, TermIndex> indexes;
    private Document document;

    @Before
    public void setUp() {
        indexDAO = new InMemoryIndexDAO();
        processor = new DocumentProcessor(indexDAO);
    }

    @After
    public void tearDown() {
        indexDAO = null;
        processor = null;
        document = null;
        indexes = null;
    }

    @Test
    public void shouldGenerateNewIndex() {
        document = new Document("Document 1", "the brown fox jumped over the brown dog");
        processor.processDocument(document);

        indexes = indexDAO.getAllTermIndexes();

        assertEquals(6, indexes.size());

        assertIndex("the", "Document 1", 2);
        assertIndex("brown", "Document 1", 2);
        assertIndex("fox", "Document 1", 1);
        assertIndex("jumped", "Document 1", 1);
        assertIndex("over", "Document 1", 1);
        assertIndex("dog", "Document 1", 1);
    }

    @Test
    public void shouldUpdateExistingIndex() {
        document = new Document("Document 1", "   the brown fox jumped over the brown dog");
        processor.processDocument(document);

        indexes = indexDAO.getAllTermIndexes();

        assertEquals(6, indexes.size());

        document = new Document("Document 2", "the red snake ate red fox   ");
        processor.processDocument(document);

        indexes = indexDAO.getAllTermIndexes();

        assertEquals(9, indexes.size());

        assertIndex("the", "Document 1", 2);
        assertIndex("brown", "Document 1", 2);
        assertIndex("fox", "Document 1", 1);
        assertIndex("jumped", "Document 1", 1);
        assertIndex("over", "Document 1", 1);
        assertIndex("dog", "Document 1", 1);

        assertIndex("the", "Document 2", 1);
        assertIndex("red", "Document 2", 2);
        assertIndex("snake", "Document 2", 1);
        assertIndex("ate", "Document 2", 1);
        assertIndex("fox", "Document 2", 1);

    }

    private void assertIndex(String term, String documentName, int occurrences) {
        TermIndex testIndex;
        testIndex = indexes.get(term);
        assertTrue(testIndex.getNameAndTermCountMap().containsKey(documentName));
        assertEquals(occurrences, testIndex.getWordCount(documentName));
    }
}