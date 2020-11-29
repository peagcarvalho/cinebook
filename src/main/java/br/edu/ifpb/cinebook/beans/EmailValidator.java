/*package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.edu.ifpb.dac.livraria.modelo.Autor;

@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator<String> {

	public void validate(FacesContext context, UIComponent component, String email) throws ValidatorException {
	    if (!email.contains("@")) {
	        throw new ValidatorException(new FacesMessage("Formato inválido de e-mail (Deve conter @)."));
	    }
	}

}*/