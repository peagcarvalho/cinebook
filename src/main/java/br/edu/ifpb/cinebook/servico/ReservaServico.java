package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebook.modelo.Ingresso;
import br.edu.ifpb.cinebook.modelo.Reserva;
import br.edu.ifpb.cinebook.modelo.Sessao;

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
		
		SessaoServico sessaoServico = new SessaoServico();
		List<Ingresso> ingressos = reserva.getIngressos();
		List<Sessao> sessoes = new ArrayList<Sessao>();
		
		for (int contador = 0; contador < ingressos.size(); contador++) {
			Ingresso ingresso = ingressos.get(contador);
			Sessao sessao = sessaoServico.buscarPeloId(ingresso.getSessao().getId());
			
			if (sessao.getQuantMaxIngressos() == sessao.getQuantIngressosVendidos()) {
				sessao.setEsgotada(true);
				
				reserva.getIngressos().remove(ingresso);
			} else {
				sessao.setQuantIngressosVendidos(sessao.getQuantIngressosVendidos() + 1);
				
				if (sessao.getQuantMaxIngressos() == sessao.getQuantIngressosVendidos()) {
					sessao.setEsgotada(true);
				}
			}
			
			sessoes.add(sessao);
		}
		
		manager.persist(reserva);
		
		for (Sessao s : sessoes) {
			sessaoServico.atualizar(s);
		}
		
		System.out.println("[INFO] Salvou a Reserva " + reserva.getId());
	}
	
	/*public Reserva retornarSessoesDisponiveis(Reserva reserva) {
		return null;
	}*/
	
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
