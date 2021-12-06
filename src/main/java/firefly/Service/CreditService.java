/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Client;
import firefly.Model.Credit;
import firefly.Repository.AccountRepository;
import firefly.Repository.CategoryRepository;
import firefly.Repository.CreditRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CategoryRepository categoryRepository;

    //Method for calculating a monthly payment to credit.
    public double calculateMonthlyPayment(String name, long accountId) {
        Credit credit = creditRepository.findByNameAndAccountId(name, accountId);
        //apr is 1/12 of annual percent rate.
        double apr = 1 / 12 * credit.getPercent();
        return ((credit.getValue() + apr) / (1 - Math.pow((1 + apr), (1 - credit.getMonths()))));
    }

    //Method for set value by calculateMonthlyPayment method
    public void setCalcMonthlyPayment(@NotNull Credit credit) {
        creditRepository.SetMonthlyPayment(credit.getId(), calculateMonthlyPayment(credit.getName(), credit.getAccountId()));
        credit.setMonthlyPayment(calculateMonthlyPayment(credit.getName(), credit.getAccountId()));
    }


    public Credit findById(long id) {
        return creditRepository.findById(id);
    }

    public List<Credit> findByAccountId(long accountId) {
        return creditRepository.findByAccountId(accountId);
    }

    public Credit findByNameAndAccountId(String name, long accountId) {
        return creditRepository.findByNameAndAccountId(name, accountId);
    }

    public void createCredit(Credit credit) {
        creditRepository.save(credit);
    }

    public void addCredit(Client client, String name, double value, double percent, String currency, int months) {
        Credit newCredit = new Credit();
        newCredit.setName(name);
        newCredit.setValue(value);
        newCredit.setCurrency(currency);
        newCredit.setPercent(percent);
        newCredit.setMonths(months);
        newCredit.setAccountId(accountRepository.findByIdClientAndCurrency(client.getId(), currency).getId());
        createCredit(newCredit);
    }

    public void addCreditPay(Client client, String creditName, double payValue, String currency) {
        Credit tmpCredit = creditRepository.findByNameAndAccountId(creditName, accountRepository.
            findByIdClientAndCurrency(client.getId(), currency).getId());
        transactionService.addNegativeTransaction(client, payValue, currency, (categoryRepository.
            findById(8).getName()));

        if (tmpCredit.isActive() && tmpCredit.getValue() == 0) {
            tmpCredit.setActive(false);
            tmpCredit.setLastDate(LocalDateTime.now());
        }
        tmpCredit.setValue(tmpCredit.getValue() - payValue);
        setCalcMonthlyPayment(tmpCredit);
    }

}
