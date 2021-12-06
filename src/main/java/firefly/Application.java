/*
 * Copyright (c)
 */

package firefly;import firefly.Model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import firefly.Repository.ClientRepository;


@ComponentScan(basePackages = "firefly")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application {

    public static void main(String[] args) {
       SpringApplication.run(Application.class,args);
    }

}
