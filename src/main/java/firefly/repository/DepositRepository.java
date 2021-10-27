/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit,Long> {
    Deposit findById(long id);

    List<Deposit> findByAccountId(long accountId);

    List<Deposit> findByName(String name);

    Deposit save(Deposit deposit);

    String querySetMonthlyPayment = "Update appAdmin.Credit c" +
        "set c.monthly_payment= :monthly_payment" +
        "where c.id= :id";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = querySetMonthlyPayment, nativeQuery = true)
    void setMonthlyPayment(@Param("id") long id,
                           @Param("monthly_payment") long monthlyPayment);

}
