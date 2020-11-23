package br.edu.ifpb.cinebooking.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebooking.modelo.Cliente;
import br.edu.ifpb.cinebooking.servico.ClienteServico;

@Named
@RequestScoped
public class ClienteBean {
	
	private Cliente cliente = new Cliente();
	@EJB
	private ClienteServico servico;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public boolean cadastrar() {
		System.out.println("[INFO] Salvando Cliente: " + cliente.getNome());
		
		try {
			servico.cadastrar(cliente);
			
			cliente = new Cliente();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}