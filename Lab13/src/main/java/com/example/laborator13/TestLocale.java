package com.example.laborator13;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;
public class TestLocale {
    public static void printWeekdays(Locale loc) {
        WeekFields wf = WeekFields.of(loc);
        DayOfWeek day = wf.getFirstDayOfWeek();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            System.out.println(day.getDisplayName(TextStyle.SHORT, loc));
            day = day.plus(1);
        }
    }
    public static void main(String args[]) {
        printWeekdays(Locale.getDefault());
//        System.out.println("Default locale:");
//        System.out.println(Locale.getDefault());
////        locateInfo(Locale.getDefault());
//        System.out.println("Available locales:");
//        Locale[] available =
//                Locale.getAvailableLocales();
//        for(Locale locale : available) {
//            System.out.println(locale.getDisplayCountry() + "\t" +
//                    locale.getDisplayLanguage(locale));
//        }
    }
}