/*
 * Copyright (c)
 */

package model;

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

    private long id_client;
    private String currency;
    private  float sum;

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public long getIdClient(){
        return this.id_client;
    }

    public void setIdClient(long id){
        this.id_client=id;
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
}
