package br.edu.ifpb.cinebook.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Sessao;

@Stateless
public class SessaoServico {
	
	@PersistenceContext
	public EntityManager manager;
	
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
	    
	    TypedQuery<Sessao> query = manager.createQuery("select s from Sessao s", Sessao.class);

	    List<Sessao> sessoes = query.getResultList();
	    
		return sessoes;
	}
	
	public List<Sessao> listarTodasSessoesAbertas() {
	    System.out.println("[INFO] Consultando todas as sessoes abertas (não-esgotadas)");
	    
	    TypedQuery<Sessao> query = manager.createQuery("select s from Sessao s where esgotada like 0", Sessao.class);

	    List<Sessao> sessoes = query.getResultList();
	    
		return sessoes;
	}
	
	public List<Sessao> filtrarSessoesPorFilme(Filme filme) {
		System.out.println("[INFO] Consultando todas as sessoes de um filme");
	    
	    TypedQuery<Sessao> query = manager.createQuery("select s from Sessao s where filme_id = :filmeId and esgotada like 0 order by dataExibicao desc", Sessao.class);
	    query.setParameter("filmeId", filme.getId());

	    List<Sessao> sessoes = query.getResultList();
	    
		return sessoes;
	}
	
	public List<Sessao> filtrarSessoesPorCinema(Integer cinemaId) {
		System.out.println("[INFO] Consultando todas as sessoes de um cinema");
	    
	    TypedQuery<Sessao> query = manager.createQuery("select s from Sessao s where cinema_id = :cinemaId order by dataExibicao desc", Sessao.class);
	    query.setParameter("cinemaId", cinemaId);

	    List<Sessao> sessoes = query.getResultList();
	    
		return sessoes;
	}
	
	public Sessao buscarPeloId(Integer sessaoId) {
	    System.out.println("[INFO] Consultando Sessao pelo Id");

		Sessao sessao = manager.find(Sessao.class, sessaoId);
		
		return sessao;
	}
	
	public void atualizar(Sessao sessao) {
		manager.merge(sessao);
	}
	
	public void excluir(Integer sessaoId) {
		Sessao sessao = buscarPeloId(sessaoId);
		
		manager.remove(sessao);
	}

}
