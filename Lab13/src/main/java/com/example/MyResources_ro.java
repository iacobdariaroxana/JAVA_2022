package com.example.laborator13;

import java.util.ListResourceBundle;

public class MyResources_ro extends ListResourceBundle {
    private static final Object[][] contents = {
            {"hello", "Salut"},
            {"welcome", "Utilizatorul {0} s-a conectat la {1}"},
                    {"bye", "La revedere"}
            };
    @Override
    public Object[][] getContents() {
        return contents;
    }
}