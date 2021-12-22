/*
 * Copyright (c)
 */

package firefly.Controller;

import firefly.Exceptions.ResourceNotFoundException;
import firefly.Model.Client;
import firefly.Model.Deposit;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.Repository.DepositRepository;
import firefly.Service.DepositService;
import firefly.DTO.ClientDepositDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/deposit")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class DepositController {

    @Autowired
    DepositService depositService;

    @Autowired
    DepositRepository depositRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    private static final Logger logger = Logger.getLogger(DepositController.class);

    @PostMapping("create-deposit")
    public Deposit CreateDeposit(@RequestBody ClientDepositDTO clientDeposit) throws AlreadyExistsException {

        if(depositRepository.findByNameAndAccountId(clientDeposit.getDepositName(),
            accountRepository.findByIdClientAndCurrency(clientRepository
                .findByLogin(clientDeposit.getClientLogin()).getId(),
                clientDeposit.getDepositCurrency()).getId())!=null){
            logger.error("Client's deposit with this name "+ clientDeposit.getDepositName()+" already exist!");
            new AlreadyExistsException("Deposit already exist!");
        }
        Deposit newDeposit = new Deposit();
        newDeposit.setDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        newDeposit.setValue(clientDeposit.getDepositValue());
        newDeposit.setAccountId(accountRepository
            .findByIdClientAndCurrency(clientRepository.findByLogin(clientDeposit
                .getClientLogin()).getId(), clientDeposit.getDepositCurrency()).getId());
        newDeposit.setCurrency(clientDeposit.getDepositCurrency());
        newDeposit.setName(clientDeposit.getDepositName());
        newDeposit.setIncome(clientDeposit.getDepositIncome());
        newDeposit.setPercent(clientDeposit.getDepositPercent());
        return depositService.addDeposit(clientDeposit.getClientLogin(), newDeposit);
    }

    @PutMapping("add-deposit-income")
    public ResponseEntity<Deposit> AddDepositIncome(@RequestBody Client client,
                                                    @RequestParam String name,
                                                    @RequestParam double value,
                                                    @RequestParam String currency) throws ResourceNotFoundException {
        return depositService.addDepositIncome(client, name, value, currency);

    }


    @GetMapping("all-client-deposits")
    public List<Deposit> allClientDeposits(@RequestParam String login) throws ResourceNotFoundException{
        if(depositService.getByClientLogin(login).get(0)==null){
            logger.error("This Client has no deposits.");
            new ResourceNotFoundException("Deposits of this Client is not exist.");
        }
        return depositService.getByClientLogin(login);
    }

    @DeleteMapping("delete-deposit")
    public Map<String,Boolean> deleteDeposit(@RequestParam long depositId) throws ResourceNotFoundException {
        return depositService.DeleteDeposit(depositId);
    }


    //DEBUG
    /*@PostMapping("create-deposit/{login}")
    public Deposit CreateDeposit(@PathVariable long id, @RequestBody Deposit deposit) throws AlreadyExistsException {

        if(depositRepository.findByNameAndAccountId(deposit.getName(),
            accountRepository.findByIdClientAndCurrency(id,
                deposit.getCurrency()).getId())!=null){
            logger.error("Client's deposit with this name "+ deposit.getName()+" already exist!");
            new AlreadyExistsException("Deposit already exist!");
        }
        deposit.setDate(LocalDateTime.now());
        return depositService.addDeposit(clientRepository.findById(id).get().getLogin(), deposit);
    }*/

}
