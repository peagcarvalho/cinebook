package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Sessao;

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
	    System.out.println("[INFO] Consultando todas as sessoes não esgotadas");
	    
		return manager.createQuery("select s from Sessao s where esgotada like 0", Sessao.class).getResultList();
	}
	
	public List<Sessao> filtrarSessoesPorFilme(Filme filme) {
		List<Sessao> sessoes = listarTodasSessoes();
		List<Sessao> sessoesResultado = new ArrayList<Sessao>();
		
		for (int contador = 0; contador < sessoes.size(); contador++) {
			Sessao sessao = sessoes.get(contador);
			
			if (sessao.getFilme().getId() == filme.getId()) {
				sessoesResultado.add(sessao);
			}
		}
		
		return sessoesResultado;
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
