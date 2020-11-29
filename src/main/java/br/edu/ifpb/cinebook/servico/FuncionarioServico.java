/*package br.edu.ifpb.cinebook.servico;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebook.modelo.Funcionario;

@Stateless
public class FuncionarioServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	public void aposCriacao() {
		System.out.println("[INFO] FuncionarioBean foi criado");
	}
	
	public void cadastrar(Funcionario funcionario) {
		System.out.println("[INFO] Salvando o Funcionario "  + funcionario.getNome());
		manager.persist(funcionario);
		System.out.println("[INFO] Salvou o Funcionario " + funcionario.getNome());
	}
	
	public List<Funcionario> listarTodosFuncionarios() {
	    System.out.println("[INFO] Consultando todos os funcionarios");
		return manager.createQuery("select f from Funcionario f", Funcionario.class).getResultList();
	}
	
	public Funcionario buscarPeloId(Integer funcId) {
	    System.out.println("[INFO] Consultando Funcionario pelo Id");

		Funcionario funcionario = manager.find(Funcionario.class, funcId);
		
		return funcionario;
	}
	
	public void atualizar(Funcionario funcionario) {
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.merge(funcionario);
		tx.commit();
	}
	
	public void excluir(Integer funcId) {
		Funcionario funcionario = buscarPeloId(funcId);
		
		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		
		manager.remove(funcionario);
		tx.commit();
	}

}*/