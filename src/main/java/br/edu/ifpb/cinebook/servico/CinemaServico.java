package br.edu.ifpb.cinebook.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebook.modelo.Cinema;

@Singleton
public class CinemaServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] CinemaBean foi criado ");
	}
	
	public void cadastrar(Cinema cinema) {
		System.out.println("[INFO] Salvando o Cinema "  + cinema.getNome());
		manager.persist(cinema);
		System.out.println("[INFO] Salvou o Cinema " + cinema.getNome());
	}
	
	public List<Cinema> listarTodosCinemas() {
	    System.out.println("[INFO] Consultando todos os cinemas");
		return manager.createQuery("select c from Cinema c", Cinema.class).getResultList();
	}
	
	public Cinema buscarPeloId(Integer cinemaId) {
	    System.out.println("[INFO] Consultando Cinema pelo Id");

		Cinema cinema = manager.find(Cinema.class, cinemaId);
		
		return cinema;
	}
	
	public void atualizar(Cinema cinema) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(cinema);
		tx.commit();
	}
	
	public void excluir(Integer cinemaId) {
		Cinema cinema = buscarPeloId(cinemaId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(cinema);
		tx.commit();
	}

}
