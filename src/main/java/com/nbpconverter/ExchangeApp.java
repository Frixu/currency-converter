package com.nbpconverter;

import java.util.Scanner;

/**
 * Główna klasa aplikacji (Singleton).
 */
public class ExchangeApp {
    private static ExchangeApp instance;
    private ExchangeTable exchangeTable;

    private ExchangeApp() {
        createSampleData(); // Używamy tylko danych testowych
    }

    public static synchronized ExchangeApp getInstance() {
        if (instance == null) instance = new ExchangeApp();
        return instance;
    }

    private void createSampleData() {
        ExchangeTable t = new ExchangeTable("A", "2024-01-15");
        t.addRate(new ExchangeRate("USD", "dolar amerykański", 3.95, 1));
        t.addRate(new ExchangeRate("EUR", "euro", 4.30, 1));
        t.addRate(new ExchangeRate("CHF", "frank szwajcarski", 4.55, 1));
        t.addRate(new ExchangeRate("GBP", "funt szterling", 5.02, 1));
        t.addRate(new ExchangeRate("JPY", "jen japoński", 0.027, 100));
        t.addRate(new ExchangeRate("PLN", "złoty polski", 1.0, 1));
        this.exchangeTable = t;
        System.out.println("💰 Załadowano przykładowe kursy walut");
    }

    public void runInteractive() {
        Scanner sc = new Scanner(System.in);

        displayMenu();

        while (true) {
            System.out.print("> ");
            String line = sc.nextLine().trim();

            if (line.isEmpty()) {
                continue;
            }

            if ("exit".equalsIgnoreCase(line)) {
                System.out.println("Do zobaczenia!");
                break;
            } else if ("list".equalsIgnoreCase(line)) {
                displayCurrencies();
            } else if ("help".equalsIgnoreCase(line) || "?".equals(line)) {
                displayMenu();
            } else {
                performExchange(line);
            }

            System.out.println(); // pusta linia dla czytelności
        }

        sc.close();
    }

    private void displayMenu() {
        System.out.println("\n=== KONWERTER WALUT NBP ===");
        System.out.println("Komendy:");
        System.out.println("  list          - pokaż dostępne waluty");
        System.out.println("  [kwota] [z] [na] - konwertuj (np: 100 USD EUR)");
        System.out.println("  help          - wyświetl pomoc");
        System.out.println("  exit          - zakończ program");
        System.out.println("Przykłady:");
        System.out.println("  100 USD EUR   - przelicz 100 dolarów na euro");
        System.out.println("  500 PLN USD   - przelicz 500 złotych na dolary");
        System.out.println("=============================");
    }

    private void displayCurrencies() {
        System.out.println("\n--- DOSTĘPNE WALUTY ---");
        for (var rate : exchangeTable) {
            System.out.println("  " + rate);
        }
        System.out.println("------------------------");
    }

    private void performExchange(String input) {
        try {
            String[] parts = input.split("\\s+");
            if (parts.length != 3) {
                System.out.println("❌ Błąd: Użyj formatu: [kwota] [z_waluty] [na_walutę]");
                System.out.println("   Przykład: 100 USD EUR");
                return;
            }

            double amount = Double.parseDouble(parts[0]);
            String fromCurrency = parts[1].toUpperCase();
            String toCurrency = parts[2].toUpperCase();

            var fromRate = exchangeTable.getRate(fromCurrency);
            var toRate = exchangeTable.getRate(toCurrency);

            if (fromRate.isEmpty()) {
                System.out.println("❌ Błąd: Nieznana waluta źródłowa: " + fromCurrency);
                System.out.println("   Użyj 'list' aby zobaczyć dostępne waluty");
                return;
            }
            if (toRate.isEmpty()) {
                System.out.println("❌ Błąd: Nieznana waluta docelowa: " + toCurrency);
                System.out.println("   Użyj 'list' aby zobaczyć dostępne waluty");
                return;
            }

            double result = fromRate.get().convertTo(toRate.get(), amount);
            System.out.printf("💱 %.2f %s = %.2f %s%n", amount, fromCurrency, result, toCurrency);

        } catch (NumberFormatException e) {
            System.out.println("❌ Błąd: Nieprawidłowa kwota: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Błąd: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println("🚀 Uruchamianie Konwertera Walut NBP...");
        ExchangeApp app = ExchangeApp.getInstance();
        app.runInteractive();
    }
}