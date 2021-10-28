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


    String queryFindClientDeposit ="Select (c.first_name||' '||c.middle_name||' 'c.last_name) as Full_name," +
        "a.id_account, d.name,d.value,d.percent,d.currency,d.month from appAdmin.Client c " +
        "join appAdmin.Account a on c.id=a.id_client" +
        "join appAdmin.Deposit d on d.id_account=a.id" +
        "where a.id= :idAccount";

    @Query(value = queryFindClientDeposit, nativeQuery = true)
    List<Deposit> findClientDeposit(@Param("idAccount") long idAccount);


}
