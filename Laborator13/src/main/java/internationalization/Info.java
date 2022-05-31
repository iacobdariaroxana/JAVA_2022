package internationalization;

import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class Info {
    private String getWeekdays(Locale locale) {
        StringBuilder days = new StringBuilder();
        WeekFields wf = WeekFields.of(locale);
        DayOfWeek day = wf.getFirstDayOfWeek();
        for (int i = 0; i < DayOfWeek.values().length; i++) {
            days.append(day.getDisplayName(TextStyle.FULL, locale)).append(" ");
            day = day.plus(1);
        }
        return days.toString();
    }

    private void printMonths(Locale locale) {
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        System.out.println("Months: " + Arrays.toString(dateFormatSymbols.getMonths()));
    }

    public void info(Locale ... locale) {
        Locale myLocale;
        if (locale.length == 0){
            myLocale = Locale.getDefault();
        } else {
            myLocale = locale[0];
        }
        System.out.println("Country: " + myLocale.getDisplayCountry());
        System.out.println("Language: " + myLocale.getDisplayLanguage());
        Currency currency = Currency.getInstance(myLocale);
        System.out.println("Currency: " + currency.getDisplayName() + " " + currency.getSymbol());
        System.out.println("Week days : " + getWeekdays(myLocale));
        printMonths(myLocale);
        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(myLocale);
        System.out.println("Today: " + today.format(formatter));
    }

}
