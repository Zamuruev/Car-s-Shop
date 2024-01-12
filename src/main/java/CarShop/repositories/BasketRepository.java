package CarShop.repositories;

import CarShop.models.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {
    Basket findByUserId(String id);
}
