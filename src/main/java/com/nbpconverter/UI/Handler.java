package com.nbpconverter.UI;

/**
 * Interfejs łańcucha odpowiedzialności.
 */
public interface Handler {
    void setNext(Handler next);
    void handle(String[] params);
}