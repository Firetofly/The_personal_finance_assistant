package firefly.Controller;

import firefly.Model.Client;
import firefly.Service.ClientService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

//import firefly.Service.SecurityService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

    @Autowired
    private ClientService clientService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    @PostMapping("/registration")
    @ResponseStatus(value = HttpStatus.OK, reason = "Successful registration")
    public void registrationUser(@RequestBody Client client){
        if (clientService.findByLogin(client.getLogin()) != null) {
            logger.info("User tried to register with following parameters:\nlogin: " +client.getLogin());
            logger.error("Incorrect login. Such login already exists");
        }
        else if(client.getLogin()==null){
            logger.error("Incorrect login. Login must be not null!");
        }
        else{
            clientService.saveClient(client);
            logger.info("User as login: " + client.getLogin() + " has been successfully registered");
        }
    }

    @PostMapping("/login")
    public Client loginUser(@RequestBody Client client){

        clientService.findByLogin(client.getLogin()).setLastLoginDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
        if (clientService.findByLogin(client.getLogin())==null){
            logger.error("Client with such login is not exist!");
            return null;
        }
        else{
            logger.info("Client "+client.getLogin()+" has been authorized successfully.");
            clientService.saveClient(clientService.findByLogin(client.getLogin()));
            return clientService.findByLogin(client.getLogin());
        }
    }

}
