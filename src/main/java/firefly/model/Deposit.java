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
@Table(name = "Deposit")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "id_account")
    private long idAccount;
    private String name;
    private float value;
    private float percent;
    private String description;
    private String currency;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString(){
        return "Deposit{ "+"id= "+id+"id_account= "+idAccount+"name= "+name+"value= "+value+"percent= "+percent
            +"description= "+description+"currency= "+currency+"}";
    }
}
