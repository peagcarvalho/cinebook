package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Sessao;
import br.edu.ifpb.cinebook.servico.SessaoServico;

@Named
@ViewScoped
public class SessaoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Sessao sessao = new Sessao();
	private List<Sessao> sessoes;
	private Filme filme = new Filme();
	@EJB
	private SessaoServico servico;
	
	@PostConstruct
	public void init() {
	}
	
	public void cadastrar() {
		servico.cadastrar(sessao);
		sessoes.add(sessao);
		
		sessao = new Sessao();
	}
	
	public void listarTodasSessoes() {
		sessoes = servico.listarTodasSessoes();
	}
	
	public void editar() {
		servico.atualizar(sessao);
	}
	
	public Sessao getSessao() {
		return sessao;
	}
	
	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

}
