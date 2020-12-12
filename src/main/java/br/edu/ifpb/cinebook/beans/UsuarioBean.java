package br.edu.ifpb.cinebook.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.Usuario;
import br.edu.ifpb.cinebook.servico.CinemaServico;
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
	
	private Integer cinemaId;
	
	@EJB
	private UsuarioServico servico;
	@EJB
	private CinemaServico cinemaServico;
	@Inject
	private LoginBean loginBean;
	@Inject
	private FacesContext facesContext;
	
	public UsuarioBean() {
		
	}
	
	@PostConstruct
	public void init() {
		funcionarios = new ArrayList<Usuario>();
		papeis = new ArrayList<String>();
	}
	
	public String cadastrarCliente() {
		System.out.println("Cadastra - Usuario: " + usuario.getNome());
		
		papeis.add("CLIENTE");
		usuario.setPapeis(papeis);
		
		servico.cadastrar(usuario);
		
		usuario = new Usuario();
		
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String cadastrarFuncionario() {
		System.out.println("Cadastra - Usuario: " + usuario.getNome());
		
		papeis.add(papel);
		usuario.setPapeis(papeis);
		
		Cinema cinema = new Cinema();
		cinema.setId(cinemaId);
		usuario.setCinema(cinema);
		
		servico.cadastrar(usuario);
		
		usuario = new Usuario();
		
		return "/gerente/listaDeFuncionarios.xhtml?faces-redirect=true";
	}
	
	public String editar() {
		System.out.println("Editando usuário " + usuario.getEmail());
		
		if (loginBean.getUsuarioLogado().getPapeis().contains("GERENTE")) {
			papeis.add(papel);
			usuario.setPapeis(papeis);
		}
		
		servico.atualizar(usuario);
		
		return "/paginaInicial.xhtml?faces-redirect=true";
	}
	
	public void recuperarCinemaId() {
		Usuario usuarioLogado = loginBean.getUsuarioLogado();
		
		if (usuarioLogado.getCinema() != null) {
			cinemaId = usuarioLogado.getCinema().getId();
		}
	}
	
	public void recuperarUsuarioEdicao() {
		usuario = loginBean.getUsuarioLogado();
	}
	
	public void listarFuncionariosDoCinema() {
		funcionarios = servico.listarTodosFuncionarios(cinemaId);
	}
	
	public void listarTodosOsFuncionarios() {
		funcionarios = servico.listarTodosOsGerentes();
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

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

}