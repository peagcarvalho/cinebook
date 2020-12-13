package br.edu.ifpb.cinebook.beans;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import br.edu.ifpb.cinebook.modelo.Sessao;
import br.edu.ifpb.cinebook.servico.SessaoServico;

@FacesConverter(managed = true, forClass = Sessao.class)
public class SessaoConverter implements Converter<Sessao> {
	
	@Inject
	private SessaoServico servico;

	@Override
	public Sessao getAsObject(FacesContext context, UIComponent component, String sessaoId) {
		if (sessaoId == null || sessaoId.isEmpty()) {
			return null;
		}
		
		try {
			Integer id = Integer.parseInt(sessaoId);
			
			Sessao sessao = servico.buscarPeloId(id);
			return sessao;
		} catch (NumberFormatException e) {
			throw new ConverterException(new FacesMessage("Sessão com id inválido"), e);
		}
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Sessao sessao) {
		if (sessao == null) {
			return "";
		}
		
		if (sessao.getId() != null) {
			return sessao.getId().toString();
		} else {
			throw new ConverterException(new FacesMessage("Sessão com id inválido"), null);
		}
	}

}
