package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRIENDS")
@NamedQueries({
        @NamedQuery(name = "Friend.findByName", query = "select f from Friend f where upper(f.user1) = upper(?1)")
})
public class Friend extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "friends_id")
    @SequenceGenerator(name="friends_id", sequenceName="friends_id", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USER1", length = 20)
    private String user1;

    @Column(name = "USER2", length = 20)
    private String user2;

    public Friend() {
    }

    public Friend(String user1, String user2) {
        this.user1 = user1;
        this.user2 = user2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

}