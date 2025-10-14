package com.nbpconverter;

/**
 * Prosta klasa pomocnicza do przeliczeń walut.
 * Wzór: amount (r1) -> PLN -> r2
 *
 * PLN = amount * (r1.rate / r1.converter)
 * result = PLN / (r2.rate / r2.converter)
 */
public class Exchange {
    public static double exchange(double amount, ExchangeRate from, ExchangeRate to) {
        double pln = amount * (from.getRate() / from.getConverter());
        return pln / (to.getRate() / to.getConverter());
    }
}