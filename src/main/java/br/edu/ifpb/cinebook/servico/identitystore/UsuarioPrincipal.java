package br.edu.ifpb.cinebook.servico.identitystore;

import javax.security.enterprise.CallerPrincipal;
import br.edu.ifpb.cinebook.modelo.Usuario;

public class UsuarioPrincipal extends CallerPrincipal {
	
	private final Usuario usuario;

	public UsuarioPrincipal(Usuario usuario) {
		super(usuario.getEmail());
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
}
