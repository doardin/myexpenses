package br.com.doardin.myexpenses.domain.transaction;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.doardin.myexpenses.domain.user.User;
import br.com.doardin.myexpenses.enums.CategoryTypes;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT t FROM Transaction t JOIN FETCH t.category WHERE t.user = :user", countQuery = "SELECT COUNT(t) FROM Transaction t JOIN t.category WHERE t.user = :user")
    Page<Transaction> findAllByUser(User user, Pageable pageable);

    @Query(value = "SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t JOIN t.category c ON c.type = :categoryType WHERE t.user = :user")
    BigDecimal sumTransactionsByCategoryType(User user, CategoryTypes categoryType);

}
