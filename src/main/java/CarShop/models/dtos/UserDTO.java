package CarShop.models.dtos;

import CarShop.utils.validation.user.UniqueUserLogin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {

    public String id;
    public String firstName;
    public String userName;
    public String lastName;
    public String password;
    public String login;
    public boolean is_active;
    public String role;
    public List<String> offer;
    public String image_url = "/img/defaultUserPhoto.png";
    public String description;
    public String basket;

    public UserDTO(String id, String firstName, String userName, String lastName, String password,
                   String login, boolean is_active, String role, List<String> offer, String image_url,
                   String description, String basket) {
        this.id = id;
        this.firstName = firstName;
        this.userName = userName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.is_active = is_active;
        this.role = role;
        this.offer = offer;
        this.image_url = image_url;
        this.description = description;
        this.basket = basket;
    }
    public UserDTO() {}

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @NotEmpty(message = "This field don't must be null or empty!")
    @Size(min = 4, message = "Length must be >= 4")
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotEmpty(message = "This field don't must be null or empty!")
    @Size(min = 2, message = "Length must be >= 2")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty(message = "This field don't must be null or empty!")
    @Size(min = 4, message = "Length must be >= 4")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @UniqueUserLogin
    @NotEmpty(message = "This field don't must be null or empty!")
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isIs_active() {
        return is_active;
    }
    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public List<String> getOffer() {
        return offer;
    }
    public void setOffer(List<String> offer) {
        this.offer = offer;
    }

    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getBasket() {
        return basket;
    }
    public void setBasket(String basket) {
        this.basket = basket;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id = '" + id + '\'' +
                ", firstName = '" + firstName + '\'' +
                ", userName = '" + userName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", password = '" + password + '\'' +
                ", login = '" + login + '\'' +
                ", is_active = " + is_active +
                ", role = '" + role + '\'' +
                ", offer = " + offer +
                ", image_url = '" + image_url + '\'' +
                ", description = '" + description + '\'' +
                ", basket = '" + basket + '\'' +
                '}';
    }
}
