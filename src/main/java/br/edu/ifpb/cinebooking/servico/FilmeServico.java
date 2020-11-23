package br.edu.ifpb.cinebooking.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebooking.modelo.Filme;

@Stateless
public class FilmeServico {
		
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] FilmeBean foi criado");
	}
	
	public void cadastrar(Filme filme) {
		System.out.println("[INFO] Salvando o Filme "  + filme.getTitulo());
		manager.persist(filme);
		System.out.println("[INFO] Salvou o Filme " + filme.getTitulo());
	}
	
	public List<Filme> listarTodosFilmes() {
	    System.out.println("[INFO] Consultando todos os filmes");
		return manager.createQuery("select f from Filme f", Filme.class).getResultList();
	}
	
	public Filme buscarPeloId(Integer filmeId) {
	    System.out.println("[INFO] Consultando Filme pelo Id");

		Filme filme = manager.find(Filme.class, filmeId);
		
		return filme;
	}
	
	public void atualizar(Filme filme) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(filme);
		tx.commit();
	}
	
	public void excluir(Integer filmeId) {
		Filme filme = buscarPeloId(filmeId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(filme);
		tx.commit();
	}

}
