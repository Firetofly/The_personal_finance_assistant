/*
 * Copyright (c)
 */

package firefly.repository;


import firefly.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findById(long id);

    List<Client> findByFirstNameAndLastNameAndMiddleName(String firstName, String middleName, String lastName);

    List<Client> findByLogin(String login);

    Client save(Client client);
}
