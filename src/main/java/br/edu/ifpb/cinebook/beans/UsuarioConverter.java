package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import br.edu.ifpb.cinebook.modelo.Usuario;
import br.edu.ifpb.cinebook.servico.UsuarioServico;

@FacesConverter(managed = true, forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {
	
	@Inject
	private UsuarioServico servico;

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String email) {
		if (email == null || email.isEmpty()) {
			return null;
		}
		
		try {
			Usuario usuario = servico.buscarPeloId(email);
			return usuario;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Usuário com email inválido"), e);
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario usuario) {
		if (usuario == null) {
			return "";
		}
		
		if (usuario.getEmail() != null) {
			return usuario.getEmail();
		} else {
			throw new ConverterException(new FacesMessage("Usuário com email inválido"), null);
		}
	}

}
