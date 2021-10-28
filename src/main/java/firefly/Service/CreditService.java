/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.model.Credit;
import firefly.model.Deposit;
import firefly.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    Credit credit = new Credit();

    //Method for calculating a monthly payment to credit.
    public double calculateMonthlyPayment() {
        //P - 1/12 of annual percent rate.
        double apr = 1 / 12 * credit.getPercent();
        return ((credit.getValue() + apr) / (1 - Math.pow((1 + apr), (1 - credit.getMonths()))));
    }

    //Method for set value by calculateMonthlyPayment method
    public void setCalcMonthlyPayment(){
        credit.setMonthlyPayment(calculateMonthlyPayment());
        creditRepository.setMonthlyPayment(credit.getId(), calculateMonthlyPayment());
    }

    public Credit findById(long id){
        return creditRepository.findById(id);
    }

    public List<Credit> findByAccountId(long accountId){
        return creditRepository.findByAccountId(accountId);
    }

    public List<Credit> findByName(String name){
        return creditRepository.findByName(name);
    }

    public void createCredit(Credit credit){
         creditRepository.save(credit);
    }










}
