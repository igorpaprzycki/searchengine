package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryIndexDAO implements IndexDAO {
    private Map<String, TermIndex> termIndexTable;

    public InMemoryIndexDAO() {
        termIndexTable = new HashMap<>();
    }

    @Override
    public void updateTermIndex(TermIndex termIndex) {
        if (termIndex != null) {
            termIndexTable.put(termIndex.getTerm(), termIndex);
        }
    }

    @Override
    public Map<String, TermIndex> getTermIndexes(Collection<String> terms) {
        Map<String, TermIndex>  indexList = new HashMap<>();

        return indexList;
    }
}
