package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Reserva;
import br.edu.ifpb.cinebook.servico.ReservaServico;

@FacesConverter(managed = true, forClass = Reserva.class)
public class ReservaConverter implements Converter<Reserva> {
	
	@Inject
	private ReservaServico servico;

	@Override
	public Reserva getAsObject(FacesContext context, UIComponent component, String idReserva) {
		if (idReserva == null || idReserva.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idReserva);
			
			Reserva reserva = servico.buscarPeloId(id);
			return reserva;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Não é possível buscar reserva pelo id"), e);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Reserva reserva) {
		if (reserva == null) {
			return "";
		}
		
		if (reserva.getId() != null) {
			return Integer.toString(reserva.getId());
		} else {
			throw new ConverterException(new FacesMessage("Não é possível converter reserva em String. Id inválido"), null);
		}
	}

}
