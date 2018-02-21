package com.paprzycki.igor.searchengine.api;

import com.paprzycki.igor.searchengine.core.SimpleDocumentProcessor;
import com.paprzycki.igor.searchengine.model.Document;
import com.paprzycki.igor.searchengine.persistance.InMemoryDocumentDAO;
import com.paprzycki.igor.searchengine.persistance.InMemoryIndexDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        SingleTermSearchEngine.class,
        InMemoryIndexDAO.class,
        InMemoryDocumentDAO.class,
        SimpleDocumentProcessor.class
})
public class SingleTermSearchEngineTest {

    @Autowired
    private SearchEngine searchEngine;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shouldReturnOrderedDocumentNames() {
        Document document1 = new Document("Document 1", "the brown fox jumped over the brown dog");
        Document document2 = new Document("Document 2", "the lazy brown dog sat in the corner");
        Document document3 = new Document("Document 3", "the red fox bit the lazy dog");

        searchEngine.addDocument(document1);
        searchEngine.addDocument(document2);
        searchEngine.addDocument(document3);

        List<String> searchForBrownResult = (List<String>) searchEngine.search("brown");
        List<String> searchForFoxResult = (List<String>) searchEngine.search("fox");

        assertEquals(Arrays.asList("Document 1", "Document 2"), searchForBrownResult);
        assertEquals(Arrays.asList("Document 1", "Document 3"), searchForFoxResult);
    }

}