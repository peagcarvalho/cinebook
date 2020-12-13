package br.edu.ifpb.cinebook.servico.identitystore;

import javax.security.enterprise.CallerPrincipal;
import br.edu.ifpb.cinebook.modelo.Usuario;

public class UsuarioPrincipal extends CallerPrincipal {
	
	private final Usuario usuario;
	private final String papel;

	public UsuarioPrincipal(Usuario usuario) {
		super(usuario.getEmail());
		this.usuario = usuario;
		papel = usuario.getPapeis().get(0);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getPapel() {
		return papel;
	}
	
}
