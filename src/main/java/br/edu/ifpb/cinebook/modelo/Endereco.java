package br.edu.ifpb.cinebook.modelo;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Endereco implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cidade;
	private String estado;
	private String bairro;
	private String logradouro;
	private String numero;
	
	public Endereco() {}
	
	public Endereco(String cidade, String estado) {
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public Endereco(String numero, String logradouro, String bairro, String cidade, String estado) {
		this.numero = numero;
		this.logradouro = logradouro;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}
	
	public String toString() {
		return logradouro + ", " + numero + ". " + bairro + ", " + cidade + " - " + estado; 
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
