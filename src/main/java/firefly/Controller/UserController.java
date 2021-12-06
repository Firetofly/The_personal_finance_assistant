package firefly.Controller;

import firefly.Model.Client;
import firefly.Others.ClientValidator;
import firefly.Service.ClientService;
import firefly.Service.SecurityService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class UserController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private ClientValidator clientValidator;

    @Autowired
    private AuthenticationManager authenticationManager;

   /* @PostMapping("/registration")
    public void registrationUser(@RequestBody Client client) throws AlreadyExistsException{
        if(clientService.loadUserByUsername(client.getLogin()) !=null){
            new AlreadyExistsException("Incorrect login. This login already exists.");
        }
        System.out.println(client.toString());
        clientService.saveClient(client);
    }

    @PostMapping("/login")
    public Client loginUser(@RequestBody Client client) throws NotFoundException {
        if (clientService.loadUserByUsername(client.getLogin())==null){
            throw new NotFoundException("Login not found!");
        }
        System.out.println(client.toString());
        System.out.println(client.getId());
        return clientService.loadUserByUsername(client.getLogin());

    }*/

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new Client());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Client userForm, BindingResult bindingResult,Model model){
        clientValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        clientService.saveClient(userForm);
        securityService.autoLogin(userForm.getLogin(), userForm.getConfirmPassword());

        return "redirect:/login";
    }



}
