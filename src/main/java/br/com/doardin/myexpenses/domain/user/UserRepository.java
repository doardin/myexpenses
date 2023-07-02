package br.com.doardin.myexpenses.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, String> {

    @Query("SELECT COUNT(u.id) > 0 FROM User u WHERE u.email = :email")
    boolean existsByEmail(String email);

}
