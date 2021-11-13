/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Account;
import firefly.Model.Client;
import firefly.Model.Transaction;
import firefly.Repository.AccountRepository;
import firefly.Repository.CategoryRepository;
import firefly.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Transaction findById(long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findByIdAccount(long idAccount) {
        return transactionRepository.findByIdAccount(idAccount);
    }

    public List<Transaction> findByIdCategory(long idCategory) {
        return transactionRepository.findByIdCategory(idCategory);
    }


    public List<Transaction> findByIncomeDate(LocalDateTime incomeDate) {
        return transactionRepository.findByIncomeDate(incomeDate);
    }

    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public List<Transaction> clientAccountTransactions(Client client) {
        List<Account> account = accountRepository.findByIdClient(client.getId());
        List<Transaction> transactions = new ArrayList<>();

        for (Account value : account) {
            List<Transaction> tmp = transactionRepository.findByIdAccount(value.getId());
            transactions.addAll(tmp);
        }
        return transactions;
    }

    public void addNegativeTransaction(Client client, double value, String currency, String categoryName) {

        Account tmpAcc = accountRepository.findByIdClientAndCurrency(client.getId(), currency);
        tmpAcc.setSum(tmpAcc.getSum() - value);

        Transaction newTransaction = new Transaction(categoryRepository.findByName(categoryName).getId(), tmpAcc.getId(),
            currency, value);

        createTransaction(newTransaction);
        transactionRepository.SetAccountValue(client.getId(), currency, tmpAcc.getSum() - value);
    }

    public void addPositiveTransaction(Client client, double value, String currency, String categoryName) {

        Account tmpAcc = accountRepository.findByIdClientAndCurrency(client.getId(), currency);
        tmpAcc.setSum(tmpAcc.getSum() + value);

        Transaction newTransaction = new Transaction(categoryRepository.findByName(categoryName).getId(), tmpAcc.getId(),
            currency, value);

        createTransaction(newTransaction);
        transactionRepository.SetAccountValue(client.getId(), currency, tmpAcc.getSum() + value);
    }

}
