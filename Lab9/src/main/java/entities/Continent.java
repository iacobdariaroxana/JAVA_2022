package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = "Continent.findAll",
                query = "select e from Continent e order by e.name"),
        @NamedQuery(name = "Continent.findById",
                query = "select e from Continent e where e.id = ?1"),
        @NamedQuery(name = "Continent.findByName",
                query = "select e from Continent e where e.name like ?1")
})
public class Continent extends AbstractEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @SequenceGenerator(name="id", sequenceName="id", allocationSize=1)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public Continent(){

    }
    public Continent(String name){
        this.name = name;
    }

    public Continent(int id, String name){
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}