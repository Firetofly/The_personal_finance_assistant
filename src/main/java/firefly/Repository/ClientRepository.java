/*
 * Copyright (c)
 */

package firefly.Repository;


import firefly.Exceptions.ResourceNotFoundException;
import firefly.Model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long>{

    //Client findById(long id);

    List<Client> findByFirstNameAndLastNameAndMiddleName(String firstName, String middleName, String lastName);

    Client findByLogin(String login);

    Client save(Client client);


}
