package entities;

import repositories.FriendsRepository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "FRIENDS")
@NamedQueries({
        @NamedQuery(name = "Friend.findAll", query = "select f from Friend f"),
        @NamedQuery(name = "Friend.findByName", query = "select f from Friend f where upper(f.user1) = upper(?1)"),
        @NamedQuery(name = "Friend.deleteByUsername", query = "delete from Friend f where f.user1 = ?1 or f.user2 = ?1"),
        @NamedQuery(name = "Friend.updateByUsername1", query = "update Friend f set f.user1 = ?2 where f.user1 = ?1"),
        @NamedQuery(name = "Friend.updateByUsername2", query = "update Friend f set f.user2 = ?2 where f.user2 = ?1"),
        @NamedQuery(name = "Friend.findRelationship", query = "select f from Friend f where f.user1 = ?1 and f.user2 = ?2")
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

    public static void updateFriend(String username, String replacedUsername){
        FriendsRepository dataFriend = new FriendsRepository("Friend");
        List<Friend> friends = dataFriend.findAll();
        friends.stream().forEach(friend -> {
            if(friend.getUser1().equals(username)){
                friend.setUser1(replacedUsername);
            }
            if(friend.getUser2().equals(username)){
                friend.setUser2(replacedUsername);
            }
        });
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", user1='" + user1 + '\'' +
                ", user2='" + user2 + '\'' +
                '}';
    }
}