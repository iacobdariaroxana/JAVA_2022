package com.example.laborator13;

import java.io.IOException;
import java.text.Collator;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class UserInterface {
    public static void displayMessages(Locale locale) {
        String baseName = "com.example.MyResources";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, locale);
        System.out.println(messages.getString("hello"));
        String pattern = messages.getString("welcome");
        Object[] arguments = {"Duke", LocalDate.now()};
        String welcome = new MessageFormat(pattern).format(arguments);
        System.out.println(welcome);
        System.out.println(messages.getString("bye"));
    }

    public static void main(String args[]) throws IOException {
        displayMessages(Locale.forLanguageTag("ro"));
        displayMessages(Locale.forLanguageTag("fr"));
        displayMessages(Locale.getDefault());
        double number = 12345.99;
        System.out.println(NumberFormat.getNumberInstance(Locale.FRANCE).format(number));

        double salary = 1_000_000d;
        Locale roLocale = Locale.forLanguageTag("ro-RO");
        System.out.println(NumberFormat.getCurrencyInstance(roLocale).format(salary));

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(roLocale);
        System.out.println("Date: " + today.format(formatter));

        String words[] = {"ramură", "rămurică", "rățuscă", "repede", "rîu"};
        Arrays.sort(words);
        System.out.println(Arrays.toString(words));

        Collator collator = Collator.getInstance(roLocale);
        Arrays.sort(words, (w1,w2) -> collator.compare(w1, w2));
        System.out.println(Arrays.toString(words));
    }
}
