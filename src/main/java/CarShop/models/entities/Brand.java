package CarShop.models.entities;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brand")
public class Brand extends Base {

    private String name;
    private List<Model> model;

    public Brand(String id, String image_url, String name) {
        super(id, image_url);
        this.name = name;
    }

    protected Brand() {}

    @Column(name = "name", unique = true, nullable = false)
    protected String getName() { return name; }
    protected void setName(String name) { this.name = name; }

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    protected List<Model> getModel() { return model; }
    protected void setModel(List<Model> model) { this.model = model; }

    @Override
    public String toString() {
        return "Brand: id = " + id + ", name = " + name + ", created = " + created.toString() + ", modified = " + modified.toString();
    }
}