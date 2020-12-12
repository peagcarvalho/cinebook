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
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.Endereco;
import br.edu.ifpb.cinebook.servico.CinemaServico;

public class CinemaTest {
	
	CinemaServico servico;
	List<Cinema> cinemas;
	Cinema cinema1;
	Cinema cinema2;
	
	@Before
	public void inicializar() {
		servico = new CinemaServico();
		
		servico.manager = Mockito.mock(EntityManager.class);
		
		cinemas = new ArrayList<Cinema>();
		
		cinema1 = new Cinema();
		cinema1.setId(1);
		cinema1.setNome("Cinepipoca");
		cinema1.setTelefoneContato("38413780");
		cinema1.setEndereco(new Endereco("158", "Rua A", "Centro", "Sertânia", "PE"));
		
		cinema2 = new Cinema();
		cinema2.setId(2);
		cinema2.setNome("Cinemarca");
		cinema2.setTelefoneContato("38417051");
		cinema2.setEndereco(new Endereco("135", "Rua B", "Centro", "Monteiro", "PB"));
		
		cinemas.add(cinema1);
		cinemas.add(cinema2);
	}
	
	@Test
	public void testaListarTodosOsCinemas() {
		final String queryTexto = "select c from Cinema c";
		
		TypedQuery<Cinema> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(cinemas);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Cinema.class)).thenReturn(query);
		
		List<Cinema> cinemasBuscados = servico.listarTodosCinemas();
		
		assertEquals(2, cinemasBuscados.size());
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Cinema.class);
	}
	
	@Test
	public void testaBuscarCinemaPeloId() {
		final Integer cinemaId = 1;
		
		Mockito.when(servico.manager.find(Cinema.class, cinemaId)).thenReturn(cinema1);
		
		Cinema cinemaBuscado = servico.buscarPeloId(cinemaId);
		
		assertEquals("Cinepipoca", cinemaBuscado.getNome());
		Mockito.verify(servico.manager, Mockito.times(1)).find(Cinema.class, cinemaId);
	}
	
	@Test
	public void testaConcatenarEnderecoDoCinema() {
		final String endereco = "Rua A, 158. Centro - Sertânia, PE";
		
		cinema1.concatenarEndereco();
		
		assertEquals(endereco, cinema1.getEnderecoConcatenado());
	}
	
	@Test
	public void testaListarCinemasPorCidadeEEstado() {
		final String cidade = "Monteiro";
		final String estado = "PB";
		final String queryTexto = "select c from Cinema c";
		
		TypedQuery<Cinema> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(cinemas);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Cinema.class)).thenReturn(query);
		
		List<Cinema> cinemasBuscados = servico.listarCinemasPorCidadeEEstado(cidade, estado);
		
		assertEquals(1, cinemasBuscados.size());
		assertTrue(cinemasBuscados.contains(cinema2));
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Cinema.class);
	}

}
