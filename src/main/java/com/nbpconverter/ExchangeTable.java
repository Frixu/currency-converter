package com.nbpconverter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Tabela kursów (zawiera kolekcję ExchangeRate).
 */
public class ExchangeTable implements Iterable<ExchangeRate> {
    private final String id;
    private final String timestamp;
    private final List<ExchangeRate> rates = new ArrayList<>();

    public String getTableName() {
        return id;
    }

    public ExchangeTable(String id, String timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public void addRate(ExchangeRate r) {
        rates.add(r);
    }

    public Optional<ExchangeRate> getRate(String code) {
        return rates.stream()
                .filter(r -> r.getCode().equalsIgnoreCase(code))
                .findFirst();
    }

    public List<ExchangeRate> getRates() {
        return new ArrayList<>(rates);
    }

    @Override
    public Iterator<ExchangeRate> iterator() {
        return rates.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExchangeTable ").append(id).append(" (").append(timestamp).append(")\n");
        for (ExchangeRate r : rates) {
            sb.append("  ").append(r).append("\n");
        }
        return sb.toString();
    }
}
