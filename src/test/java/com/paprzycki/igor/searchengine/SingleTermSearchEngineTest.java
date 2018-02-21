package com.paprzycki.igor.searchengine;

import com.paprzycki.igor.searchengine.core.SimpleDocumentProcessor;
import com.paprzycki.igor.searchengine.core.UnsupportedDocumentException;
import com.paprzycki.igor.searchengine.core.UnsupportedSearchTermException;
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
    private Document document1;
    private Document document2;
    private Document document3;

    @Before
    public void setUp() throws UnsupportedDocumentException {
        document1 = new Document("Document 1", "the brown fox jumped over the brown dog");
        document2 = new Document("Document 2", "the lazy brown dog sat in the corner");
        document3 = new Document("Document 3", "the red fox bit the lazy dog");
        searchEngine.addDocument(document1);
        searchEngine.addDocument(document2);
        searchEngine.addDocument(document3);

    }

    @After
    public void tearDown() {
        document1 = null;
        document2 = null;
        document3 = null;
        searchEngine = null;
    }

    @Test
    public void shouldReturnOrderedDocumentNames() throws UnsupportedSearchTermException {

        List<String> searchForBrownResult = (List<String>) searchEngine.search("brown");
        List<String> searchForFoxResult = (List<String>) searchEngine.search("fox");

        assertEquals(Arrays.asList("Document 1", "Document 2"), searchForBrownResult);
        assertEquals(Arrays.asList("Document 3", "Document 1"), searchForFoxResult);
    }

    @Test
    public void shouldReturnDocumentForGivenName() {
        assertEquals(document1, searchEngine.getDocument("Document 1"));
        assertEquals(document2, searchEngine.getDocument("Document 2"));
        assertEquals(document3, searchEngine.getDocument("Document 3"));
    }

    @Test(expected = UnsupportedSearchTermException.class)
    public void shouldThrowExceptionOnUnsupportedSearchTerm() throws UnsupportedSearchTermException {
        searchEngine.search("to long term to search");
    }

    @Test(expected = UnsupportedDocumentException.class)
    public void shouldThrowExceptionOnIncorrectDocumentInitialization() throws UnsupportedDocumentException {
        searchEngine.addDocument(new Document(null, null));
    }

    @Test(expected = UnsupportedDocumentException.class)
    public void shouldThrowExceptionOnNullDocument() throws UnsupportedDocumentException {
        searchEngine.addDocument(null);
    }

}