/*package br.edu.ifpb.cinebook.beans;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cliente;
import br.edu.ifpb.cinebook.modelo.Funcionario;
import br.edu.ifpb.cinebook.servico.UsuarioServico;

@Named
@RequestScoped
public class FuncionarioBean {
	
	private Funcionario funcionario = new Funcionario();
	@EJB
	private UsuarioServico servico;
	
	public boolean cadastrar() {
		System.out.println("[INFO] Salvando Cliente: " + getFuncionario().getNome());
		
		try {
			servico.cadastrarFuncionario(getFuncionario());
			
			setFuncionario(new Funcionario());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}*/
