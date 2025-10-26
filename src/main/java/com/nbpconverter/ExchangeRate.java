package com.nbpconverter;

/**
 * Reprezentuje kurs pojedynczej waluty.
 */
public class ExchangeRate {
    private final String code;
    private final String name;
    private final double rate;
    private final int converter;

    public ExchangeRate(String code, String name, double rate, int converter) {
        this.code = code;
        this.name = name;
        this.rate = rate;
        this.converter = converter;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getConverter() {
        return converter;
    }

    public double convertTo(ExchangeRate target, double amount) {
        // Konwersja przez PLN
        double amountInPLN = (amount * this.rate) / this.converter;
        return (amountInPLN * target.converter) / target.rate;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %.4f (przelicznik: %d)", code, name, rate, converter);
    }
}