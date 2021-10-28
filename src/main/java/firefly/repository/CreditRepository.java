/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findById(long id);

    List<Credit> findByAccountId(long accountId);

    List<Credit> findByName(String name);

    String querySetMonthlyPayment = "Update appAdmin.Credit c" +
        "set c.monthly_payment= :monthly_payment" +
        "where c.id= :id";

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = querySetMonthlyPayment, nativeQuery = true)
    void setMonthlyPayment(@Param("id") long id,
                           @Param("monthly_payment") double monthlyPayment);

    Credit save(Credit credit);

}
