package com.nbpconverter.document;

import com.nbpconverter.ExchangeTable;

public interface Document {
    ExchangeTable getTable(String content) throws Exception;
}
