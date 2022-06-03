package app;

import cartography.WorldFrame;
import com.opencsv.CSVReader;
import database.CityDAO;
import database.ContinentDAO;
import database.CountryDAO;
import database.Database;
import model.City;
import model.Continent;
import model.Country;

import java.io.FileReader;

public class Main {
    public static double distance(City start, City end) {
        double lonStart = Math.toRadians(start.getLongitude());
        double lonEnd = Math.toRadians(end.getLongitude());
        double latStart = Math.toRadians(start.getLatitude());
        double latEnd = Math.toRadians(end.getLatitude());

        double distanceLon = lonEnd - lonStart;
        double distanceLat = latEnd - latStart;
        double a = Math.pow(Math.sin(distanceLat / 2), 2)
                + Math.cos(latStart) * Math.cos(latEnd)
                * Math.pow(Math.sin(distanceLon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
        return c * r;
    }

    public static void loadData(String file, ContinentDAO continents, CountryDAO countries, CityDAO cities) {
        int count = 0;
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextLine;
            csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                if (continents.getByName(nextLine[5]) == null) {
                    continents.create(new Continent(count, nextLine[5]));
                }
                countries.create(new Country(100 + count, nextLine[0], nextLine[4], continents.getByName(nextLine[5]).getId()));
                cities.create(new City(500 + count, nextLine[1], countries.getByName(nextLine[0]).getId(), 1, Double.parseDouble(nextLine[2]), Double.parseDouble(nextLine[3])));
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Database.getConnection();
        Database.runScript();
        var continents = new ContinentDAO();
        var countries = new CountryDAO();
        var cities = new CityDAO();
        /*
        continents.create(new Continent(1, "Europe", 10_180_000, 746_419_440));
        continents.create(new Continent(2, "Asia"));
        continents.create(new Continent(3, "North America"));
        continents.create(new Continent(4, "South America", 17_840_000, 423_581_078));
        continents.create(new Continent(5, "Africa"));
        continents.create(new Continent(6, "Oceania"));
        continents.create(new Continent(7, "Antarctica", 13_209_000, 0));

        Continent continent = continents.getByName("Europe");
        System.out.println(continent);

        continent = continents.getById(6);
        System.out.println(continent);

        System.out.println(continents.getAll());

        continents.deleteEntry(6);
        System.out.println(continents.getAll());

        continents.updateEntry(new Continent(3, "Russia", 10_180_000, 1));

        System.out.println(continents.getById(1));

        countries.create(new Country(1, "Romania", "RO", continents.getByName("Europe").getId()));
        countries.create(new Country(2, "Spain", "ES", continents.getByName("Europe").getId()));
        countries.create(new Country(3, "Algeria", "DZ", continents.getByName("Africa").getId()));

        System.out.println(countries.getByName("Romania"));
        System.out.println(countries.getById(1));

        System.out.println(countries.getAll());

        countries.deleteEntry(2);
        System.out.println(countries.getAll());

        countries.updateEntry(new Country(3, "Argentina", "AR", continents.getByName("South America").getId()));
        System.out.println(countries.getByName("Argentina"));

        cities.create(new City(1, "Iasi", countries.getByName("Romania").getId(), 0, 47.15, 27.58));
        System.out.println(cities.getByName("Iasi"));*/
        loadData("concap.csv", continents, countries, cities);
        System.out.println(continents.getAll());
        System.out.println(countries.getAll());
        System.out.println(cities.getAll());

        System.out.println("Distance between Bucharest and Moscow is: " + distance(cities.getByName("Bucharest"), cities.getByName("Moscow")) + " km ");
        System.out.println("Distance between Cairo and Moscow is: " + distance(cities.getByName("Cairo"), cities.getByName("Moscow")) + " km ");

        new WorldFrame(cities.getAll()).setVisible(true);

        Database.closeConnection();
    }
}
