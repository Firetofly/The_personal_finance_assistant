/*
 * Copyright (c)
 */

package model;

import javax.persistence.*;

@Entity
@Table(name="Roles")

public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String display_name;
    private String description;


    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getDisplayName(){
        return this.display_name;
    }

    public void setDisplayName(String name){
        this.display_name=name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description=description;
    }

}
