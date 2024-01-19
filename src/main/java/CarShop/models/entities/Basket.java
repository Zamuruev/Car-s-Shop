package CarShop.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    private String id;
    private User user;
    private List<Model> models = new ArrayList<>();

    public Basket(String id, User user, List<Model> models) {
        this.id = id;
        this.user = user;
        this.models = models;
    }

    public Basket() {}

    public Basket(User user) { this.user = user;}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "basket")
    public List<Model> getModels() {
        return models;
    }
    public void setModels(List<Model> models) {
        this.models = models;
    }
}
