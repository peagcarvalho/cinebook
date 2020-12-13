package br.edu.ifpb.cinebook.modelo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Reserva {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private int quantIngressos;
	private float valorTotal;
	private Date dataEmissao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(name = "sessao_id")
	private Sessao sessao;
	
	@OneToMany(mappedBy = "reserva", cascade = CascadeType.PERSIST)
	private List<Ingresso> ingressos;
	
	public Reserva() {
		
	}
	
	public void adicionarIngresso(Ingresso ingresso) {
		ingressos.add(ingresso);
	}
	
	public float calcularValorTotal() {
		float valorTotal = 0;
		
		for (int contador = 0; contador < ingressos.size(); contador++) {
			valorTotal += ingressos.get(contador).getValor();
		}
		
		return valorTotal;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public int getQuantIngressos() {
		return quantIngressos;
	}

	public void setQuantIngressos(int quantIngressos) {
		this.quantIngressos = quantIngressos;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}
	
}