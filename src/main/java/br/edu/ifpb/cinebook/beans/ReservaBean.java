package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cartao;
import br.edu.ifpb.cinebook.modelo.Ingresso;
import br.edu.ifpb.cinebook.modelo.Reserva;
import br.edu.ifpb.cinebook.servico.ReservaServico;

@Named
@FlowScoped(value = "reserva")
public class ReservaBean implements Serializable{
	
	private Reserva reserva = new Reserva();
	private List<Reserva> reservas;
	private Ingresso ingresso;
	private List<Ingresso> ingressos;
	private Cartao cartao = new Cartao();
	@EJB
	private ReservaServico reservaServico;
	
	public void reservar() {
		reserva.setIngressos(ingressos);
		reserva.calcularValorTotal();
		reserva.setDataEmissao(new Date(System.currentTimeMillis()));
//		reserva.setCliente(); Adicionar cliente quando a função de login for criada
		
		if (validarPagamento() == true) {
			reservaServico.cadastrar(reserva);
			
			reserva = new Reserva();
		}
		
	}
	
	public void adicionarIngresso() {
		ingressos.add(ingresso);
	}
	
	public String retornarBandeiraDoCartao() {
		return "";
	}
	
	public boolean validarPagamento() {
		return false;
	}
	
	public Reserva getReserva() {
		return reserva;
	}
	
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

}
