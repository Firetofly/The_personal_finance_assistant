/*
 * Copyright (c)
 */

package firefly.Model;

import javax.management.relation.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "account")

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Column(name = "id_client")
    private long idClient;

    private String currency;
    private double sum;

    @ManyToMany
    @JoinTable(name = "account_transaction", joinColumns = @JoinColumn(name="id_account"),
        inverseJoinColumns = @JoinColumn(name="id_transaction"))
    private Set<Transaction> transactions;

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

    public double getSum(){
        return this.sum;
    }

    public void setSum(double sum){
        this.sum=sum;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString(){
        return "Account{"+id+"\nclient id= "+idClient+"\ncurrency= "+currency+"\nsum= "+sum+"}";
    }
}
