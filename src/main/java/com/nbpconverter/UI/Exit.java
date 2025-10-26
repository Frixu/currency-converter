package com.nbpconverter.UI;

public class Exit extends ConsoleInterface {

    @Override
    protected boolean checkCondition(String[] params) {
        return params.length > 0 && "exit".equalsIgnoreCase(params[0]);
    }

    @Override
    protected void handleConcrete(String[] params) {
        System.out.println("Do zobaczenia!");
        System.exit(0);
    }
}