/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Credit;
import firefly.View.ClientCreditView;
import firefly.View.ClientDepositView;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {

    Credit findById(long id);

    List<Credit> findByAccountId(long accountId);

    Credit findByName(String name);

    String querySetMonthlyPayment = "Update appAdmin.Credit c" +
        "set c.monthly_payment= :monthly_payment" +
        "where c.id= :id";
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = querySetMonthlyPayment, nativeQuery = true)
    void setMonthlyPayment(@Param("id") long id,
                           @Param("monthly_payment") double monthlyPayment);

    @NotNull Credit save(Credit credit);

    String queryFindClientCredit="Select (cl.first_name||' '||cl.middle_name||' 'cl.last_name) as FullName," +
        "a.id_account as idAccount, cr.name as CreditName,cr.value,cr.percent,cr.currency,cr.month," +
        "cr.monthlyPayment from app_admin.Client cl " +
        "join app_admin.Account a on c.id=a.id_client" +
        "join app_admin.Credit cr on cr.id_account=a.id" +
        "where cl.id= :id";
    @Query(value = queryFindClientCredit, nativeQuery = true)
    List<ClientCreditView> findClientCredit(@Param("id") long id);


}
