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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ifpb.cinebook.modelo.Filme;

@DeclareRoles({"ADMINISTRADOR", "CLIENTE", "OPERADOR", "GERENTE"})
@Stateless
public class FilmeServico {
		
	@PersistenceContext
	public EntityManager manager;
	
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
	    
	    TypedQuery<Filme> query = manager.createQuery("select f from Filme f", Filme.class);
	    
	    List<Filme> filmes = query.getResultList();
	    
		return filmes;
	}
	
	@PermitAll
	public Filme buscarPeloId(Integer filmeId) {
	    
		Query query = manager.createQuery("select f from Filme f inner join fetch f.generos where f.id = " + filmeId, Filme.class);
	    
		Filme filme = (Filme) query.getSingleResult();
		
		System.out.println("Filme consultado: " + filme.getTitulo());
		
		return filme;
	}
	
	@RolesAllowed("ADMINISTRADOR")
	public void atualizar(Filme filme) {
		System.out.println("Editando filme " + filme.getTitulo());
		
		manager.merge(filme);
		
		System.out.println("Filme editado: " + filme.getTitulo());
	}
	
	@RolesAllowed("ADMINISTRADOR")
	public void excluir(Integer filmeId) {
		Filme filme = buscarPeloId(filmeId);
		
		manager.remove(filme);
	}
	
	@PermitAll
	public List<Filme> listarFilmesEmCartaz() {
		System.out.println("Consultando filmes em Cartaz");
		TypedQuery<Filme> query = manager.createQuery("select f from Filme f", Filme.class);
	    
	    List<Filme> filmes = query.getResultList();
	    List<Filme> filmesEmCartaz = new ArrayList<Filme>();
	    
	    for (Filme filme : filmes) {
	    	if (filme.getSessoes().size() > 0) {
	    		filmesEmCartaz.add(filme);
	    	}
	    }
	    
	    return filmesEmCartaz;
	}
	
	@PermitAll
	public List<Filme> buscarPorPalavra(String string) {
		List<Filme> filmes = listarTodosFilmes();
		List<Filme> filmesResultado = new ArrayList<Filme>();
		
		for(int contador = 0; contador < filmes.size(); contador++) {
			Filme filme = filmes.get(contador);
			
			System.out.println(filme.getTitulo());
			
			String titulo = filme.getTitulo();
			
			if (titulo.contains(string)) {
				filmesResultado.add(filme);
			}
		}
		
		return filmesResultado;
	}

}
