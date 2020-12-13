package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.mindrot.jbcrypt.BCrypt;
import br.edu.ifpb.cinebook.modelo.Usuario;

@Stateless
public class UsuarioServico {
	
	@PersistenceContext
	public EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
		System.out.println("[INFO] UsuarioDao foi criado.");
	}
	
	public void cadastrar(Usuario usuario) {
		usuario.setSenha(transformarSenhaEmHash(usuario.getSenha()));
		
		manager.persist(usuario);
	}
	
	public void atualizar(Usuario usuario) {
		String senhaHashed = transformarSenhaEmHash(usuario.getSenha());
		
		usuario.setSenha(senhaHashed);
		
		manager.merge(usuario);
	}
	
	public Usuario buscarPeloId(String email) {
	    System.out.println("[INFO] Consultando Usuario pelo Id");

		Usuario usuario = manager.find(Usuario.class, email);
		
		System.out.println("Usuario buscado: " + usuario.getNome());
		
		return usuario;
	}
	
	public Usuario buscarPeloCPF(String clienteCPF) {
		System.out.println("[INFO] Consultando Cliente pelo CPF");

	    Query query = manager.createQuery("select u from Usuario u where u.cpf like " + clienteCPF);
	    
		Usuario usuario = (Usuario) query.getSingleResult();
		
		System.out.println("Usuario buscado: " + usuario.getNome());
		
		return usuario;
	}
	
	public void excluir(String email) {
		Usuario usuario = buscarPeloId(email);
		
		manager.remove(usuario);
	}
	
	public List<Usuario> todosUsuarios() {
	    System.out.println("[INFO] Consultando todos os usuarios");
	    
	    TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u inner join fetch u.papeis", Usuario.class);
	    
	    List<Usuario> usuarios = query.getResultList();
	    
		return usuarios;
	}
	
	public List<Usuario> listarTodosClientes() {
	    System.out.println("[INFO] Consultando todos os clientes");
	    
	    List<Usuario> usuarios = todosUsuarios();
	    List<Usuario> clientes = new ArrayList<Usuario>();
	    
	    for(Usuario usuario : usuarios) {
	    	if (usuario.getPapeis().contains("CLIENTE") == true) {
	    		clientes.add(usuario);
	    	}
	    }
	    
		return clientes;
	}
	
	public List<Usuario> listarTodosFuncionarios() {
	    System.out.println("[INFO] Consultando todos os funcionarios");
		
	    TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u inner join fetch u.papeis", Usuario.class);
	    List<Usuario> usuarios = query.getResultList();
	    List<Usuario> funcionarios = new ArrayList<Usuario>();
	    
	    for(Usuario usuario : usuarios) {
	    	if (usuario.getPapeis().contains("OPERADOR") || usuario.getPapeis().contains("GERENTE")) {
	    		funcionarios.add(usuario);
	    	}
	    }
	    
		return funcionarios;
	}
	
	public List<Usuario> listarTodosOsGerentes() {
	    System.out.println("[INFO] Consultando todos os gerentes");
		
	    TypedQuery<Usuario> query = manager.createQuery("select u from Usuario u inner join fetch u.papeis", Usuario.class);
	    List<Usuario> usuarios = query.getResultList();
	    List<Usuario> funcionarios = new ArrayList<Usuario>();
	    
	    for(Usuario usuario : usuarios) {
	    	if (usuario.getPapeis().contains("GERENTE")) {
	    		funcionarios.add(usuario);
	    	}
	    }
	    
		return funcionarios;
	}
	
	public List<Usuario> listarTodosFuncionarios(Integer cinemaId) {
	    System.out.println("[INFO] Consultando todos os funcionarios do cinema");
		
	    List<Usuario> funcionarios = listarTodosFuncionarios();
	    List<Usuario> funcionariosDoCinema = new ArrayList<Usuario>();
	    
	    for(Usuario funcionario : funcionarios) {
    		if (funcionario.getCinema().getId() == cinemaId) {
    			funcionariosDoCinema.add(funcionario);
    		}
	    }
	    
		return funcionariosDoCinema;
	
	}
	
	public String transformarSenhaEmHash(String senhaBruta) {
		System.out.println("Gerando Hash usando Bcrypt");
		String senhaHashed = null;
		String salto = BCrypt.gensalt();
		
	    senhaHashed = BCrypt.hashpw(senhaBruta, salto);
	    
	    System.out.println("Bcrypt - senhaHash: " + senhaHashed);
		
		return senhaHashed;
	}
	
	public String retornarPapelDoUsuario(String email) {
		Usuario usuario = buscarPeloId(email);
		
		String papel = usuario.getPapeis().get(0);
		
		return papel;
	}
	
	public Usuario buscaPeloEmailESenha(String email, String senha) {
		System.out.println("[INFO] Consultando o usuario pelo e-mail: " + email);
	    
	    Usuario usuarioRecuperado = null; 
	    try {
	    
		    usuarioRecuperado = manager.createQuery("select u from Usuario u where u.email = :email", Usuario.class)
			.setParameter("email", email)
			.getSingleResult();
	    
	    }catch (NoResultException nre){
	    	return null;
	    }
	    
	    if (usuarioRecuperado!=null) {
			if(verificaSenhaHash(senha, usuarioRecuperado.getSenha())) {
				return usuarioRecuperado;
			}
			
		}
	    
		return null; 
	}
	
	public boolean verificaSenhaHash(String senha, String senhaRecuperada) {
		System.out.println("Checando senha...");
		return BCrypt.checkpw(senha, senhaRecuperada);
	}
	
}
