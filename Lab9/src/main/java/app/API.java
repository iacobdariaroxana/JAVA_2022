package app;

import com.opencsv.CSVReader;
import entities.City;
import entities.Continent;
import entities.Country;
import repositories.CityRepository;
import repositories.ContinentRepository;
import repositories.CountryRepository;
import repositories.AbstractRepository;
import singleton.MyEntityManagerFactory;

import java.io.FileReader;
import java.util.concurrent.ThreadLocalRandom;

public class API {
    public static void loadData(String file) {
        int count = 0;
        int cityPopulation;
        AbstractRepository dataContinent = new ContinentRepository("Continent");
        AbstractRepository dataCountry = new CountryRepository("Country");
        AbstractRepository dataCity = new CityRepository("City");
        try {
            FileReader filereader = new FileReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextLine;
            csvReader.readNext();
            while ((nextLine = csvReader.readNext()) != null) {
                if (dataContinent.findByName(nextLine[5]).isEmpty()) {
                    dataContinent.create(new Continent(nextLine[5]));
                }

                dataCountry.create(new Country(nextLine[0], nextLine[4], (Continent)dataContinent.findByName(nextLine[5]).get(0)));
                cityPopulation = ThreadLocalRandom.current().nextInt(1, 50);
                dataCity.create(new City(nextLine[1], (Country)dataCountry.findByName(nextLine[0]).get(0), 1, Double.parseDouble(nextLine[2]), Double.parseDouble(nextLine[3]), cityPopulation));
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        EntityManager em = MyEntityManagerFactory.getEntityManager();
//        em.getTransaction().begin();
//        Continent continent = new Continent("Central America");
//        em.persist(continent);
//        Continent c = (Continent)em.createQuery(
//                        "select e from Continent e where e.name='Europe'")
//                .getSingleResult();
//        c.setName("Europe");
        long startTime = System.currentTimeMillis();
        loadData("concap.csv");
        System.err.println("Insertion time: " + (System.currentTimeMillis() - startTime)/1000 + " seconds ");

        AbstractRepository data = new CityRepository("City");
        System.out.println("City with id = 710 is " + data.findById(710));
        System.out.println("Cities that contain 'ari' substring  are " + data.findByName("%ari%"));
        data.create(new City("Iasi"));

        data = new ContinentRepository("Continent");
        System.out.println("Continent with id = 1 is " + data.findById(1));
        System.out.println("Continents that end with 'ca' are " + data.findByName("%ca"));

        data = new CountryRepository("Country");
        System.out.println("Country with id = 123 is " + data.findById(123));
        System.out.println("Countries that start with letter 'R' are " + data.findByName("R%"));
        data.create(new Country("Transnistria"));

//        em.getTransaction().commit();
        MyEntityManagerFactory.closeEntities();
    }
}
