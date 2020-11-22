package br.edu.ifpb.cinebooking.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

@Entity
public class Filme {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	@Lob
	private byte[] capa;
	@Temporal(TemporalType.TIME)
	private Date duracao;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private TipoClassificacao classificacao;
	@Temporal(TemporalType.DATE)
	private Date dataLancamento;
	@ElementCollection
	@CollectionTable(name = "filme_diretor", joinColumns = @JoinColumn(name = "filme_id"))
	@Column(name = "diretor")
	private List<String> diretores;
	
	@ElementCollection
	@CollectionTable(name = "filme_genero", joinColumns = @JoinColumn(name = "filme_id"))
	@Column(name = "genero")
	private List<String> generos;
	
	@ElementCollection
	@CollectionTable(name = "filme_palavras_chave", joinColumns = @JoinColumn(name = "filme_id"))
	@Column(name = "palavras_chave")
	private List<String> palavrasChave;
	
	@OneToMany(mappedBy = "filme", fetch = FetchType.EAGER)
	private List<Sessao> sessoes;
	
	public Filme() {}
	
	public void adicionarDiretor(String diretor) {
		diretores.add(diretor);
	}
	
	public void adicionarGenero(String genero) {
		generos.add(genero);
	}
	
	public void adicionarPalavraChave(String palavraChave) {
		palavrasChave.add(palavraChave);
	}
	
	public String concatenarDiretores() {
		String todosDiretores = diretores.get(0);
		
		if (diretores.size() > 1) {
			for (int contador = 1; contador < diretores.size(); contador++) {
				todosDiretores += ", " + diretores.get(contador);
			}
		}
		
		return todosDiretores;
	}
	
	public String concatenarGeneros() {
		String todosGeneros = generos.get(0);
		
		if (generos.size() > 1) {
			for (int contador = 1; contador < generos.size(); contador++) {
				todosGeneros += ", " + generos.get(contador);
			}
		}
		
		return todosGeneros;
	}
	
	public String concatenarPalavrasChave() {
		String todasPalavrasChave = generos.get(0);
		
		if (palavrasChave.size() > 1) {
			for (int contador = 1; contador < palavrasChave.size(); contador++) {
				todasPalavrasChave += ", " + palavrasChave.get(contador);
			}
		}
		
		return todasPalavrasChave;
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

	public byte[] getCapa() {
		return capa;
	}

	public void setCapa(byte[] capa) {
		this.capa = capa;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public TipoClassificacao getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(TipoClassificacao classificacao) {
		this.classificacao = classificacao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public List<String> getDiretores() {
		return diretores;
	}

	public void setDiretores(List<String> diretores) {
		this.diretores = diretores;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public List<String> getPalavrasChave() {
		return palavrasChave;
	}

	public void setPalavrasChave(List<String> palavrasChave) {
		this.palavrasChave = palavrasChave;
	}
	
}
