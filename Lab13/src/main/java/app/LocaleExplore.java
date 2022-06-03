package app;

import internationalization.DisplayLocales;
import internationalization.Info;
import internationalization.SetLocale;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void displayMessages(Locale locale) {
        String baseName = "res.Messages";
        ResourceBundle messages = ResourceBundle.getBundle(baseName, locale);
        Scanner scanner = new Scanner(System.in);
        System.out.println(messages.getString("prompt"));
        String command = scanner.nextLine();
        String pattern;
        Object[] arguments;
        switch (command.split(" ")[0]) {
            case "locales":
                System.out.println(messages.getString("locales"));
                new DisplayLocales().displayLocales();
                break;
            case "locale.set":
                pattern = messages.getString("locale.set");
                String[] params = command.split(" ");
                new SetLocale().setLocale(new Locale(params[1], params[2]));
                Object[] argumentsSetLocale = { Locale.getDefault() };
                String localeSet = new MessageFormat(pattern).format(argumentsSetLocale);
                System.out.println(localeSet);
                break;
            case "info":
                pattern = messages.getString("info");
                Object[] argumentsInfo = { Locale.getDefault() };
                String localeInfo = new MessageFormat(pattern).format(argumentsInfo);
                System.out.println(localeInfo);
                String[] localeParams = command.split(" ");
                if (localeParams.length == 3){
                    new Info().info(new Locale(localeParams[1], localeParams[2]));
                } else {
                    new Info().info();
                }
                break;
            default :
                System.out.println(messages.getString("invalid"));
        }
    }

    public static void main(String[] args) {
        while (true){
            displayMessages(Locale.getDefault());
        }
    }
}
