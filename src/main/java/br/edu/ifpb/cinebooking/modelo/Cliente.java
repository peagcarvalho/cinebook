package br.edu.ifpb.cinebooking.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity(name = "cliente")
@DiscriminatorValue("Cliente")
public class Cliente extends Usuario {

	@Column(nullable = false)
	private String cpf;
	
	@Embedded
	private Endereco endereco;
	
	@OneToMany(mappedBy = "cliente")
	private List<Reserva> reservas;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
