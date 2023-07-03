package br.com.doardin.myexpenses.domain.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doardin.myexpenses.domain.user.User;

public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query(value = "SELECT c FROM Category c WHERE c.user = :user", countQuery = "SELECT COUNT(c) FROM Category c WHERE c.user = :user")
    Page<Category> findAllByUser(User user, Pageable pageable);

}
