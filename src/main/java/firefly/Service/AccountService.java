/*
 * Copyright (c)
 */

package firefly.Service;


import firefly.Model.Account;
import firefly.Repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account findById(long id){
        return accountRepository.findById(id);
    }

    public List<Account> findByClientId(long id){
        return accountRepository.findByIdClient(id);
    }

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findByIdClientAndCurrency(long idClient, String currency){
        return accountRepository.findByIdClientAndCurrency(idClient, currency);
    }


}
