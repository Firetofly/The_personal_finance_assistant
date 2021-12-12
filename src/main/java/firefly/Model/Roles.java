/*
 * Copyright (c)
 */

package firefly.Model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String displayName;
    private String description;


/*    @ManyToMany(mappedBy = "roles")
    private Set<Client> clients;*/



    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


/*    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }*/


    @Override
    public String toString() {
        return "Role{" +
            "id=" + id +
            ", name= " + displayName +
            ", description= " + description + "}";
    }

    @Override
    public String getAuthority() {
        return getDisplayName();
    }
}
