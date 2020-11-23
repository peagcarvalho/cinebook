package br.edu.ifpb.cinebooking.beans;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.edu.ifpb.cinebooking.modelo.Ingresso;
import br.edu.ifpb.cinebooking.servico.BilheteriaServico;

@Named
@SessionScoped
public class BilheteriaBean {
	
	private Ingresso ingresso = new Ingresso();
	@EJB
	private BilheteriaServico servico;
	
	public void cadastrar() {
		
	}
	
	public Ingresso getIngresso() {
		return ingresso;
	}
	
	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

}
