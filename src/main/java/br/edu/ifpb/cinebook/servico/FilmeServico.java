package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import br.edu.ifpb.cinebook.modelo.Filme;

@DeclareRoles({"ADMINISTRADOR", "CLIENTE", "OPERADOR", "GERENTE"})
@Stateless
public class FilmeServico {
		
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] FilmeBean foi criado");
	}
	
	@RolesAllowed("ADMINISTRADOR")
	public void cadastrar(Filme filme) {
		System.out.println("[INFO] Salvando o Filme "  + filme.getTitulo());
		manager.persist(filme);
		System.out.println("[INFO] Salvou o Filme " + filme.getTitulo());
	}
	
	@PermitAll
	public List<Filme> listarTodosFilmes() {
	    System.out.println("[INFO] Consultando todos os filmes");
		return manager.createQuery("select f from Filme f", Filme.class).getResultList();
	}
	
	@PermitAll
	public Filme buscarPeloId(Integer filmeId) {
	    
		Query query = manager.createQuery("select f from Filme f inner join fetch f.generos where f.id = " + filmeId, Filme.class);
	    
		Filme filme = (Filme) query.getSingleResult();
		
		return filme;
	}
	
	@RolesAllowed("ADMINISTRADOR")
	public void atualizar(Filme filme) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(filme);
		tx.commit();
	}
	
	@RolesAllowed("ADMINISTRADOR")
	public void excluir(Integer filmeId) {
		Filme filme = buscarPeloId(filmeId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(filme);
		tx.commit();
	}
	
	@PermitAll
	public List<Filme> buscarPorPalavra(String string) {
		List<Filme> filmes = listarTodosFilmes();
		List<Filme> filmesResultado = new ArrayList<Filme>();
		
		for(int contador = 0; contador < filmes.size(); contador++) {
			Filme filme = filmes.get(contador);
			
			if (filme.getTitulo().contains(string) || filme.getSinopse().contains(string)) {
				filmesResultado.add(filme);
			}
		}
		
		return filmesResultado;
	}

}
