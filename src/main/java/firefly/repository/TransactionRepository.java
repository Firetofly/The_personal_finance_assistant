/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction findById(long id);

    List<Transaction> findByIdAccount(long idAccount);

    List<Transaction> findByIdCategory(long idCategory);

    List<Transaction> findByIncomeDate(LocalDateTime incomeDate);
}
