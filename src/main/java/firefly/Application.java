/*
 * Copyright (c)
 */

package firefly;import firefly.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import firefly.repository.ClientRepository;


@ComponentScan(basePackages = "firefly")
@SpringBootApplication
public class Application {
    @Autowired
    public ClientRepository clientRepository;

    public static void main(String[] args) {
        Application application=  new Application();

        SpringApplication.run(Application.class,args);

        System.out.println(application.test());
        /*System.out.println(test());
        System.out.println(clientRepository.findById(2));*/
    }
    public Client test(){
        return clientRepository.findById(1);
    }


}
