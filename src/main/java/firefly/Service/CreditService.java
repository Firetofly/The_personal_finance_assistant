/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Credit;
import firefly.Repository.CreditRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    //Method for calculating a monthly payment to credit.
    public double calculateMonthlyPayment(String name) {
        Credit credit = creditRepository.findByName(name);
        //apr is 1/12 of annual percent rate.
        double apr = 1 / 12 * credit.getPercent();
        return ((credit.getValue() + apr) / (1 - Math.pow((1 + apr), (1 - credit.getMonths()))));
    }

    //Method for set value by calculateMonthlyPayment method
    public void setCalcMonthlyPayment(@NotNull Credit credit) {
        creditRepository.setMonthlyPayment(credit.getId(), calculateMonthlyPayment(credit.getName()));
        credit.setMonthlyPayment(calculateMonthlyPayment(credit.getName()));
    }


    public Credit findById(long id) {
        return creditRepository.findById(id);
    }

    public List<Credit> findByAccountId(long accountId) {
        return creditRepository.findByAccountId(accountId);
    }

    public Credit findByName(String name) {
        return creditRepository.findByName(name);
    }

    public void createCredit(Credit credit) {
        creditRepository.save(credit);
    }


}
