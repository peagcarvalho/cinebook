package br.edu.ifpb.cinebook.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario {
	 
	@Id
	@Column(length = 150)
	private String email;
	@Column(nullable = false)
	private String senha;
	private String nome;
	@Temporal(value = TemporalType.DATE)
	private Date dataNascimento;
	@Column(length = 700)
	private String saltoHash;
	@Column(unique = true)
	private String cpf;
	
	@ManyToOne
	private Cinema cinema;
	
	@OneToMany(mappedBy = "cliente")
	private List<Reserva> reservas;
	
	@ElementCollection(targetClass = String.class,fetch = FetchType.EAGER)
	private List<String> papeis;
	
	public Usuario() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getSaltoHash() {
		return saltoHash;
	}

	public void setSaltoHash(String saltoHash) {
		this.saltoHash = saltoHash;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}
	
	public Set<String> getPapeisString() {
		return new HashSet<String>(papeis);
	}
	
	public List<String> getPapeis() {
		return papeis;
	}

	public void setPapeis(List<String> papeis) {
		this.papeis = papeis;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	
}
