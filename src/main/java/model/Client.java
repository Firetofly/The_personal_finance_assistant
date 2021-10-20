/*
 * Copyright (c)
 */

package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Client")

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String first_name;
    private String middle_name;
    private String last_name;
    private String login;
    private String password;
    private LocalDateTime last_login_date;

    @Override
    public String toString(){
        return "Client{ " + "id= " + id + "first_name= " + first_name +
            "middle_name= " + middle_name + "last_name= " + last_name + "login= " +
            "password= " + password + "last_login_date= " + last_login_date;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id=id;
    }

    public  String getFirstName(){
        return this.first_name;
    }

    public void setFirstName(String first_name){
        this.first_name=first_name;
    }

    public String getMiddleName(){
        return this.middle_name;
    }

    public void setMiddleName(String middle_name){
        this.middle_name=middle_name;
    }

    public String getLastName(){
        return this.last_name;
    }

    public void setLastName(String last_name){
        this.last_name=last_name;
    }

    public String getLogin(){
        return this.login;
    }

    public void setLogin(String login){
        this.login=login;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public LocalDateTime getLastLoginDate(){
        return this.last_login_date;
    }

    public void setLastLoginDate(LocalDateTime date){
        this.last_login_date=date;
    }

}
