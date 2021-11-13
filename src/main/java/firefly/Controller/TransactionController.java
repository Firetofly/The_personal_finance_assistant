/*
 * Copyright (c)
 */

package firefly.Controller;

import firefly.Model.Account;
import firefly.Model.Category;
import firefly.Model.Client;
import firefly.Model.Transaction;
import firefly.Repository.AccountRepository;
import firefly.Repository.CategoryRepository;
import firefly.Repository.ClientRepository;
import firefly.Repository.TransactionRepository;
import firefly.Service.TransactionService;
import firefly.View.TransactionCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/Transaction")
@CrossOrigin(origins = "http://localhost:8090")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    TransactionService transactionService;

    @GetMapping("/all-date-transaction")
    public List<TransactionCategoryView> allClientTransactionsDate(@RequestParam long idClient) throws NullPointerException {
        if (transactionRepository.queryTransactions(idClient, LocalDateTime.now().minusYears(31), LocalDateTime.now()) != null) {
            return transactionRepository.queryTransactions(idClient,
                LocalDateTime.now().minusYears(31),
                LocalDateTime.now());
        } else {
            throw new NullPointerException("Client has not any transactions!");
        }
    }

    @GetMapping("/all-transactions")
    public List<Transaction> allClientTransactions(@RequestBody Client client) throws NullPointerException {
        if (transactionService.clientAccountTransactions(client) != null) {
            return transactionService.clientAccountTransactions(client);
        } else {
            throw new NullPointerException("Client has not any transactions!");
        }
    }

    @PostMapping("/add-negative-transaction")
    public void addNegativeTransaction(@RequestParam Client client, @RequestParam double value,
                                       @RequestParam String currency, @RequestParam String categoryName) {
        transactionService.addNegativeTransaction(client, value, currency, categoryName);
    }

    @PostMapping("/add-positive-transaction")
    public void addPositiveTransaction(@RequestParam Client client, @RequestParam double value,
                                       @RequestParam String currency, @RequestParam String categoryName) {
        transactionService.addPositiveTransaction(client, value, currency, categoryName);
    }

    @GetMapping("/all-time-category-transactions")
    public List<Transaction> allCategoryTransactions(@RequestParam Client client, @RequestParam String nameCategory) {
        return transactionRepository.getTransactionsByCategory(client.getId(),
            LocalDateTime.now().minusYears(31),
            LocalDateTime.now(), nameCategory);
    }

    @GetMapping("/last-month-all-transactions")
    public List<TransactionCategoryView> lastMonthTransactions(@RequestParam long idClient) throws NullPointerException {
        if (transactionRepository.queryTransactions(idClient, LocalDateTime.now().minusMonths(1), LocalDateTime.now()) != null) {
            return transactionRepository.queryTransactions(idClient,
                LocalDateTime.now().minusMonths(1),
                LocalDateTime.now());
        } else {
            throw new NullPointerException("Client has not any transactions!");
        }
    }

    @GetMapping("/last-year-all-transactions")
    public List<TransactionCategoryView> lastYearTransactions(@RequestParam long idClient) throws NullPointerException {
        if (transactionRepository.queryTransactions(idClient, LocalDateTime.now().minusYears(1), LocalDateTime.now()) != null) {
            return transactionRepository.queryTransactions(idClient,
                LocalDateTime.now().minusYears(1),
                LocalDateTime.now());
        } else {
            throw new NullPointerException("Client has not any transactions!");
        }
    }

    @GetMapping("/last-month-category-transactions")
    public List<Transaction> lastMonthCategoryTransactions(@RequestParam Client client, @RequestParam String nameCategory) {
        return transactionRepository.getTransactionsByCategory(client.getId(),
            LocalDateTime.now().minusMonths(1),
            LocalDateTime.now(), nameCategory);
    }

    @GetMapping("/last-year-category-transactions")
    public List<Transaction> lastYearCategoryTransactions(@RequestParam Client client, @RequestParam String nameCategory) {
        return transactionRepository.getTransactionsByCategory(client.getId(),
            LocalDateTime.now().minusYears(1),
            LocalDateTime.now(), nameCategory);
    }


}
