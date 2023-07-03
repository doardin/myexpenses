package br.com.doardin.myexpenses.domain.paymentmethod;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doardin.myexpenses.domain.user.User;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, String> {

    @Query(value = "SELECT p FROM PaymentMethod p WHERE p.user = :user", countQuery = "SELECT COUNT(p) FROM PaymentMethod p WHERE p.user = :user")
    Page<PaymentMethod> findAllByUser(User user, Pageable pageable);

}
