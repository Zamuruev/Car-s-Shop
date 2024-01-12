package CarShop.models.dtos;

import CarShop.utils.validation.brand.UniqueBrandName;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BrandDTO {

    public String id;
    public String name;
    public String image_url = "/img/logoBrand/default.png";

    public BrandDTO(String id, String name, String image_url) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
    }

    public BrandDTO() {

    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @UniqueBrandName
    @NotEmpty(message = "This field don't must be null or empty!")
    @Size(min = 2, message = "Length must be >= 2")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "BrandDTO: id = " + id +", name = " + name;
    }
}
