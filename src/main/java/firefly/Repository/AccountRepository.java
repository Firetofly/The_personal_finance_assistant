/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Account;
import firefly.View.ClientAccountView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findById(long id);

    List<Account> findByIdClient(long idClient);

    Account findByIdClientAndCurrency(long idClient, String currency);

    String queryClientAccounts ="select cl.first_name||' '||cl.middle_name||' '||cl.last_name as Full name" +
        ",acc.id as accountId, cl.login, acc.currency,acc.sum as depositOfAccount" +
        "from app_admin.Account acc join app_admin.Client cl on acc.id_client=cl.id" +
        "where cl.id= :clientId" +
        "order by cl.id";
    @Query(value = queryClientAccounts, nativeQuery = true)
    List<ClientAccountView> findClientAccounts(@Param("clientId") long id);

    List<Account> findAll();

    Account save(Account account);

}
