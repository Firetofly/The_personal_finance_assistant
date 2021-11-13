/*
 * Copyright (c)
 */

package firefly.Controller;


import firefly.Model.Client;
import firefly.Repository.AccountRepository;
import firefly.Repository.ClientRepository;
import firefly.Service.ClientService;
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
//@CrossOrigin(origins = "http://localhost:8090")
public class ClientController {

    @Autowired
    ClientService clientService;

    //@Autowired


    //Function for admin role
    @GetMapping("/find-client-by-fullname")
    public List<Client> findByFirstNameAndLastNameAndMiddleName(@RequestParam String firstName,
                                                                @RequestParam String middleName,
                                                                @RequestParam String lastName) {

        return clientService.findByFirstnameAndLastnameAndMiddlename(firstName,
            middleName, lastName);
    }

    //Function for admin role
    @GetMapping("/find-client-by-login")
    public Client findByLogin(@RequestParam String login) {
        return clientService.findByLogin(login);
    }

    @GetMapping("/find-by-id")
    public Client findById(@RequestParam long idClient){
        return clientService.findById(idClient);
    }


   /* @GetMapping("/client-accounts")
    public List<ClientAccountView> ClientAccounts(@RequestParam Client client) {
        return accountRepository.findClientAccounts(client.getId());
    }*/


}
