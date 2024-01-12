package CarShop.repositories;


import CarShop.models.entities.Basket;
import CarShop.models.entities.Model;
import CarShop.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ModelRepository extends JpaRepository<Model, String> {

    List<Model> findAllByActive(String active);

    List<Model> findAllByBasketId(String basket_id);

    List<Model> findAllByOfferId(String id);

    @Transactional
    @Modifying
    void deleteAllByActive(String active);

    @Transactional
    @Modifying
    @Query("update Model m set m.active = ?1, m.modified = ?3, m.basket = ?4 where m.id = ?2")
    void updateActiveById(String active, String id, LocalDateTime now, Basket basket);


    @Transactional
    @Modifying
    @Query("update Model m set m.active = 'Bought', m.modified = ?1, m.basket = null, m.offer = ?2 where m.basket.id = ?3")
    void updateAllByBasketId(LocalDateTime localDateTime, Offer offer, String basket_id);

}
