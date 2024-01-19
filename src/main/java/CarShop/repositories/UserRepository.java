package CarShop.repositories;

import CarShop.models.entities.User;
import CarShop.models.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByLogin(String login);

    @Transactional
    @Modifying
    @Query("update User u set u.is_active = ?2 where u.login = ?1")
    void updateOnline(String login, boolean active);

    @Query("SELECT u FROM User u ORDER BY u.is_active DESC, u.firstName ASC")
    List<User> findAllOrderByActive();

    @Transactional
    @Modifying
    void deleteAllByRole(Role role);

    List<User> findAllByRole(Role role);
}
