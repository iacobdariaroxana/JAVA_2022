package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@NamedQueries({
        @NamedQuery(name = "City.findAll",
                query = "select e from City e order by e.name"),
        @NamedQuery(name = "City.findById",
                query = "select e from City e where e.id = ?1"),
        @NamedQuery(name = "City.findByName",
                query = "select e from City e where e.name like ?1")
})
public class City extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "city_id")
    @SequenceGenerator(name = "city_id", sequenceName = "city_id", allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "country", referencedColumnName = "id")
    @ManyToOne
    private Country country; // country id
    @Column(name = "capital")
    private int capital;
    @Column(name = "latitude")
    private double latitude;
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "population")
    private int population;

    public City() {

    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, Country country, int capital, double latitude, double longitude, int population) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public City(int id, String name, Country country, int capital, double latitude, double longitude, int population) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapital() {
        return capital;
    }

    public void setCapital(int capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    public int getPopulation() {
        return this.population;
    }
}
