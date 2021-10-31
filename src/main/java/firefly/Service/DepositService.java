/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Deposit;
import firefly.Repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositService {

    @Autowired
    DepositRepository depositRepository;

    public List<Deposit> findByAccountId(long accountId){
        return depositRepository.findByAccountId(accountId);
    }

    public List<Deposit> findByName(String name){
        return depositRepository.findByName(name);
    }

    public void createDeposit(Deposit deposit){
        depositRepository.save(deposit);
    }
}
