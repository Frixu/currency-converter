package com.nbpconverter.UI;

public class Menu extends ConsoleInterface {

    @Override
    protected boolean checkCondition(String[] params) {
        // Menu wyświetla się TYLKO przy pustym wejściu
        return params.length == 0 || params[0].isEmpty();
    }

    @Override
    protected void handleConcrete(String[] params) {
        displayMenu();
        // Po wyświetleniu menu PRZEKAŻ dalej puste polecenie
        if (next != null) {
            next.handle(new String[]{""});
        }
    }

    private void displayMenu() {
        System.out.println("\n=== KONWERTER WALUT NBP ===");
        System.out.println("1. list          - pokaż dostępne waluty");
        System.out.println("2. [kwota] [z] [na] - konwertuj (np: 100 USD EUR)");
        System.out.println("3. exit          - zakończ program");
        System.out.println("=============================");
    }
}