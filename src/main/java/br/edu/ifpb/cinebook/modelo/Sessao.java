package br.edu.ifpb.cinebook.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sessao {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String sala;
	@Temporal(TemporalType.DATE)
	private Date dataExibicao;
	@Temporal(TemporalType.TIME)
	private Date horaExibicao;
	private boolean legendado;
	private boolean tresDimensoes;
	private int quantMaxIngressos;
	private int quantIngressosVendidos;
	@Column(columnDefinition = "boolean default false")
	private boolean esgotada;
	
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	@OneToMany(mappedBy = "sessao", cascade = CascadeType.REMOVE)
	private List<Ingresso> ingressos;
	
	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}
	
	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public Date getDataExibicao() {
		return dataExibicao;
	}
	
	public void setDataExibicao(Date dataExibicao) {
		this.dataExibicao = dataExibicao;
	}
	
	public Date getHoraExibicao() {
		return horaExibicao;
	}
	
	public void setHoraExibicao(Date horaExibicao) {
		this.horaExibicao = horaExibicao;
	}
	
	public boolean isLegendado() {
		return legendado;
	}
	
	public void setLegendado(boolean legendado) {
		this.legendado = legendado;
	}
	
	public boolean isTresDimensoes() {
		return tresDimensoes;
	}
	
	public void setTresDimensoes(boolean tresDimensoes) {
		this.tresDimensoes = tresDimensoes;
	}

	public boolean isEsgotada() {
		return esgotada;
	}

	public void setEsgotada(boolean esgotada) {
		this.esgotada = esgotada;
	}

	public int getQuantIngressosVendidos() {
		return quantIngressosVendidos;
	}

	public void setQuantIngressosVendidos(int quantIngressosVendidos) {
		this.quantIngressosVendidos = quantIngressosVendidos;
	}

	public int getQuantMaxIngressos() {
		return quantMaxIngressos;
	}

	public void setQuantMaxIngressos(int quantMaxIngressos) {
		this.quantMaxIngressos = quantMaxIngressos;
	}

	public Cinema getCinema() {
		return cinema;
	}

	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}

}