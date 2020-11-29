package br.edu.ifpb.cinebook.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "cinema")
public class Cinema {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Embedded
	private Endereco endereco = new Endereco();
	private String telefoneContato;
	private String nome;

	@OneToMany(mappedBy = "cinema")
	private List<Usuario> funcionarios;
	
	@OneToMany(mappedBy = "cinema", cascade = CascadeType.REMOVE)
	private List<Sessao> sessoes;

	@Transient
	private String enderecoConcatenado;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getTelefoneContato() {
		return telefoneContato;
	}

	public void setTelefoneContato(String telefoneContato) {
		this.telefoneContato = telefoneContato;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public List<Usuario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Usuario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getEnderecoConcatenado() {
		return enderecoConcatenado;
	}

	public void concatenarEndereco() {
		enderecoConcatenado = endereco.toString();
	}
	
}
