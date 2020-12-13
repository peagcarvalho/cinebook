package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.servico.CinemaServico;

@FacesConverter(managed = true, forClass = Cinema.class)
public class CinemaConverter implements Converter<Cinema> {
	
	@Inject
	private CinemaServico servico;

	@Override
	public Cinema getAsObject(FacesContext context, UIComponent component, String idCinema) {
		if (idCinema == null || idCinema.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idCinema);
			
			Cinema cinema = servico.buscarPeloId(id);
			return cinema;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Cinema com ID inválido"), e);
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Cinema cinema) {
		if (cinema == null) {
			return "";
		}
		
		if (cinema.getId() != null) {
			return cinema.getId().toString();
		} else {
			throw new ConverterException(new FacesMessage("Cinema com ID inválido"), null);
		}
	}

}