/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Client;
import firefly.Model.Roles;
import firefly.Repository.ClientRepository;
import firefly.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Client findById(long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findByFirstnameAndLastnameAndMiddlename(String firstName, String middleName, String lastName) {
        return clientRepository.findByFirstNameAndLastNameAndMiddleName(firstName, middleName, lastName);
    }

    public List<Client> findAllClients(){
        return clientRepository.findAll();
    }

    //Implementation of UserDetailsService method
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Client client = clientRepository.findByLogin(login);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Roles role: client.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getDisplayName()));
        }
        return new org.springframework.security.core.userdetails.User(client.getLogin(),client.getPassword(),grantedAuthorities);
    }

    //Method created a new client with the default role is "User".
    public void saveClient(Client client){
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        Set<Roles> roles = new HashSet<>();
        roles.add(rolesRepository.getOne(1L));
        client.setRoles(roles);
        clientRepository.save(client);
    }

    public Client findClientByLogin(String login){
        return clientRepository.findByLogin(login);
    }



}
