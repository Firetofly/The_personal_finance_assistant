/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Exceptions.ResourceNotFoundException;
import firefly.Model.Account;
import firefly.Model.Client;
import firefly.Model.Deposit;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.Repository.DepositRepository;
import lombok.val;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepositService {

    private static final Logger logger = Logger.getLogger(DepositService.class);

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    public List<Deposit> getByAccountId(long accountId) {
        return depositRepository.findByAccountId(accountId);
    }

    public Deposit getByNameAndAccountId(String name, long accountId) {
        return depositRepository.findByNameAndAccountId(name, accountId);
    }

    public void createDeposit(Deposit deposit) {
        deposit.setDate(LocalDateTime.now());
        depositRepository.save(deposit);
    }


    public ResponseEntity<Deposit> addDepositIncome(Client client,
                                                    String depositName,
                                                    double value,
                                                    String currency) throws ResourceNotFoundException {

        Account tmpAcc = accountRepository.findByIdClientAndCurrency(client.getId(), currency);
        Deposit tmpDep = depositRepository.findByNameAndAccountId(depositName, tmpAcc.getId());
        if (tmpDep == null) {
            logger.error("Client's deposit does not exist with follow name: " + depositName);
            new ResourceNotFoundException("Deposit does not found by this name: " +
                depositName + " or Client has no deposits.");
        }

        //Calculate monthly income:
        val income = (tmpDep.getValue() * tmpDep.getPercent() + (LocalDateTime
            .now().getDayOfMonth() - tmpDep.getDate().getDayOfMonth()) / LocalDateTime.now().getDayOfYear()) / 100;
        //Save updated parameters
        tmpDep.setValue(tmpDep.getValue() + value);
        tmpDep.setAccountId(tmpAcc.getId());
        tmpDep.setCurrency(currency);
        tmpDep.setName(depositName);
        tmpDep.setPercent(tmpDep.getPercent());
        tmpDep.setDate(LocalDateTime.now());
        tmpDep.setIncome(income);
        final Deposit updatedDeposit = depositRepository.save(tmpDep);
        logger.info("Deposit " + depositName + "of Client " + client.getLogin() + " has been updated. Income added successfully!");
        return ResponseEntity.ok(updatedDeposit);
    }

    public Map<String, Boolean> DeleteDeposit(long depositId) throws ResourceNotFoundException {
        Deposit deposit = depositRepository.findById(depositId)
            .orElseThrow(() -> new ResourceNotFoundException("Deposit not found for this id:: " + depositId));

        depositRepository.delete(deposit);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        logger.info("Deposit " + deposit + " has been deleted!");
        return response;
    }

    public List<Deposit> getByClientLogin(String login) {
        List<Deposit> allList = new ArrayList<>();
        List<Account> allAccounts = accountRepository.findByIdClient(clientRepository.findByLogin(login).getId());
        for (Account acc : allAccounts) {
            allList.addAll(depositRepository.findByAccountId(acc.getId()));
        }
        return allList;
    }

    public Deposit addDeposit(String login, Deposit deposit){
        Account tmpAcc = accountRepository
            .findByIdClientAndCurrency(clientRepository.findByLogin(login).getId(), deposit.getCurrency());
            deposit.setAccountId(tmpAcc.getId());
            createDeposit(deposit);
        return deposit;
    }


}
