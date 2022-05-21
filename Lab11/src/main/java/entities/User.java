package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u"),
        @NamedQuery(name = "User.getMessages", query = "select u from User u where u.username = ?1"),
        @NamedQuery(name = "User.findByName", query = "select u from User u where upper(u.username) = upper(?1)"),
        @NamedQuery(name = "User.findById", query = "select u from User u where u.id = ?1"),
        @NamedQuery(name = "User.updateMessages", query = "update User u set u.messages = ?1 where upper(u.username) = upper(?2)"),
        @NamedQuery(name = "User.updateByUsername", query = "update User set username = ?2 where username = ?1"),
        @NamedQuery(name = "User.delete", query = "delete from User u where u.id = ?1")
})
public class User extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
    @SequenceGenerator(name = "user_id", sequenceName = "user_id", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;

    @Column(name = "MESSAGES", length = 300)
    private String messages;

    public User(String username) {
        this.username = username;
    }

    public User(Long id, String username, String messages) {
        this.id = id;
        this.username = username;
        this.messages = messages;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{ " + username + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}