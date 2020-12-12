package unitario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.servico.FilmeServico;

public class FilmeTest {
	
	FilmeServico servico;
	List<Filme> filmes;
	Filme filme1;
	Filme filme2;
	
	@Before
	public void inicializar() {
		servico = new FilmeServico();
		filmes = new ArrayList<Filme>();
		
		servico.manager = Mockito.mock(EntityManager.class);
		
		filme1 = new Filme();
		filme1.setId(1);
		filme1.setTitulo("Vingadores: Ultimato");
		filme1.setSinopse("Vingadores: Ultimato é um filme de super-herói estadunidense de 2019, "
						+ "baseado na equipe Os Vingadores da Marvel Comics.");
		
		List<String> generos1 = new ArrayList<String>();
		generos1.add("Aventura");
		generos1.add("Ficção Científica");
		
		filme1.setGeneros(generos1);
		
		
		filme2 = new Filme();
		filme2.setId(2);
		filme2.setTitulo("Trolls 2");
		filme1.setSinopse("Em Trolls 2, a rainha Poppy e Branch fazem uma descoberta surpreendente: há outros"
				+ "		  mundos Troll além do seu, e suas diferenças criam grandes confrontos entre essas diversas tribos.");
		
		List<String> generos2 = new ArrayList<String>();
		generos2.add("Animação");
		generos2.add("Comédia");
		
		filme2.setGeneros(generos2);
		
		filmes.add(filme1);
		filmes.add(filme2);
	}
	
	@Test
	public void testaListarTodosOsFilmes() {
		final String queryTexto = "select f from Filme f";
		
		TypedQuery<Filme> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(filmes);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Filme.class)).thenReturn(query);
		
		List<Filme> filmesBuscados = servico.listarTodosFilmes();
		
		assertEquals(2, filmesBuscados.size());
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Filme.class);
	}
	
	@Test
	public void testaBuscarFilmePeloId() {
		final Integer filmeId = 2;
		final String queryTexto = "select f from Filme f inner join fetch f.generos where f.id = " + filmeId;
		
		TypedQuery<Filme> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getSingleResult()).thenReturn(filme2);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Filme.class)).thenReturn(query);
		
		Filme filmeBuscado = servico.buscarPeloId(filmeId);
		
		assertEquals(filme2.getTitulo(), filmeBuscado.getTitulo());
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Filme.class);
	}
	
	@Test
	public void testaBuscarFilmePorFiltroDePesquisa() {
		final String textoBusca = "Vingadores";
		final String queryTexto = "select f from Filme f";
		
		TypedQuery<Filme> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(filmes);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Filme.class)).thenReturn(query);
		
		List<Filme> filmesBuscados = servico.buscarPorPalavra(textoBusca);
		
		assertEquals(1, filmesBuscados.size());
		assertTrue(filmesBuscados.contains(filme1));
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Filme.class);
		
	}
	
	@Test
	public void testarConcatenarGeneros() {
		final String generos = "Animação, Comédia";
		
		filme2.concatenarGeneros();
		
		assertEquals(generos, filme2.getGenerosConcatenados());
	}

}