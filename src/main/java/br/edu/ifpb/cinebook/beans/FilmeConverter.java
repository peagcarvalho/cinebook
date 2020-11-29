package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.servico.FilmeServico;

@FacesConverter(managed = true, forClass = Filme.class)
public class FilmeConverter implements Converter<Filme> {
	
	@Inject
	private FilmeServico servico;

	@Override
	public Filme getAsObject(FacesContext context, UIComponent component, String idFilme) {
		if (idFilme == null || idFilme.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(idFilme);
			
			Filme filme = servico.buscarPeloId(id);
			return filme;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Filme com ID inválido"), e);
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Filme filme) {
		if (filme == null) {
			return "";
		}
		
		if (filme.getId() != null) {
			return filme.getId().toString();
		} else {
			throw new ConverterException(new FacesMessage("Filme com ID inválido"), null);
		}
	}

}
