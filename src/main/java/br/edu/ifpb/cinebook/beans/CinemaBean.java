package br.edu.ifpb.cinebook.beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.servico.CinemaServico;

@Named
@RequestScoped
public class CinemaBean {
	
	private Cinema cinema = new Cinema();
	private List<Cinema> cinemas;
	@EJB
	private CinemaServico servico;
	
	@PostConstruct
	public void aposCriacao() {
		cinemas = servico.listarTodosCinemas();
	}
	
	public void cadastrar() {
		servico.cadastrar(cinema);
		
		cinema = new Cinema();
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
