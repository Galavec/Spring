package com.galavec.findstrings.util;

import java.util.ArrayList;
import java.util.List;

public class StringStorage {

    public List<String> storeFoundText(String data, List<String> addFoundText) {
        List<String> lAddFoundText = new ArrayList<>(addFoundText);

        if (!(lAddFoundText.contains(data) || data.equals("")))
            lAddFoundText.add(data);

        return lAddFoundText;
    }

    public List<String> storeTextNotFound(List<String> nonRepeatingTexts, List<String> addFoundText) {
        List<String> lTextNotFound = new ArrayList<>(nonRepeatingTexts);

        if (!(addFoundText.isEmpty())) {
            lTextNotFound.clear();

            for (String nonRepeatingText : nonRepeatingTexts) {
                if (!(addFoundText.contains(nonRepeatingText))) {
                    lTextNotFound.add(nonRepeatingText);
                }
            }
        }

        return lTextNotFound;
    }

    public List<String> storeRepeatedData(String data, List<String> repeatedText) {
        if (!(repeatedText.contains(data) || data.equals("")))
            repeatedText.add(data);

        return repeatedText;
    }

    public String[] separatedByCommas(String data) {
        return data.split(",");
    }
}
