/*
 * Copyright (c)
 */

package firefly.repository;

import firefly.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findById(long id);

    Roles findByName(String name);

    Roles save(Roles role);
}
