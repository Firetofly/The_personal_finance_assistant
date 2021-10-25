/*
 * Copyright (c)
 */

package firefly.Service;


import firefly.model.Account;
import firefly.repository.AccountRepository;
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


}
