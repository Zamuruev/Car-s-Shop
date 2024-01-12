package CarShop.models.entities;

import CarShop.models.enums.Category;
import CarShop.models.enums.Engine;
import CarShop.models.enums.Transmission;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Model extends Base {

    private int mileage;
    private String name;
    private Brand brand;
    private Category category;
    private double price;
    private Offer offer;
    private Engine engine;
    private Transmission transmission;
    private String active = "In Basket";
    private Basket basket;

    public Model(String id, String image_url,
                 String name, Brand brand, Category category, Offer offer,
                 double price, Engine engine, Transmission transmission, Basket basket) {
        super(id, image_url);
        this.name = name;
        this.brand = brand;
        this.category = category;
        this.offer = offer;
        this.active = "In Basket";
        this.price = price;
        this.engine = engine;
        this.transmission = transmission;
        this.basket = basket;
    }

    protected Model() {}

    @Column(name = "name", nullable = false)
    protected String getName() { return name; }
    protected void setName(String name) { this.name = name; }

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    protected Category getCategory() {
        return category;
    }
    protected void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "transmission", nullable = false)
    @Enumerated(EnumType.STRING)
    public Transmission getTransmission() {
        return transmission;
    }
    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Column(name = "engine", nullable = false)
    @Enumerated(EnumType.STRING)
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @ManyToOne
    @JoinColumn(name = "offer_id")
    protected Offer getOffer() {
        return offer;
    }
    protected void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Column(name = "active")
    public String getActive() {
        return active;
    }
    public void setActive(String active) {
        this.active = active;
    }

    @Column(name = "price", nullable = false)
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "mileage")
    public int getMileage() {
        return mileage;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @ManyToOne
    @JoinColumn(name = "basket_id")
    public Basket getBasket() {
        return basket;
    }
    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", brand =" + brand.getName() +
                ", category=" + category +
                ", price=" + price +
                ", engine=" + engine +
                ", transmission=" + transmission +
                ", active='" + active + '\'' +
                ", id='" + id + '\'' +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
