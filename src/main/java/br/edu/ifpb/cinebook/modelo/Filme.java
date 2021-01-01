package br.edu.ifpb.cinebook.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

@Entity
public class Filme {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String capa;
	@Column(length = 500)
	private String sinopse;
	private String classificacao;
	@Temporal(TemporalType.DATE)
	private Date anoLancamento;
	
	@ElementCollection
	@CollectionTable(name = "filme_genero", joinColumns = @JoinColumn(name = "filme_id"))
	@Column(name = "genero")
	private List<String> generos;
	
	@OneToMany(mappedBy = "filme", fetch = FetchType.EAGER)
	private List<Sessao> sessoes;
	
	@Transient
	private String generosConcatenados;
	
	public Filme() {}
	
	public void adicionarGenero(String genero) {
		generos.add(genero);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	
	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public Date getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Date anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public String getGenerosConcatenados() {
		return generosConcatenados;
	}
	
	public void concatenarGeneros() {
		if (getGeneros().size() > 0 && getGeneros() != null) {
			String generosConcatenados = getGeneros().get(0);
			
			for (int contador = 1; contador < getGeneros().size(); contador++) {
				generosConcatenados += ", " + getGeneros().get(contador);
			}
			
			this.generosConcatenados = generosConcatenados;
		}
	}
	
}
