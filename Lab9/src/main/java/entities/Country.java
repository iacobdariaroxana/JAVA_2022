package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = "Country.findAll",
                query = "select e from Country e order by e.name"),
        @NamedQuery(name = "Country.findById",
                query = "select e from Country e where e.id = ?1"),
        @NamedQuery(name = "Country.findByName",
                query = "select e from Country e where e.name like ?1")
})

public class Country extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "country_id")
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @JoinColumn(name = "continent", referencedColumnName = "id")
    @ManyToOne
    private Continent continent; // continent id

    public Country() {

    }

    public Country(String name) {
        this.name = name;
    }

    public Country(String name, String code, Continent continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
    }
    public Country(int id, String name, String code, Continent continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}
