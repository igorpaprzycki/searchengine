package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;

import java.util.Collection;
import java.util.Map;

public interface IndexDAO {
    void updateTermIndex(TermIndex termIndex);

    Map<String, TermIndex> getTermIndexes(Collection<String> terms);

}
