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
import java.time.LocalDateTime;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "id_category")
    private long idCategory;

    @Column(name = "id_account")
    private long idAccount;

    @Column(name = "income_date")
    private LocalDateTime incomeDate;

    private String currency;
    private float value;

    public Transaction(long idAccount,long idCategory,LocalDateTime incomeDate, String currency, float value) {
        this.idAccount=idAccount;
        this.incomeDate=incomeDate;
        this.currency=currency;
        this.value=value;
        this.idCategory=idCategory;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public LocalDateTime getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(LocalDateTime incomeDate) {
        this.incomeDate = incomeDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "Transaction{"+"id= "+id+"id_category= "+idCategory
            +"id_account= "+idAccount+"income_date= "+incomeDate+"currency= "+currency
            +"value= "+value+"}";
    }
}
