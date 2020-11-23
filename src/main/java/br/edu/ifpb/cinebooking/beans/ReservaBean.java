package br.edu.ifpb.cinebooking.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebooking.modelo.Reserva;
import br.edu.ifpb.cinebooking.servico.BilheteriaServico;
import br.edu.ifpb.cinebooking.servico.ReservaServico;

@Named
@FlowScoped(value = "reserva")
public class ReservaBean {
	
	private Reserva reserva = new Reserva();
	private List<Reserva> reservas;
	@EJB
	private ReservaServico reservaServico;
	@EJB
	private BilheteriaServico bilheteriaServico;
	
	public void cadastrar() {
		
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

}
