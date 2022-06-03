package internationalization;

import java.util.Locale;

public class DisplayLocales {
    public void displayLocales(){
        System.out.println("Default locale:");
        System.out.println(Locale.getDefault());
        System.out.println("Available locales:");
        Locale[] available = Locale.getAvailableLocales();
        for(Locale locale : available) {
            System.out.println(locale.getDisplayCountry() + "\t" +
                    locale.getDisplayLanguage(locale));
        }
    }
}
