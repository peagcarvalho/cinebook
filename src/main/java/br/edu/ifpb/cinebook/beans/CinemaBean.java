package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
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

@Named
@ViewScoped
public class CinemaBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Cinema cinema = new Cinema();
	private List<Cinema> cinemas;
	@EJB
	private CinemaServico servico;
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private FacesContext facesContext;
	
	@PostConstruct
	public void aposCriacao() {
		cinemas = servico.listarTodosCinemas();
	}
	
	public String cadastrar() {
		servico.cadastrar(cinema);
		
		cinema = new Cinema();
		
		return "admin/painelAdmin.xhtml?faces-redirect=true";
	}
	
	public String editar() {
		System.out.println("Cinema - Nome: "+ cinema.getNome());
		
		servico.atualizar(cinema);			
		
		return "admin/listaDeCinemas.xhtml?faces-redirect=true";
	}
	
	public void recuperarCinema() {
		Usuario usuarioLogado = loginBean.getUsuarioLogado();
		
		if (usuarioLogado.getCinema() != null) {
			cinema = usuarioLogado.getCinema();
		}
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
	public List<Cinema> getCinemas() {
		return cinemas;
	}
	
	public void setCinemas(List<Cinema> cinemas) {
		this.cinemas = cinemas;
	}
	
}
