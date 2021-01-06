package br.edu.ifpb.cinebook.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.faces.annotation.FacesConfig;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;

@CustomFormAuthenticationMechanismDefinition(
		loginToContinue = @LoginToContinue(
				loginPage = "/login.xhtml",
				errorPage = "/login.xhtml"
				)
		)
@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@ApplicationScoped
@Default
public class ConfiguracaoAplicacao {


}
