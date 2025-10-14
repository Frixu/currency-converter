package com.nbpconverter.document;


import org.w3c.dom.*;
import com.nbpconverter.ExchangeRate;
import com.nbpconverter.ExchangeTable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

/**
 * Parsowanie najczęściej spotykanego formatu tabel NBP:
 * - root contains "numer_tabeli" or "data_publikacji"
 * - many "pozycja" elements with "kod_waluty", "kurs_sredni", "nazwa_waluty", "przelicznik"
 *
 * Uwaga: parser jest tolerancyjny, ale zależy od konkretnego XMLa.
 */
public class XMLDocument implements Document {

    @Override
    public ExchangeTable getTable(String content) throws Exception {
        byte[] bytes = content.getBytes("UTF-8");
        try (ByteArrayInputStream in = new ByteArrayInputStream(bytes)) {
            DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
            DocumentBuilder b = f.newDocumentBuilder();
            org.w3c.dom.Document doc = b.parse(in);
            Element root = doc.getDocumentElement();

            String id = getTextContent(root, "numer_tabeli").orElseGet(() -> root.getNodeName());
            String timestamp = getTextContent(root, "data_publikacji").orElseGet(() -> root.getAttribute("data"));

            ExchangeTable table = new ExchangeTable(id, timestamp);

            NodeList positions = root.getElementsByTagName("pozycja");
            for (int i = 0; i < positions.getLength(); i++) {
                Node p = positions.item(i);
                if (p.getNodeType() != Node.ELEMENT_NODE) continue;
                Element e = (Element) p;
                String code = getTextContent(e, "kod_waluty").orElse(null);
                String name = getTextContent(e, "nazwa_waluty").orElse("");
                String rateStr = getTextContent(e, "kurs_sredni").orElse(null);
                String multStr = getTextContent(e, "przelicznik").orElse("1");

                if (code == null || rateStr == null) continue;

                // kurs_sredni in Poland uses comma as decimal separator in some files -> replace
                rateStr = rateStr.replace(',', '.');
                double rate = Double.parseDouble(rateStr);
                int multiplier = Integer.parseInt(multStr.trim());

                ExchangeRate r = new ExchangeRate(code.trim(), name.trim(), rate, multiplier);
                table.addRate(r);
            }
            return table;
        }
    }

    private java.util.Optional<String> getTextContent(Element parent, String tag) {
        NodeList nl = parent.getElementsByTagName(tag);
        if (nl.getLength() == 0) return java.util.Optional.empty();
        String txt = nl.item(0).getTextContent();
        return java.util.Optional.ofNullable(txt == null ? null : txt.trim());
    }
}
