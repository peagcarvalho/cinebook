package br.edu.ifpb.cinebooking.beans;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebooking.modelo.Cliente;
import br.edu.ifpb.cinebooking.servico.ServicoCliente;

@Named
@RequestScoped
public class ClienteBean implements Serializable {
	
	private static final long serialVersionUID = 6101032576115231144L;
	private Cliente cliente = new Cliente();
	@EJB
	private ServicoCliente servico;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void cadastrar() {
		System.out.println("[INFO] Salvando Cliente: " + cliente.getNome());
		
		servico.cadastrar(cliente);
		cliente = new Cliente();
	}

}