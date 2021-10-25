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
@Table(name = "Account")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Column(name = "id_client")
    private long idClient;

    private String currency;
    private  float sum;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getIdClient(){
        return this.idClient;
    }

    public void setIdClient(long id){
        this.idClient=id;
    }

    public String getCurrency(){
        return this.currency;
    }

    public void setCurrency(String currency){
        this.currency=currency;
    }

    public float getSum(){
        return this.sum;
    }

    public void setSum(float sum){
        this.sum=sum;
    }

    @Override
    public String toString(){
        return "Account{"+id+"\nclient id= "+idClient+"\ncurrency= "+currency+"\nsum= "+sum+"}";
    }
}
