/*
 * Copyright (c)
 */

package firefly.Controller;

import firefly.DTO.ClientAccountDTO;
import firefly.Model.Account;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.Service.AccountService;
import javassist.NotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountService accountService;

    @GetMapping("all-client-accounts")
    public List<Account> getAllClientAccount(@RequestParam String login) throws NotFoundException {
        if (accountRepository.findByIdClient(clientRepository.findByLogin(login).getId()) == null) {
            logger.error("Accounts for this client: " + login + " not found!");
            throw new NotFoundException("Accounts for this client: " + login + " not found!");
        }
        return accountRepository.findByIdClient(clientRepository.findByLogin(login).getId());
    }

    @PostMapping("create-client-account")
    public void createClientAccount(@RequestBody ClientAccountDTO clientAccountDTO) throws AlreadyExistsException {
        if (accountRepository.findByIdClientAndCurrency(clientRepository
            .findByLogin(clientAccountDTO.getClientLogin()).getId(), clientAccountDTO.getCurrency()) != null) {
            logger.error("Account with follow parameters already exist: login = "
                + clientAccountDTO.getClientLogin() + " currency= " + clientAccountDTO.getCurrency());
            throw new AlreadyExistsException("This account already exist!");
        }
        accountService.addNewAccount(clientAccountDTO);
        logger.info("Account with follow parameters created successfully: "
            +clientAccountDTO.getClientLogin()+" "+clientAccountDTO.getCurrency());
    }
}
