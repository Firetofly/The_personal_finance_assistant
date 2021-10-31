/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Transaction;
import firefly.View.ClientDepositView;
import firefly.View.TransactionCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    Transaction findById(long id);

    List<Transaction> findByIdAccount(long idAccount);

    List<Transaction> findByIdCategory(long idCategory);

    List<Transaction> findByIncomeDate(LocalDateTime incomeDate);

    Transaction save(Transaction transaction);

    String queryTransactionForTheLastMonth="select distinct ct.display_name as  \"NameOfTransaction\" ," +
        "count(ct.display_name) as \"NumberOfTransactions\",tr.income_date \n" +
        "as \"IncomeDate\",sum(\"value\") as \"Sum\", tr.currency as \"Currency\" " +
        "from  app_admin.\"Transaction\" tr \n" +
        "join app_admin.\"Category\" ct on tr.id_category=ct.id\n" +
        "join app_admin.\"Account\" ac on tr.id_account=ac.id\n" +
        "where extract(MONTH FROM tr.income_date) = extract(MONTH FROM now()) and ac.id_client= :idClient\n" +
        "group by ct.display_name,tr.income_date,tr.currency,ac.id,ac.id_client\n" +
        "order by ac.id asc;";
    @Query(value = queryTransactionForTheLastMonth, nativeQuery = true)
    List<TransactionCategoryView> queryTransactionForTheLastMonth(@Param("idClient") long idClient);

    String queryTransactionForTheLastYear="select distinct ct.display_name as  \"NameOfTransaction\" ," +
        "count(ct.display_name) as \"NumberOfTransactions\",tr.income_date \n" +
        "as \"IncomeDate\",sum(\"value\") as \"Sum\", tr.currency as \"Currency\" " +
        "from  app_admin.\"Transaction\" tr \n" +
        "join app_admin.\"Category\" ct on tr.id_category=ct.id\n" +
        "join app_admin.\"Account\" ac on tr.id_account=ac.id\n" +
        "where extract(YEAR FROM tr.income_date) = extract(YEAR FROM now()) and ac.id_client= :idClient\n" +
        "group by ct.display_name,tr.income_date,tr.currency,ac.id,ac.id_client\n" +
        "order by ac.id asc;";
    @Query(value = queryTransactionForTheLastYear, nativeQuery = true)
    List<TransactionCategoryView> queryTransactionForTheLastYear(@Param("idClient") long idClient);

    String queryTransactionForTheInputDate="select distinct ct.display_name as  \"NameOfTransaction\" ," +
        "count(ct.display_name) as \"NumberOfTransactions\",tr.income_date \n" +
        "as \"IncomeDate\",sum(\"value\") as \"Sum\", tr.currency as \"Currency\"" +
        " from  app_admin.\"Transaction\" tr \n" +
        "join app_admin.\"Category\" ct on tr.id_category=ct.id\n" +
        "join app_admin.\"Account\" ac on tr.id_account=ac.id\n" +
        "where tr.income_date= :incomeDate and ac.id_client= :idClient\n" +
        "group by ct.display_name,tr.income_date,tr.currency,ac.id,ac.id_client\n" +
        "order by ac.id asc;";
    @Query(value = queryTransactionForTheInputDate, nativeQuery = true)
    List<TransactionCategoryView> queryTransactionForTheInputDate(@Param("idClient") long idClient,
                                                            @Param("incomeDate")LocalDateTime inputDate);



}
