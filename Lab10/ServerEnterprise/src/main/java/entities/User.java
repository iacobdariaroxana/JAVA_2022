package entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "select u from User u"),
        @NamedQuery(name = "User.getMessages", query = "select u from User u where u.username = ?1"),
        @NamedQuery(name = "User.findByName", query = "select u from User u where upper(u.username) = upper(?1)"),
        @NamedQuery(name = "User.updateMessages", query = "update User u set u.messages = ?1 where upper(u.username) = upper(?2)")
})
public class User extends AbstractEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id")
    @SequenceGenerator(name="user_id", sequenceName="user_id", allocationSize=1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "USERNAME", nullable = false, length = 20)
    private String username;

    @Column(name = "MESSAGES", length = 300)
    private String messages;

    public User(String username) {
        this.username = username;
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

}