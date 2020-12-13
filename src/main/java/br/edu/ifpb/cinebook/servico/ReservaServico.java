package br.edu.ifpb.cinebook.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.edu.ifpb.cinebook.modelo.Ingresso;
import br.edu.ifpb.cinebook.modelo.Reserva;

@DeclareRoles({"ADMINISTRADOR", "CLIENTE", "OPERADOR", "GERENTE"})
@Stateful
public class ReservaServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] ReservaBean foi criado");
	}
	
	@RolesAllowed("CLIENTE")
	public void cadastrar(Reserva reserva) {
		Integer maiorId = (Integer) manager.createNativeQuery("select max(r.id) from reserva as r").getSingleResult();
		
		if (maiorId == null) {
			maiorId = 1;
		} else {
			maiorId++;
		}
		
		for (Ingresso ingresso : reserva.getIngressos()) {
			Reserva reservaId = new Reserva();
			reservaId.setId(maiorId);
			
			ingresso.setReserva(reservaId);
		}
		
		manager.persist(reserva);
		
		System.out.println("[INFO] Salvou a Reserva " + reserva.getId());
	}
	
	@RolesAllowed("CLIENTE")
	public void atualizarIngresso(Ingresso ingresso) {
		manager.merge(ingresso);
	}
	
	@RolesAllowed("CLIENTE")
	public List<Reserva> listarTodasReservas() {
	    System.out.println("[INFO] Consultando todas as reservas");
	    
		return manager.createQuery("select r from Reserva r", Reserva.class).getResultList();
	}
	
	@RolesAllowed("CLIENTE")
	public List<Reserva> listarReservasDoCliente(String email) {
	    System.out.println("[INFO] Consultando todas as reservas de um cliente");
	    
	    return manager.createQuery("select r from Reserva r inner join fetch r.ingressos where r.cliente = '" + email + "' group by r.id order by r.id desc", Reserva.class).getResultList();
	}
	
	@RolesAllowed("CLIENTE")
	public Reserva buscarPeloId(Integer reservaId) {
	    System.out.println("[INFO] Consultando Reserva pelo Id");

		TypedQuery<Reserva> query = manager.createQuery("select r from Reserva r inner join fetch r.ingressos where r.id = " + reservaId, Reserva.class);
		
		return query.getSingleResult();
	}
	
	@RolesAllowed("CLIENTE")
	public void excluir(Integer reservaId) {
		System.out.println("[INFO] Excluindo Reserva pelo Id");
		Reserva reserva = buscarPeloId(reservaId);
		
		manager.remove(reserva);
	}

}
