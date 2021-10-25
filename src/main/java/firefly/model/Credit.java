/*
 * Copyright (c)
 */

package firefly.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Account_id")
    private long accountId;

    private String name;
    private String description;
    private float percent;
    private float value;
    private float currency;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Credit{ "+id+"account_id= "+accountId+"name= "+"description= "+description+"percent= "+percent
            +"value= "+value+"currency= "+currency+"}";
    }


}
