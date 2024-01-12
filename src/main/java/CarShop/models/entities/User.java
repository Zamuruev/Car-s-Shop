package CarShop.models.entities;

import CarShop.models.enums.Role;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User extends Base {

    private String firstName;
    private String userName;
    private String lastName;
    private String password;
    private String login;
    private boolean is_active;
    private Role role;
    private List<Offer> offer;

    public User(String id, String image_url, String firstName, String userName,
                String lastName, String password, String login, boolean is_active, Role role, List<Offer> offer) {
        super(id, image_url);
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.is_active = is_active;
        this.role = role;
        this.offer = offer;
    }

    public User(String firstName, String userName, String lastName, String password,
                String login, boolean is_active, Role role, List<Offer> offer) {
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.is_active = is_active;
        this.role = role;
        this.offer = offer;
    }

    public User() {}

    @Column(name = "firstName", nullable = false)
    public String getFirstName() {
        return firstName;
    }
    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "userName", nullable = false)
    public String getUserName() {
        return userName;
    }
    protected void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }
    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "image_url")
    public String getImage_url() {
        return image_url;
    }
    protected void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() { return password; }
    protected void setPassword(String password) { this.password = password; }

    @Column(name = "is_active")
    public boolean isIs_active() { return is_active; }
    protected void setIs_active(boolean is_active) { this.is_active = is_active; }

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    public List<Offer> getOffer() { return offer; }
    protected void setOffer(List<Offer> offer) { this.offer = offer; }

    @Column(name = "login", nullable = false, unique = true)
    public String getLogin() {
        return login;
    }
    protected void setLogin(String login) {
        this.login = login;
    }

}
