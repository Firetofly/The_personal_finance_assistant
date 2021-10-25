/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.model.Client;
import firefly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

/*    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }*/

    public Client findById(long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByFullName(String firstName, String middleName, String lastName) {
        return clientRepository.findByFullName(firstName, middleName, lastName);
    }

    public List<Client> findByLogin(String login) {
        return findByLogin(login);
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }


}
