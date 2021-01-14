package br.edu.ifpb.cinebook.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/login.xhtml",
				errorPage = "/login.xhtml"
				)
		)
@FacesConfig
@ApplicationScoped
public class ConfiguracaoAplicacao {


}
