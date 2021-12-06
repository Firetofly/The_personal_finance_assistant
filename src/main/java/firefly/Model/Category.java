/*
 * Copyright (c)
 */

package firefly.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
/*
    @ManyToMany(mappedBy = "category")
    private Set<Transaction> transactions;
*/

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
/*
    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
*/
    @Override
    public String toString(){
        return "Category{ "+id+"\nname= "+name+"}";
    }
}
