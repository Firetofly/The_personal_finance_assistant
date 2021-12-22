/*
 * Copyright (c)
 */

package firefly.Service;


import firefly.DTO.ClientAccountDTO;
import firefly.Model.Account;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Account findById(long id){
        return accountRepository.findById(id);
    }

    public List<Account> findByClientId(long id){
        return accountRepository.findByIdClient(id);
    }

    //default create method in DB
    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findByIdClientAndCurrency(long idClient, String currency){
        return accountRepository.findByIdClientAndCurrency(idClient, currency);
    }

    //Customize create method by DTO
    public void addNewAccount(ClientAccountDTO clientAccountDTO){
        Account newAcc = new Account();
        newAcc.setSum(0);
        newAcc.setCurrency(clientAccountDTO.getCurrency());
        newAcc.setIdClient(clientRepository.findByLogin(clientAccountDTO.getClientLogin()).getId());
        createAccount(newAcc);
    }




}
