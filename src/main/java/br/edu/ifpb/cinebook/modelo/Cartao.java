package br.edu.ifpb.cinebook.modelo;

import java.util.Date;

public class Cartao {
	
	private String titular;
	private String agencia;
	private Long numero;
	private Date validade;
	private Integer cvv;
	
	public Long getNumero() {
		return numero;
	}
	
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	
	public Date getValidade() {
		return validade;
	}
	
	public void setValidade(Date validade) {
		this.validade = validade;
	}
	
	public Integer getCvv() {
		return cvv;
	}
	
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}
	
}
