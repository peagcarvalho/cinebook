package br.edu.ifpb.cinebooking.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import br.edu.ifpb.cinebooking.modelo.Reserva;

@Stateful
public class ReservaServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] ReservaBean foi criado");
	}
	
	public void cadastrar(Reserva reserva) {
		System.out.println("[INFO] Salvando a Reserva "  + reserva.getId());
		manager.persist(reserva);
		System.out.println("[INFO] Salvou a Reserva " + reserva.getId());
	}
	
	public List<Reserva> listarTodasReservas() {
	    System.out.println("[INFO] Consultando todas as reservas");
		return manager.createQuery("select r from Reserva r", Reserva.class).getResultList();
	}
	
	public Reserva buscarPeloId(Integer reservaId) {
	    System.out.println("[INFO] Consultando Reserva pelo Id");

		Reserva reserva = manager.find(Reserva.class, reservaId);
		
		return reserva;
	}
	
	public void atualizar(Reserva reserva) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(reserva);
		tx.commit();
	}
	
	public void excluir(Integer reservaId) {
		Reserva reserva = buscarPeloId(reservaId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(reserva);
		tx.commit();
	}

}
