package br.edu.ifpb.cinebook.beans;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "cpfValidator")
public class CPFValidator implements Validator<String> {
	
	private final String CPF_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";
	
	private static Pattern pattern = null;

	@Override
	public void validate(FacesContext context, UIComponent component, String cpf) throws ValidatorException {
		pattern = Pattern.compile(CPF_PATTERN, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(cpf);
	    
		if (matcher.matches() == false) {
	        throw new ValidatorException(new FacesMessage("O CPF não parece estar no formato correto"));
	    }
	}
	
	

}
