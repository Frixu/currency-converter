package com.nbpconverter.UI;

import com.nbpconverter.ExchangeTable;
import com.nbpconverter.ExchangeRate;
import java.util.Optional;

public class ExchangeCurrency extends ConsoleInterface {
    private final ExchangeTable table;

    public ExchangeCurrency(ExchangeTable table) {
        this.table = table;
    }

    @Override
    protected boolean checkCondition(String[] params) {
        // Obsługuje TYLKO gdy są 3 części (kwota waluta waluta)
        if (params.length == 0) return false;

        String[] parts = params[0].split("\\s+");
        return parts.length == 3 && isNumeric(parts[0]);
    }

    @Override
    protected void handleConcrete(String[] params) {
        performExchange(params[0]);
        // Po konwersji wróć do menu
        if (next != null) {
            next.handle(new String[]{""});
        }
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void performExchange(String input) {
        try {
            String[] parts = input.split("\\s+");
            double amount = Double.parseDouble(parts[0]);
            String fromCurrency = parts[1].toUpperCase();
            String toCurrency = parts[2].toUpperCase();

            Optional<ExchangeRate> fromRate = table.getRate(fromCurrency);
            Optional<ExchangeRate> toRate = table.getRate(toCurrency);

            if (fromRate.isEmpty()) {
                System.out.println("Błąd: Nieznana waluta źródłowa: " + fromCurrency);
                return;
            }
            if (toRate.isEmpty()) {
                System.out.println("Błąd: Nieznana waluta docelowa: " + toCurrency);
                return;
            }

            double result = fromRate.get().convertTo(toRate.get(), amount);
            System.out.printf("💱 %.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);

        } catch (NumberFormatException e) {
            System.out.println("Błąd: Nieprawidłowa kwota");
        } catch (Exception e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}