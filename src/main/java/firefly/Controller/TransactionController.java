/*
 * Copyright (c)
 */

package firefly.Controller;

import firefly.Model.Account;
import firefly.Model.Client;
import firefly.Model.Transaction;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.Repository.TransactionRepository;
import firefly.Service.TransactionService;
import firefly.View.TransactionCategoryView;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/alldatetransaction")
    public List<TransactionCategoryView> allClientTransactionsDate(@RequestParam long idClient,
                                                                   @RequestParam LocalDateTime dateFrom) throws NullPointerException{
        if(transactionRepository.queryTransactions(idClient, dateFrom) != null){
            return transactionRepository.queryTransactions(idClient, dateFrom);
        }
        else{
            throw new NullPointerException("Client has not any transactions!");
        }

    }

    @GetMapping("/alltransactions")
    public List<Transaction> AllClientTransactions(@RequestBody Client client) throws  NullPointerException{
        if(transactionService.clientAccountTransactions(client) !=null) {
            return transactionService.clientAccountTransactions(client);
        }
        else {
            throw new NullPointerException("Client has not any transactions!");
        }
    }

}
