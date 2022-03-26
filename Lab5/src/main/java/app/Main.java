package app;

import commands.*;
import exceptions.InvalidCatalogException;
import model.*;

import java.io.IOException;
import java.util.*;

public class Main {
    private void testCreateSave() throws IOException {
        Catalog catalog = new Catalog("My References");
        var book = new Book("cursJava", "Fisa disciplinei JAVA", "C:/Users/Roxana/Desktop/An2/SEM2/PA/fisaDisciplinei.pdf", LocationType.LOCAL);
        catalog.add(book);
        book.addTag("year", 2022);
        book.addTag("authors", Arrays.asList("Cristian Frasinaru"));

        var article = new Article("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", LocationType.WEB);
        catalog.add(article);
        article.addTag("year", 2021);
        article.addTag("authors", Arrays.asList("James Gosling", "Bill Joy"));

        var resource = new Resource("cv", "CV photo", "C:/Users/Roxana/Desktop/An2/IMG_20210919_174450.jpg", LocationType.LOCAL);
        catalog.add(resource);
        CatalogUtil.save(catalog, "d:research/catalog.json");
    }

    private void testLoadListView() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("d:research/catalog.json");
//        Command command =  new LoadCommand("d:research/catalog.json");
//        command.solve();
//        Catalog catalog = command.getCatalog();
        new ViewCommand(catalog.findById("java17")).solve();
        new ListCommand(catalog).solve();
        new ViewCommand(catalog.findById("cursJava")).solve();
    }

    private void testReportInfo() throws InvalidCatalogException {
        Catalog catalog = CatalogUtil.load("d:research/catalog.json");
        new ReportCommand(catalog).solve();
        new InfoCommand(catalog).solve();
    }

    public static void main(String[] args) throws IOException {
        Main app = new Main();
        try {
            app.testCreateSave();
            app.testLoadListView();
            app.testReportInfo();
        } catch (InvalidCatalogException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            System.err.println("I/O error in method testCreateSave()");
        }
    }
}
