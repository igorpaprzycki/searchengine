package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryIndexDAO implements IndexDAO {
    private Map<String, TermIndex> termIndexTable;

    public InMemoryIndexDAO() {
        termIndexTable = new HashMap<>();
    }

    @Override
    public void updateTermIndex(TermIndex termIndex) {
        termIndexTable.put(termIndex.getTerm(), termIndex);
    }

    @Override
    public TermIndex getTermIndex(String term) {
        return termIndexTable.get(term);
    }
}
