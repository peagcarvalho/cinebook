package br.edu.ifpb.cinebooking.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebooking.modelo.Sessao;

@Stateless
public class SessaoServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] SessaoBean foi criado");
	}
	
	public void cadastrar(Sessao sessao) {
		System.out.println("[INFO] Salvando a Sessao "  + sessao.getId());
		manager.persist(sessao);
		System.out.println("[INFO] Salvou a Sessao " + sessao.getId());
	}
	
	public List<Sessao> listarTodasSessoes() {
	    System.out.println("[INFO] Consultando todas as sessoes");
		return manager.createQuery("select s from Sessao s", Sessao.class).getResultList();
	}
	
	public Sessao buscarPeloId(Integer sessaoId) {
	    System.out.println("[INFO] Consultando Sessao pelo Id");

		Sessao sessao = manager.find(Sessao.class, sessaoId);
		
		return sessao;
	}
	
	public void atualizar(Sessao sessao) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(sessao);
		tx.commit();
	}
	
	public void excluir(Integer sessaoId) {
		Sessao sessao = buscarPeloId(sessaoId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(sessao);
		tx.commit();
	}

}
