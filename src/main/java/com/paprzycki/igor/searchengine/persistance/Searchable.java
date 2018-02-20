package com.paprzycki.igor.searchengine.persistance;

import java.util.Collection;

public interface Searchable {
    Collection<String> search(String term);
}
