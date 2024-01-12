package CarShop.models.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Offer extends Base {

    private List<Model> models = new ArrayList<>();
    private User seller;

    public Offer(String id, String image_url, List<Model> models, User seller) {
        super(id, image_url);
        this.models = models;
        this.seller = seller;
    }

    public Offer(String image_url, User seller, LocalDateTime created, LocalDateTime modified) {
        super(image_url);
        this.seller = seller;
        this.created = created;
        this.modified = modified;
    }

    public Offer() {}

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    public List<Model> getModels() {
        return models;
    }
    public void setModels(List<Model> models) {
        this.models = models;
    }

    @ManyToOne
    public User getSeller() {
        return seller;
    }
    public void setSeller(User seller) {
        this.seller = seller;
    }
}
