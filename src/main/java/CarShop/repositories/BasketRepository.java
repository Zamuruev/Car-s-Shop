package CarShop.repositories;

import CarShop.models.entities.Basket;
import CarShop.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {
    Basket findByUserId(String id);

    @Transactional
    @Modifying
    public void deleteByUser(User user);

    @Transactional
    @Modifying
    public void deleteByUserId(String id);
}
