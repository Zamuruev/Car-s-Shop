package CarShop.repositories;

import CarShop.models.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
    List<Offer> findAllBySellerId(String id);

    Optional<Offer> findById(String id);
}
