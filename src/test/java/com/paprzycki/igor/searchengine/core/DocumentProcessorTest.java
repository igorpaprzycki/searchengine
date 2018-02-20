package com.paprzycki.igor.searchengine.core;

import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.model.TermIndex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DocumentProcessorTest {

    private Processor processor;
    private Map<String, TermIndex> indexes;
    private Document document;

    @Before
    public void setUp() {
        processor = new DocumentProcessor();
    }

    @After
    public void tearDown() {
        processor = null;
        document = null;
        indexes = null;
    }

    @Test
    public void shouldGenerateNewIndex() {
        document = new Document("Document 1", "the brown fox jumped over the brown dog");
        indexes = processor.processDocument(document);

        assertEquals(6, indexes.size());

        assertIndex("the", 2);
        assertIndex("brown", 2);
        assertIndex("fox", 1);
        assertIndex("jumped", 1);
        assertIndex("over", 1);
        assertIndex("dog", 1);

    }

    @Test
    public void shouldUpdateExistingIndex() {
        Document document1 = new Document("Document 1", "the brown fox jumped over the brown dog");
        Map<String, TermIndex> indexes = processor.processDocument(document1);

        assertEquals(6, indexes.size());

        Document document2 = new Document("Document 2", "the red snake ate brown fox");
    }

    private void assertIndex(String term, int occurrences) {
        TermIndex testIndex;
        testIndex = indexes.get(term);
        assertTrue(testIndex.getNameAndTermCountMap().containsKey(document.getName()));
        assertEquals(occurrences, testIndex.getWordCount(document));
    }
}