/*
 * Copyright (c)
 */

package firefly.Repository;

import firefly.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {

    Roles findById(long id);

    Roles findByDisplayName(String displayName);

    Roles save(Roles role);
}
