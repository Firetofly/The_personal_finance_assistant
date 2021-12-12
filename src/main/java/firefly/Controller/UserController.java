package firefly.Controller;

import firefly.Model.Client;
import firefly.Service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import firefly.Service.SecurityService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

    @Autowired
    private ClientService clientService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping("/registration")
    public void registrationUser(@RequestBody Client client){
        if (clientService.findByLogin(client.getLogin()) != null) {
            logger.info("User tried to register with following parameters: " + client);
            logger.error("Incorrect login. Such login already exists");
        }
        clientService.saveClient(client);
        logger.info("User as login: " + client.getLogin() + " has been successfully registered");
    }

    @CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
    @PostMapping("/login")
    public Client loginUser(@RequestBody Client client){
        if (clientService.findByLogin(client.getLogin())==null){
            logger.error("Client with such login already exists!");
        }
        logger.info(client+" has been authorized successfully.");
        return clientService.findByLogin(client.getLogin());
    }

}
