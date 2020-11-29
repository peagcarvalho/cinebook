package br.edu.ifpb.cinebook.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.mindrot.jbcrypt.BCrypt;

import br.edu.ifpb.cinebook.modelo.Usuario;

@Stateless
public class UsuarioServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
		System.out.println("[INFO] UsuarioDao foi criado.");
	}
	
	public void cadastrar(Usuario usuario) {
		usuario.setSenha(transformarSenhaEmHash(usuario.getSenha()));
		
		manager.persist(usuario);
	}
	
	public void atualizar(Usuario usuario) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(usuario);
		tx.commit();
	}
	
	public Usuario buscarPeloId(String email) {
	    System.out.println("[INFO] Consultando Usuario pelo Id");

		Usuario usuario = manager.find(Usuario.class, email);
		
		return usuario;
	}
	
	public Usuario buscarPeloCPF(String clienteCPF) {
		System.out.println("[INFO] Consultando Cliente pelo CPF");

	    Query query = manager.createQuery("select u from Usuario u where u.cpf like " + clienteCPF);
	    
		Usuario usuario = (Usuario) query.getSingleResult();
		
		return usuario;
	}
	
	public void excluir(String email) {
		Usuario usuario = buscarPeloId(email);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(usuario);
		tx.commit();
	}
	
	public List<Usuario> todosUsuarios() {
	    System.out.println("[INFO] Consultando todos os autores ");
		return manager.createQuery("select u from Usuario u", Usuario.class).getResultList();
	}
	
	public List<Usuario> listarTodosClientes() {
	    System.out.println("[INFO] Consultando todos os clientes");
	    
	    List<Usuario> usuarios = todosUsuarios();
	    List<Usuario> clientes = new ArrayList<Usuario>();
	    
	    for(Usuario usuario : usuarios) {
	    	if (usuario.getPapeis().contains("Cliente") == true) {
	    		clientes.add(usuario);
	    	}
	    }
	    
		return clientes;
	}
	
	public List<Usuario> listarTodosFuncionarios() {
	    System.out.println("[INFO] Consultando todos os funcionarios");
		
		List<Usuario> usuarios = todosUsuarios();
	    List<Usuario> funcionarios = new ArrayList<Usuario>();
	    
	    for(Usuario usuario : usuarios) {
	    	if (usuario.getPapeis().contains("Operador") || usuario.getPapeis().contains("Gerente") == true) {
	    		funcionarios.add(usuario);
	    	}
	    }
	    
		return funcionarios;
	
	
	}
	
	private String transformarSenhaEmHash(String senhaBruta) {
		System.out.println("Gerando Hash usando Bcrypt");
		String senhaHashed = null;
		String salto = BCrypt.gensalt();
		
	    senhaHashed = BCrypt.hashpw(senhaBruta, salto);
	    
	    System.out.println("Bcrypt - senhaHash: " + senhaHashed);
		
		return senhaHashed;
	}
	
	public Usuario buscaPeloEmailESenha(String email, String senha) {
		System.out.println("[INFO] Consultando o usuario pelo e-mail: " + email);
	    
	    Usuario usuarioRecuperado = null; 
	    try {
	    
		    usuarioRecuperado = manager.createQuery("select u from Usuario u where u.email = :email",Usuario.class)
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
	
	private boolean verificaSenhaHash(String senha, String senhaRecuperada) {
		System.out.println("Checando senha...");
		return BCrypt.checkpw(senha, senhaRecuperada);
	}
	

}
