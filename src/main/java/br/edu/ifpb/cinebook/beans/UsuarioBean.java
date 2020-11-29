package br.edu.ifpb.cinebook.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Usuario;
import br.edu.ifpb.cinebook.servico.UsuarioServico;
import java.io.Serializable;

@Named
@ViewScoped
public class UsuarioBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> clientes;
	private List<Usuario> funcionarios;
	private List<Usuario> administradores;
	private String papel;
	private List<String> papeis;
	
	@EJB
	private UsuarioServico servico;
	
	public UsuarioBean() {
		
	}
	
	@PostConstruct
	public void init() {
		clientes = servico.listarTodosClientes();
		funcionarios = servico.listarTodosFuncionarios();
		papeis = new ArrayList<String>();
	}
	
	public String cadastrarCliente() {
		System.out.println("Cadastra - Usuario: " + usuario.getNome());
		
		papel = FacesContext.getCurrentInstance().getExternalContext().
				getRequestParameterMap().get("papel");
		
		papeis.add(papel);
		usuario.setPapeis(papeis);
		
		servico.cadastrar(usuario);
		clientes.add(usuario);
		
		usuario = new Usuario();
		
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String cadastrarFuncionario() {
		System.out.println("Cadastra - Usuario: " + usuario.getNome());
		
		papeis.add(papel);
		usuario.setPapeis(papeis);
		
		servico.cadastrar(usuario);
		funcionarios.add(usuario);
		
		usuario = new Usuario();
		
		return "/login.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getClientes() {
		return clientes;
	}

	public void setClientes(List<Usuario> clientes) {
		this.clientes = clientes;
	}

	public List<Usuario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Usuario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public List<Usuario> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Usuario> administradores) {
		this.administradores = administradores;
	}

	public String getPapel() {
		return papel;
	}

	public void setPapel(String papel) {
		this.papel = papel;
	}

	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}
	
}