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

    Credit findByNameAndAccountId(String name, long accountId);

    String querySetMonthlyPayment = "Update appAdmin.Credit c" +
        "set c.monthly_payment= :monthly_payment" +
        "where c.id= :idCredit";
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = querySetMonthlyPayment, nativeQuery = true)
    void SetMonthlyPayment(@Param("idCredit") long id,
                           @Param("monthly_payment") double monthlyPayment);

    @NotNull Credit save(Credit credit);

    String queryFindAllClientCredits="Select (cl.first_name||' '||cl.middle_name||' '||cl.last_name) as UserFullName,\n" +
        "        a.id as Account_id, cr.name as CreditName,cr.value,cr.percent,cr.currency,\n" +
        "        cr.monthly_payment, cr.last_date as LastDate, cr.active from client cl \n" +
        "        join account a on cl.id=a.id_client\n" +
        "        join credit cr on cr.account_id=a.id\n" +
        "        where cl.id= :id";
    @Query(value = queryFindAllClientCredits, nativeQuery = true)
    List<ClientCreditView> findAllClientCredits(@Param("id") long id);

    String queryFindWithActiveClientCredits="Select (cl.first_name||' '||cl.middle_name||' '||cl.last_name) as UserFullName, \n" +
        "        a.id as Account_id, cr.name as CreditName,cr.value,cr.percent,cr.currency, \n" +
        "        cr.monthly_payment, cr.last_date as LastDate, cr.active from client cl \n" +
        "        join account a on cl.id=a.id_client \n" +
        "        join credit cr on cr.account_id=a.id \n" +
        "        where cl.id= :id and cr.active= :active";
    @Query(value = queryFindWithActiveClientCredits, nativeQuery = true)
    List<ClientCreditView> findWithActiveClientCredits(@Param("id") long id, @Param("active") boolean active);



}
