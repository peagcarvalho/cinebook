package unitario;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import br.edu.ifpb.cinebook.modelo.Usuario;
import br.edu.ifpb.cinebook.servico.UsuarioServico;

public class UsuarioTest {
	
	UsuarioServico servico;
	List<Usuario> usuarios;
	Usuario usuario1;
	Usuario usuario2;
	Usuario usuario3;
	
	@Before
	public void inicializar() {
		servico = new UsuarioServico();
		
		servico.manager = Mockito.mock(EntityManager.class);
		
		usuarios = new ArrayList<Usuario>();
		
		usuario1 = new Usuario();
		usuario1.setNome("Marcos");
		usuario1.setEmail("marcos@gmail.com");
		usuario1.setSenha("marcos123");
		usuario1.setPapeis(new ArrayList<String>());
		usuario1.adicionarPapel("GERENTE");
		
		usuario2 = new Usuario();
		usuario2.setNome("Lucia");
		usuario2.setEmail("Lucia@gmail.com");
		usuario2.setSenha("lucia123");
		usuario2.setPapeis(new ArrayList<String>());
		usuario2.adicionarPapel("OPERADOR");
		
		usuario3 = new Usuario();
		usuario3.setNome("Marta");
		usuario3.setEmail("marta@gmail.com");
		usuario3.setSenha("$2a$10$kyd19qHv95n2QR40a2PHoO.EfeiSTe80gR95pIfYF1cyfgs/kL/y63"); // Senha: marta123
		usuario3.setCpf("111.111.111-11");
		usuario3.setPapeis(new ArrayList<String>());
		usuario3.adicionarPapel("CLIENTE");
		
		usuarios.add(usuario1);
		usuarios.add(usuario2);
		usuarios.add(usuario3);
	}
	
	@Test
	public void testaListarTodosOsUsuarios() {
		final String queryTexto = "select u from Usuario u";
		
		TypedQuery<Usuario> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(usuarios);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Usuario.class)).thenReturn(query);
		
		List<Usuario> usuariosBuscados = servico.todosUsuarios();
		
		assertEquals(usuarios, usuariosBuscados);
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Usuario.class);
	}
	
	@Test
	public void testarListarSomenteFuncionarios() {
		final String queryTexto = "select u from Usuario u";
		
		TypedQuery<Usuario> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(usuarios);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Usuario.class)).thenReturn(query);
		
		List<Usuario> funcionarios = servico.listarTodosFuncionarios();
		
		assertFalse(funcionarios.contains(usuario3));
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Usuario.class);
	}
	
	@Test
	public void testarListarSomenteClientes() {
		final String queryTexto = "select u from Usuario u";
		
		TypedQuery<Usuario> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.getResultList()).thenReturn(usuarios);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Usuario.class)).thenReturn(query);
		
		List<Usuario> clientes = servico.listarTodosClientes();
		
		assertFalse(clientes.contains(usuario1));
		assertFalse(clientes.contains(usuario2));
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Usuario.class);
	}
	
	@Test
	public void testaBuscarPeloEmail() {
		final String email = "marcos@gmail.com";
		
		Mockito.when(servico.manager.find(Usuario.class, email)).thenReturn(usuario1);
		
		Usuario usuarioBuscado = servico.buscarPeloId(email);
		
		assertEquals(usuario1.getNome(), usuarioBuscado.getNome());
		Mockito.verify(servico.manager, Mockito.times(1)).find(Usuario.class, email);
	}
	
	@Test
	public void testaBuscarPeloCPF() {
		final String cpf = "111.111.111-11";
		final String queryTexto = "select u from Usuario u where u.cpf like " + cpf;
		
		Query query = Mockito.mock(Query.class);
		Mockito.when(query.getSingleResult()).thenReturn(usuario3);
		
		Mockito.when(servico.manager.createQuery(queryTexto)).thenReturn(query);
		
		Usuario usuarioBuscado = servico.buscarPeloCPF(cpf);
		
		assertEquals(usuario3.getNome(), usuarioBuscado.getNome());
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto);
	}
	
	/*@Test
	public void testaBuscarPeloEmailESenhaParaLogin() {
		final String email = "marta@gmail.com";
		final String senha = "marta123";
		final String queryTexto = "select u from Usuario u where u.email = :email";
		
		TypedQuery<Usuario> query = Mockito.mock(TypedQuery.class);
		Mockito.when(query.setParameter("email", email)).thenReturn(query);
		Mockito.when(query.getSingleResult()).thenReturn(usuario3);
		
		Mockito.when(servico.manager.createQuery(queryTexto, Usuario.class)).thenReturn(query);
		
		Usuario usuarioBuscado = servico.buscaPeloEmailESenha(email, senha);
		
		assertEquals(usuario3.getNome(), usuarioBuscado.getNome());
		Mockito.verify(servico.manager, Mockito.times(1)).createQuery(queryTexto, Usuario.class);
	}*/
	
	@Test
	public void testaRetornarPapelDoUsuario() {
		final String email = "lucia@gmail.com";
		
		Mockito.when(servico.manager.find(Usuario.class, email)).thenReturn(usuario2);
		
		String papelUsuario = servico.retornarPapelDoUsuario(email);
		
		assertEquals("OPERADOR", papelUsuario);
		Mockito.verify(servico.manager, Mockito.times(1)).find(Usuario.class, email);
		
	}

	@Test
	public void testaTransformarSenhaEmHash() {
		String senha = "senha123";
		
		String senhaHash = servico.transformarSenhaEmHash(senha);
		
		assertTrue(servico.verificaSenhaHash(senha, senhaHash));
	}
	
}
