package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class PesquisasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String buscaFilme = "";
	
	public PesquisasBean() {
		
	}

	public String getBuscaFilme() {
		return buscaFilme;
	}

	public void setBuscaFilme(String buscaFilme) {
		this.buscaFilme = buscaFilme;
	}

}
