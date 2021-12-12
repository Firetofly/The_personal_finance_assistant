/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Exceptions.ResourceNotFoundException;
import firefly.Model.Client;
import firefly.Repository.ClientRepository;
import firefly.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService /*implements UserDetailsService*/ {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RolesRepository rolesRepository;


    /*public Optional<Client> getById(long id) {
        return clientRepository.findById(id);
    }*/

    public List<Client> getByFirstnameAndLastnameAndMiddlename(String firstName, String middleName, String lastName) {
        return clientRepository.findByFirstNameAndLastNameAndMiddleName(firstName, middleName, lastName);
    }

    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

/*    //Implementation of UserDetailsService method
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Roles role: client.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getDisplayName()));
        }
        return new org.springframework.security.core.userdetails.User(client.getLogin(),client.getPassword(),grantedAuthorities);
    }*/

    //Method created a new client with the default role is "User".
    /*public void saveClient(Client client){
        //client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        client.setPassword(client.getPassword());
        Set<Roles> roles = new HashSet<>();
        roles.add(rolesRepository.getOne(1L));
        client.setRoles(roles);
        clientRepository.save(client);
    }*/

    public void saveClient(Client client){
        clientRepository.save(client);
    }

    public Client findByLogin(String login){
        return clientRepository.findByLogin(login);
    }

    /*
    * public ResponseEntity<Client> findById(@RequestParam long idClient) throws ResourceNotFoundException {
        Client client = clientRepository.findById(idClient)
            .orElseThrow(()->new ResourceNotFoundException("Client not found for this id:: "+idClient));
        return ResponseEntity.ok().body(client);
    }
    */

    public ResponseEntity<Client> findById(long idClient)throws ResourceNotFoundException{
        Client client = clientRepository.findById(idClient)
            .orElseThrow(()->new ResourceNotFoundException("Client not found for this id:: "+idClient));
        return ResponseEntity.ok().body(client);
    }

    public ResponseEntity<Client> updateClient(String loginClient, Client clientDetails) throws ResourceNotFoundException {
        Client client =clientRepository.findByLogin(loginClient);
        if (client != null){
            new ResourceNotFoundException("Client not found of this login:: "+ loginClient);
        }
           //orElseThrow(()->new ResourceNotFoundException("Client not found of this login:: "+ loginClient));
        client.setFirstName(clientDetails.getFirstName());
        client.setLastName(clientDetails.getLastName());
        client.setMiddleName(clientDetails.getMiddleName());
        clientDetails.setLogin(clientDetails.getLogin());

        final Client updatedClient = clientRepository.save(client);
        return ResponseEntity.ok(updatedClient);
    }

    public Map<String, Boolean> deleteClient(long idClient) throws ResourceNotFoundException{
        Client client = clientRepository.findById(idClient)
            .orElseThrow(()-> new ResourceNotFoundException("Client not found for this id:: "+ idClient));

        clientRepository.delete(client);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
