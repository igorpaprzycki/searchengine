package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;

import java.util.Collection;
import java.util.Map;

public interface IndexDAO {
    void updateTermIndex(Collection<TermIndex> termIndex);

    Map<String, TermIndex> getTermIndexes(Collection<String> terms);

    Map<String, TermIndex> getAllTermIndexes();

}
