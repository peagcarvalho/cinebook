package br.edu.ifpb.cinebooking.servico;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import br.edu.ifpb.cinebooking.modelo.Ingresso;

@Singleton
public class BilheteriaServico {
	
	@PersistenceContext
	private EntityManager manager;
	
	private List<Ingresso> ingressos;
	
	@PostConstruct
	public void aposCriacao() {
		ingressos = new ArrayList<Ingresso>();
	}
	
	public void adicionarIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public void excluirIngresso(Integer ingressoId) {
		ingressos.removeIf(ingresso->ingresso.getId()==ingressoId);
	}
	
	public void excluirTodosIngressos() {
		ingressos = new ArrayList<Ingresso>();
	}
	
	public List<Ingresso> consultarTodosIngressos() {
		return ingressos;
	}
	
	public float calcularValorTotal() {
		float valorTotal = 0;
		
		for (int contador = 0; contador < ingressos.size(); contador++) {
			valorTotal += ingressos.get(contador).getValor();
		}
		
		return valorTotal;
	}

}
