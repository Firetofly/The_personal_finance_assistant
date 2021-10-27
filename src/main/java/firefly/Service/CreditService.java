/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.model.Credit;
import firefly.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditService {

    @Autowired
    private CreditRepository creditRepository;

    Credit credit = new Credit(1, "Credit for TV", 8.0, 15000, "RUB", 12);

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


}
