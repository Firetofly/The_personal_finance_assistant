/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Client;
import firefly.Repository.ClientRepository;
import firefly.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RolesRepository rolesRepository;

    /*@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;*/

   /*@Autowired
    private EntityManager entityManager;*/

/*    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }*/

    public Client findById(long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByFirstnameAndLastnameAndMiddlename(String firstName, String middleName, String lastName) {
        return clientRepository.findByFirstNameAndLastNameAndMiddleName(firstName, middleName, lastName);
    }

    public Client findByLogin(String login) {
        Client tmpClient =clientRepository.findByLogin(login);
        return tmpClient;
    }

    public void createClient(Client client) {
        clientRepository.save(client);
    }


    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);
        if(client != null){
            return (UserDetails) client;
        }
        else{
            throw new UsernameNotFoundException("Client's login doesn't exist!");
        }
    }



}
