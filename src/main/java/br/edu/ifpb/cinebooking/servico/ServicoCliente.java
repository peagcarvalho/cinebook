package br.edu.ifpb.cinebooking.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import br.edu.ifpb.cinebooking.modelo.Cliente;

@Stateless
@LocalBean
public class ServicoCliente {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] ClienteBean foi criado");
	}
	
	public void cadastrar(Cliente cliente) {
		System.out.println("[INFO] Salvando o Cliente "  + cliente.getNome());
		manager.persist(cliente);
		System.out.println("[INFO] Salvou o Cliente " + cliente.getNome());
	}
	
	public List<Cliente> listarTodosClientes() {
	    System.out.println("[INFO] Consultando todos os clientes");
		return manager.createQuery("select c from Cliente c", Cliente.class).getResultList();
	}
	
	public Cliente buscarPeloCPF(String clienteCPF) {
	    System.out.println("[INFO] Consultando Cliente pelo CPF");

	    Query query = manager.createQuery("select c from Cliente c where c.cpf like " + clienteCPF);
	    
		Cliente cliente = (Cliente) query.getSingleResult();
		
		return cliente;
	}

}
