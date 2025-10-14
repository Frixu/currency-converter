package com.nbpconverter.repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class REST implements IRemoteRepository {
    @Override
    public byte[] get(String url) throws Exception {
        System.out.println("Łączę z: " + url);

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        int responseCode = conn.getResponseCode();
        System.out.println("Kod odpowiedzi: " + responseCode);

        if (responseCode != 200) {
            // Spróbuj pobrać treść błędu
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                StringBuilder errorContent = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorContent.append(line);
                }
                System.out.println("Treść błędu: " + errorContent.toString());
            }
            throw new RuntimeException("HTTP GET failed: " + responseCode + " for URL: " + url);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                content.append(line);
            }
            System.out.println("Pobrano " + content.length() + " znaków");
            return content.toString().getBytes();
        }
    }
}