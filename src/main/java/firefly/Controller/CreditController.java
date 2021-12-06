/*
 * Copyright (c)
 */

package firefly.Controller;


import firefly.Model.Client;
import firefly.Model.Credit;
import firefly.Repository.CreditRepository;
import firefly.Service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Credit")
//@CrossOrigin(origins = "http://localhost:8090")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class CreditController {

    @Autowired
    CreditService creditService;

    @PostMapping("/add-new-credit")
    public void addNewCredit(@RequestParam Client client,@RequestParam String name,@RequestParam double value,
                             @RequestParam double percent,@RequestParam String currency,@RequestParam int months){
       creditService.addCredit(client, name, value, percent, currency, months);
    }

    @PostMapping("/add-credit-pay")
    public void addCreditPay(@RequestParam Client client,@RequestParam String creditName,
                             @RequestParam double payValue,@RequestParam String currency){
        creditService.addCreditPay(client, creditName, payValue, currency);
    }

}
