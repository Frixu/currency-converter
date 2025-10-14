package com.nbpconverter.UI;

import com.nbpconverter.ExchangeTable;

public class DisplayTable extends ConsoleInterface {
    private final ExchangeTable table;

    public DisplayTable(ExchangeTable table) {
        this.table = table;
    }

    @Override
    protected boolean checkCondition(String[] params) {
        return params.length > 0 && "list".equalsIgnoreCase(params[0]);
    }

    @Override
    protected void handleConcrete(String[] params) {
        displayCurrencies();
        // Po wyświetleniu wróć do menu
        if (next != null) {
            next.handle(new String[]{""});
        }
    }

    private void displayCurrencies() {
        System.out.println("\n--- DOSTĘPNE WALUTY ---");
        for (var rate : table) {
            System.out.println("  " + rate);
        }
        System.out.println("------------------------");
    }
}