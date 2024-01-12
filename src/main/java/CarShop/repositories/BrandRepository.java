package CarShop.repositories;

import CarShop.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,String> {

    Optional<Brand> findBrandByName(String name);

    @Modifying
    @Transactional
    void deleteByName(String name);
}
