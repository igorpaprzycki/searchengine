package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;

public interface IndexDAO {
    void updateTermIndex(TermIndex termIndex);

    TermIndex getTermIndex(String term);

}
