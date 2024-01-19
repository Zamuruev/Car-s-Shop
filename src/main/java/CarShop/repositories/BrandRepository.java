package CarShop.repositories;

import CarShop.models.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand,String> {

    Optional<Brand> findBrandByName(String name);

    @Transactional
    @Modifying
    @Query("update Brand b set b.top = ?1 where b.id = ?2")
    void updateByTop(int top, String id);

    @Modifying
    @Transactional
    void deleteByName(String name);

    @Query("SELECT b FROM Brand b order by b.top desc limit 3")
    List<Brand> findTop3ByOrderByTopDesc();

    @Transactional
    @Modifying
    @Query("update Brand b set b.name = ?1, b.modified = ?2 where b.id = ?3")
    public void updateBrand(String name, LocalDateTime now, String id);

}
