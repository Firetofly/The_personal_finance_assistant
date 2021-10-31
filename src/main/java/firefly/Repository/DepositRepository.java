/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Deposit;
import firefly.View.ClientDepositView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit,Long> {
    Deposit findById(long id);

    List<Deposit> findByAccountId(long accountId);

    List<Deposit> findByName(String name);

    Deposit save(Deposit deposit);


    String queryFindClientDeposit ="Select (c.first_name||' '||c.middle_name||' 'c.last_name) as FullName," +
        "a.id_account as idAccount, d.name as DepositName,d.value,d.percent,d.currency,d.month from app_admin.Client c " +
        "join app_admin.Account a on c.id=a.id_client" +
        "join app_admin.Deposit d on d.id_account=a.id" +
        "where c.id= :id";
    @Query(value = queryFindClientDeposit, nativeQuery = true)
    List<ClientDepositView> findClientDeposit(@Param("id") long id);

}
