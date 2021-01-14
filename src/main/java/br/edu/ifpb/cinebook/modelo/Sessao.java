package br.edu.ifpb.cinebook.modelo;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
	@Column(columnDefinition = "boolean default false")
	private boolean legendado;
	@Column(columnDefinition = "boolean default false")
	private boolean tresDimensoes;
	private int quantMaxIngressos;
	private int quantIngressosVendidos;
	@Column(columnDefinition = "boolean default false")
	private boolean esgotada;
	
	@ManyToOne
	@JoinColumn(name = "cinema_id")
	private Cinema cinema;
	
	@OneToMany(mappedBy = "sessao")
	private List<Reserva> reservas;
	
	@ManyToOne
	@JoinColumn(name = "filme_id")
	private Filme filme;
	
	@Transient
	private String tipoSessao;
	
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

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public String getTipoSessao() {
		String tipo = "";
		
		if (legendado == true) {
			tipo += "LEG ";
		} else {
			tipo += "DUB ";
		}
		
		if (tresDimensoes == true) {
			tipo += "3D ";
		} else {
			tipo += "2D ";
		}
		
		return tipo;
	}

}