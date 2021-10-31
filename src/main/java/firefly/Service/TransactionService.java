/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Transaction;
import firefly.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction findById(long id){
        return transactionRepository.findById(id);
    }

    public List<Transaction> findByIdAccount(long idAccount){
        return transactionRepository.findByIdAccount(idAccount);
    }

    public List<Transaction> findByIdCategory(long idCategory){
        return transactionRepository.findByIdCategory(idCategory);
    }


    public List<Transaction> findByIncomeDate(LocalDateTime incomeDate){
        return transactionRepository.findByIncomeDate(incomeDate);
    }

    public void createTransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }


}
