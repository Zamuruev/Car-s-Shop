package CarShop.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Base {

    protected String id;
    protected LocalDateTime created;
    protected LocalDateTime modified;
    protected String image_url;
    protected String description;

    protected Base(String id, String image_url) {
        this.id = id;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.image_url = image_url;
    }

    protected Base(String image_url) {
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.image_url = image_url;
    }

    protected Base() {}

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    public String getId() {
        return id;
    }
    protected void setId(String id) {
        this.id = id;
    }

    @Column(name = "created")
    protected LocalDateTime getCreated() {
        return created;
    }
    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Column(name = "modified")
    protected LocalDateTime getModified() {
        return modified;
    }
    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Column(name = "image_url")
    protected String getImage_url() {
        return image_url;
    }
    protected void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Column(name = "description", length = 800)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
