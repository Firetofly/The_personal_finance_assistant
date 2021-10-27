/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findById(long id);

    List<Account> findByIdClient(long idClient);

    String queryClientAccounts ="select cl.first_name||' '||cl.middle_name||' '||cl.last_name as Full name" +
        ",acc.id as Account id, cl.login, acc.currency,acc.sum as Deposit of account" +
        "from app_admin.Account acc join app_admin.Client cl" +
        "on acc.id_client=cl.id" +
        "order by cl.id";
    @Query(value = queryClientAccounts, nativeQuery = true)

    List<Account> findAll();

    Account save(Account account);

}
