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
        return new StringBuilder("Client{ ").append("id= ").append(id).append("first_name= ").append(first_name)
            .append("middle_name= ").append(middle_name).append("last_name= ").append(last_name).append("login= ")
            .append("password= ").append(password).append("last_login_date= ").append(last_login_date).toString();
    }

}
