package kaiser0.com.myspringboot.repository;

import kaiser0.com.myspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    // Aktif (silinmemiş) kullanıcıları bul
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.deleted = false")
    Optional<User> findByUsername(@Param("username") String username);

    // Silinmiş kullanıcılar dahil kullanıcı bul
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsernameIncludingDeleted(@Param("username") String username);

    // Tüm aktif kullanıcıları getir
    @Query("SELECT u FROM User u WHERE u.deleted = false")
    List<User> findAllActiveUsers();

    // Tüm silinmiş kullanıcıları getir
    @Query("SELECT u FROM User u WHERE u.deleted = true")
    List<User> findAllDeletedUsers();

    // Kullanıcıyı ID ile bul (soft delete dahil)
    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdIncludingDeleted(@Param("id") Long id);

    // Kullanıcıyı ID ile bul (sadece aktif)
    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deleted = false")
    Optional<User> findActiveById(@Param("id") Long id);
}