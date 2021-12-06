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
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "transaction")
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
    private double value;

   /* @ManyToMany(mappedBy = "transaction")
    private Set<Account> accounts;

    @ManyToMany
    @JoinTable(name = "account_transaction", joinColumns = @JoinColumn(name="id_transaction"),
        inverseJoinColumns = @JoinColumn(name="id_category"))
    private Set<Category> categories;
*/
    /*public Transaction(long idCategory, long idAccount, String currency, double value) {
        this.idCategory = idCategory;
        this.idAccount = idAccount;
        this.incomeDate = LocalDateTime.now();
        this.currency = currency;
        this.value = value;
        this.incomeDate=LocalDateTime.now();
    }*/

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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
/*
    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
*/
    @Override
    public String toString(){
        return "Transaction{"+"id= "+id+"id_category= "+idCategory
            +"id_account= "+idAccount+"income_date= "+incomeDate+"currency= "+currency
            +"value= "+value+"}";
    }
}
