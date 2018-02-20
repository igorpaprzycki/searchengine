package com.paprzycki.igor.searchengine.persistance;

import com.paprzycki.igor.searchengine.model.TermIndex;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryIndexDAO implements IndexDAO {
    private Map<String, TermIndex> termIndexTable;

    public InMemoryIndexDAO() {
        termIndexTable = new HashMap<>();
    }

    @Override
    public void updateTermIndex(Collection<TermIndex> termIndex) {
        if (termIndex != null) {
            termIndex.forEach((e) ->
                    termIndexTable.put(e.getTerm(), e)
            );
        }
    }

    @Override
    public Map<String, TermIndex> getTermIndexes(Collection<String> terms) {
        Map<String, TermIndex> indexList = new HashMap<>();
        terms.forEach((t) -> {
            TermIndex index = termIndexTable.get(t);
            if (index != null) {
                indexList.put(index.getTerm(), index);
            }
        });
        return indexList;
    }

    @Override
    public Map<String, TermIndex> getAllTermIndexes() {
        return termIndexTable;
    }
}
