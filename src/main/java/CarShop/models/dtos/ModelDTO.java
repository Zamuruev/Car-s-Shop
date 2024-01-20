package CarShop.models.dtos;

import CarShop.models.enums.Category;
import CarShop.models.enums.Engine;
import CarShop.models.enums.Transmission;
import jakarta.validation.constraints.*;

public class ModelDTO {

    public String id;
    public String name;
    public String image_url = "/img/models/defaultModel.png";
    public BrandDTO brand;
    public Category category;
    public String offer;
    public String basket;
    public double price;
    public Engine engine;
    public Transmission transmission;
    public String active = "In Basket";
    public int mileage;

    public ModelDTO(String id, String name, String image_url, BrandDTO brand, Category category,
                    String offer, double price, Engine engine, Transmission transmission, String active, int mileage, String basket) {
        this.id = id;
        this.name = name;
        this.image_url = image_url;
        this.brand = brand;
        this.category = category;
        this.offer = offer;
        this.price = price;
        this.engine = engine;
        this.transmission = transmission;
        this.active = active;
        this.mileage = mileage;
        this.basket = basket;
    }

    public ModelDTO() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty(message = "This field don't must be null or empty!")
    @Size(min = 4, message = "Length must be >= 4")
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

    public BrandDTO getBrand() {
        return brand;
    }
    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public String getOffer() {
        return offer;
    }
    public void setOffer(String offer) {
        this.offer = offer;
    }

    @DecimalMin(value = "10000.0", message = "Price >= 10000.0 $")
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getBasket() {
        return basket;
    }
    public void setBasket(String basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "ModelDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image_url='" + image_url + '\'' +
                ", brand=" + brand +
                ", category=" + category +
                ", offer='" + offer + '\'' +
                ", basket='" + basket + '\'' +
                ", price=" + price +
                ", engine=" + engine +
                ", transmission=" + transmission +
                ", active='" + active + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
