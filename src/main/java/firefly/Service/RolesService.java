/*
 * Copyright (c)
 */

package firefly.Service;

import firefly.Model.Roles;
import firefly.Repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    Roles findById(long id){
        return rolesRepository.findById(id);
    }

    Roles findByName(String name){
        return rolesRepository.findByDisplayName(name);
    }

    public void createRole(Roles role){
        rolesRepository.save(role);
    }

}
