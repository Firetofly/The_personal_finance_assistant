/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Category;
import firefly.Model.Transaction;
import firefly.View.ClientDepositView;
import firefly.View.TransactionCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Transaction findById(long id);

    List<Transaction> findByIdAccount(long idAccount);

    List<Transaction> findByIdCategory(long idCategory);

    List<Transaction> findByIncomeDate(LocalDateTime incomeDate);

    Transaction save(Transaction transaction);

    String queryTransactions = "select ct.display_name as \"NameOfTransaction\" ," +
        "count(ct.display_name) as \"NumberOfTransactions\",tr.income_date \n" +
        "as \"IncomeDate\",sum(\"value\") as \"Sum\", tr.currency as \"Currency\" " +
        "from  app_admin.\"Transaction\" tr \n" +
        "join app_admin.\"Category\" ct on tr.id_category=ct.id\n" +
        "join app_admin.\"Account\" ac on tr.id_account=ac.id\n" +
        "where tr.income_date>= :incomeDateFrom and tr.income_date <= :incomeDateTo and ac.id_client= :idClient\n" +
        "group by ct.display_name,tr.income_date,tr.currency,ac.id,ac.id_client\n" +
        "order by tr.currency asc;";
    @Query(value = queryTransactions, nativeQuery = true)
    List<TransactionCategoryView> queryTransactions(@Param("idClient") long idClient,
                                                    @Param("incomeDateFrom") LocalDateTime incomeDateFrom,
                                                    @Param("incomeDateTo") LocalDateTime incomeDateTo);

    String querySetAccountValue = "update app_admin.Account acc" +
        "set acc.value = accValue" +
        "where acc.id_client =: idClient and acc.currency =: accCurrency and accValue =: accValue";
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = querySetAccountValue, nativeQuery = true)
    public void SetAccountValue(@Param("idClient") long idClient,
                                @Param("currency") String currency,
                                @Param("accValue") double accValue);

    /*String queryGetTransactions="select ct.display_name as \"NameOfTransaction\" ,\n" +
        "        count(ct.display_name) as \"NumberOfTransactions\",\n" +
        "\t\ttr.income_date as \"IncomeDate\",\n" +
        "\t\tsum(\"value\") as \"Sum\",\n" +
        "\t\ttr.currency as \"Currency\"\n" +
        "        from  app_admin.\"Transaction\" tr \n" +
        "        join app_admin.\"Category\" ct on tr.id_category=ct.id\n" +
        "        join app_admin.\"Account\" ac on tr.id_account=ac.id\n" +
        "        where tr.income_date>= :incomeDateFrom and tr.income_date <= :incomeDateTo and ac.id_client= :idClient" +
        "        and ct.display_name = : nameCategory\n" +
        "        group by ct.display_name,tr.income_date,tr.currency,ac.id,ac.id_client\n" +
        "        order by tr.currency asc;";
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = queryGetTransactions, nativeQuery = true)
    public List<Transaction> getTransactionsByCategory(@Param("idClient") long idClient,
                                                    @Param("incomeDateFrom") LocalDateTime incomeDateFrom,
                                                    @Param("incomeDateTo") LocalDateTime incomeDateTo,
                                                    @Param("nameCategory") String nameCategory);
*/
}
