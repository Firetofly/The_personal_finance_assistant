/**
 * Validator for {@link firefly.Model.Client} class,
 * implements {@link org.springframework.validation.Validator} interface
 *
 * @author Fomin Sergey
 */

package firefly.Others;

import firefly.Model.Client;
import firefly.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    @Autowired
    ClientService clientService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Client.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Client client = (Client) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Required");
        if(client.getLogin().length()<8 || client.getLogin().length()>32){
            errors.rejectValue("login", "Size.userForm.login");
        }
        if(clientService.findClientByLogin(client.getLogin())!=null){
            errors.rejectValue("login","Duplicate.userForm.login");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if(client.getPassword().length()<8 || client.getPassword().length()>32){
            errors.rejectValue("password", "Size.userForm.password");
        }
        if(!client.getConfirmPassword().equals(client.getPassword())){
            errors.rejectValue("password", "Different.userForm.password");
        }

    }
}
