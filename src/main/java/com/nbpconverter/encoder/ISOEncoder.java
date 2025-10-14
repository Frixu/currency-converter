package com.nbpconverter.encoder;

import java.util.Base64;

/**
 * Przykładowa strategia: kodowanie Base64.
 */
public class ISOEncoder implements Encoder {
    @Override
    public String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }
}

