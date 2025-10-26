package com.nbpconverter.UI;

public abstract class ConsoleInterface {
    protected ConsoleInterface next;
    protected String parameter = "";

    public void setNext(ConsoleInterface next) {
        this.next = next;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    /**
     * Metoda wywoływana dla wszystkich żądań; implementacja powinna sprawdzić warunek
     * i albo obsłużyć, albo przekazać dalej.
     */
    public final void handle(String[] params) {
        if (checkCondition(params)) {
            handleConcrete(params);
        } else if (next != null) {
            next.handle(params);
        } else {
            System.out.println("Nieobsługiwane polecenie.");
        }
    }

    protected abstract boolean checkCondition(String[] params);
    protected abstract void handleConcrete(String[] params);
}
