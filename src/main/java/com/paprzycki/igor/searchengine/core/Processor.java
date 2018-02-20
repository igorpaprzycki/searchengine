package com.paprzycki.igor.searchengine.core;

import com.paprzycki.igor.searchengine.model.Document;
import com.sun.istack.internal.NotNull;

interface Processor {
    void processDocument(@NotNull Document document);
}
