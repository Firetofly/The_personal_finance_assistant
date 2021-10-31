/*
 * Copyright (c)
 */

package firefly.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Credit")
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Account_id")
    private long accountId;

    private String name;
    private double percent;
    private double value;
    private String currency;
    private int months;
    @Column(name = "monthly_payment")
    private double monthlyPayment;
    @Column(name = "last_date")
    private LocalDateTime lastDate;

/*    public Credit(long accountId, String name, *//*String description,*//* double percent, double value, String currency, int months) {
        this.accountId = accountId;
        this.name = name;
        //this.description = description;
        this.percent = percent;
        this.value = value;
        this.currency = currency;
        this.months = months;
    }*/

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public LocalDateTime getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDateTime lastDate) {
        this.lastDate = lastDate;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Credit{ " + id + "account_id= " + accountId + "name= " + "percent= " + percent
            + "value= " + value + "currency= " + currency + "}";
    }


}
