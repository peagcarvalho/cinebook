package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.Endereco;

@DeclareRoles({"ADMINISTRADOR", "CLIENTE", "OPERADOR", "GERENTE"})
@Stateless
public class CinemaServico {
	
	@PersistenceContext
	public EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] CinemaBean foi criado ");
	}
	
	@RolesAllowed({"ADMINISTRADOR"})
	public void cadastrar(Cinema cinema) {
		System.out.println("[INFO] Salvando o Cinema "  + cinema.getNome());
		manager.persist(cinema);
		System.out.println("[INFO] Salvou o Cinema " + cinema.getNome());
	}
	
	@PermitAll
	public List<Cinema> listarTodosCinemas() {
	    System.out.println("[INFO] Consultando todos os cinemas");
	    
	    TypedQuery<Cinema> query = manager.createQuery("select c from Cinema c", Cinema.class);
		List<Cinema> cinemas = query.getResultList();
		
		for (Cinema cinema : cinemas) {
			cinema.concatenarEndereco();
		}
		
		return cinemas;
	}
	
	@PermitAll
	public List<Cinema> listarCinemasPorCidadeEEstado(String cidade, String estado) {
		System.out.println("[INFO] Consultando todos os cinemas por cidade e estado");
		
		List<Cinema> todosOsCinemas = listarTodosCinemas();
		List<Cinema> cinemasDaCidade = new ArrayList<Cinema>();
		
		for (Cinema cinema : todosOsCinemas) {
			Endereco endereco = cinema.getEndereco();
			
			if (endereco.getCidade().equals(cidade)) {
				if (endereco.getEstado().equals(estado)) {
					cinemasDaCidade.add(cinema);
				}
			}
			
		}
		
		return cinemasDaCidade;
	}
	
	@RolesAllowed({"ADMINISTRADOR", "GERENTE", "OPERADOR"})
	public Cinema buscarPeloId(Integer cinemaId) {
	    System.out.println("[INFO] Consultando Cinema pelo Id");

		Cinema cinema = manager.find(Cinema.class, cinemaId);
		
		cinema.concatenarEndereco();
		
		return cinema;
	}
	
	@RolesAllowed({"ADMINISTRADOR", "GERENTE"})
	public void atualizar(Cinema cinema) {
		manager.merge(cinema);
	}
	
	@RolesAllowed({"ADMINISTRADOR"})
	public void excluir(Integer cinemaId) {
		Cinema cinema = buscarPeloId(cinemaId);
		
		manager.remove(cinema);
	}
	
}
