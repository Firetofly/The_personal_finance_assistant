/*
 * Copyright (c)
 */

package firefly.Controller;

import firefly.Exceptions.ResourceNotFoundException;
import firefly.Model.Client;
import firefly.Repository.ClientRepository;
import firefly.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AdminController {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    //Function for admin role
    @GetMapping("/find-client-by-fullname")
    public List<Client> findByFirstNameAndLastNameAndMiddleName(@RequestParam String firstName,
                                                                @RequestParam String middleName,
                                                                @RequestParam String lastName) {

        return clientService.getByFirstnameAndLastnameAndMiddlename(firstName,
            middleName, lastName);
    }

    //Function for admin role
    @GetMapping("/find-client-by-login")
    public Client findByLogin(@RequestParam String login) {
        return clientService.findByLogin(login);
    }

    //Function for admin role
    @GetMapping("/find-by-id")
    public ResponseEntity<Client> getById(@RequestParam long idClient) throws ResourceNotFoundException {
        return clientService.findById(idClient);
    }

    //Function for admin role
    @GetMapping("/find-all-clients")
    public List<Client> findAllClients(){
        return clientService.getAllClients();
    }

    //Change client's data
    @PutMapping("/update-client")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) throws ResourceNotFoundException {
        return clientService.updateClient(client.getLogin(),client);
    }

    //Delete client by login
    @DeleteMapping("delete-client")
    public Map<String, Boolean> deleteClient(@RequestParam String login) throws ResourceNotFoundException{
        return clientService.deleteClient(clientService.findByLogin(login).getId());
    }

   /* @GetMapping("/client-accounts")
    public List<ClientAccountView> ClientAccounts(@RequestParam Client client) {
        return accountRepository.findClientAccounts(client.getId());
    }*/


}
