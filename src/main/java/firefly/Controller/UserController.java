package firefly.Controller;

import firefly.Model.Client;
import firefly.Service.ClientService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/registration")
    public void registrationUser(@RequestBody Client client) throws AlreadyExistsException{
        if(clientService.findByLogin(client.getLogin()) !=null){
            new AlreadyExistsException("Incorrect login. This login already exists.");
        }
        System.out.println(client.toString());
        clientService.createClient(client);
    }

    @PostMapping("/login")
    public Client loginUser(@RequestBody Client client) throws NotFoundException {
        if (clientService.findByLogin(client.getLogin())==null){
            throw new NotFoundException("Login not found!");
        }
        System.out.println(client.toString());

        return clientService.findByLogin(client.getLogin());

    }



}
