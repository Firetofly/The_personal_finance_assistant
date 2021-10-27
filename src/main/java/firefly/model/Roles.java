/*
 * Copyright (c)
 */

package firefly.model;

import javax.persistence.*;

@Entity
@Table(name="Roles")

public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String displayName;
    private String description;


    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getDisplayName(){
        return this.displayName;
    }

    public void setDisplayName(String displayName){
        this.displayName=displayName;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    @Override
    public String toString(){
        return "Role{ "+ id+"\nrole name= "+displayName+"\ndescription= "+description+"}";
    }

}
