package br.edu.ifpb.cinebooking.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity(name = "funcionario")
@DiscriminatorValue("Funcionario")
public class Funcionario extends Usuario {
	
	@Enumerated(EnumType.STRING)
	private TipoFuncionario cargo;
	
	@ManyToOne
	private Cinema cinema;
	
	public TipoFuncionario getCargo() {
		return cargo;
	}
	
	public void setCargo(TipoFuncionario cargo) {
		this.cargo = cargo;
	}

}
