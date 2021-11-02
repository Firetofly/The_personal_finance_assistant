/*
 * Copyright (c)
 */

package firefly.Model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Client")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    private String login;
    @Transient
    private String password;
    private LocalDateTime lastLoginDate;

    @Override
    public String toString(){
        return "Client{ " + "\nid= " + id + "\nfirst_name= " + firstName +
            "\nmiddle_name= " + middleName + "\nlast_name= " + lastName + "\nlogin= " +
            "\npassword= " + password + "\nlast_login_date= " + lastLoginDate+"}";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
}
