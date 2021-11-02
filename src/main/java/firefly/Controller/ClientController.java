/*
 * Copyright (c)
 */

package firefly.Controller;


import firefly.Model.Client;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.View.ClientAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Client")
@CrossOrigin(origins = "http://localhost:8090")
public class ClientController{

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    AccountRepository accountRepository;

    /*
    @GetMapping("/findById")
    public Client findById(@RequestParam long id){
        return clientRepository.findById(id);
    }
    */

    @GetMapping("/findclientbyfullname")
    public List<Client> findByFirstNameAndLastNameAndMiddleName(@RequestParam String firstName,
                                                                @RequestParam String middleName,
                                                                @RequestParam String lastName){

        return clientRepository.findByFirstNameAndLastNameAndMiddleName(firstName,
            middleName, lastName);
    }

    @GetMapping("/client")
    public Client findByLogin(@RequestParam String login){
        return clientRepository.findByLogin(login);
    }

    /*
    @PostMapping("/saveclient")
    public Client save(@RequestParam Client client){
        return clientRepository.save(client);
    }
    */

   /* @GetMapping("/findallclients")
    public List<Client> findAll(){
        return clientRepository.findAll();
    }*/

    @GetMapping("/clientaccounts")
    public List<ClientAccountView> ClientAccounts(@RequestParam Client client){
        return accountRepository.findClientAccounts(client.getId());
    }



}
