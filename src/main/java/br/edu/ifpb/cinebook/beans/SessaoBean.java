package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Sessao;
import br.edu.ifpb.cinebook.modelo.Usuario;
import br.edu.ifpb.cinebook.servico.FilmeServico;
import br.edu.ifpb.cinebook.servico.SessaoServico;

@Named
@ViewScoped
public class SessaoBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Sessao sessao = new Sessao();
	private List<Sessao> sessoes;
	private Filme filme = new Filme();
	private List<Filme> filmes;
	private Integer filmeId;
	private Integer cinemaId;
	
	@EJB
	private SessaoServico servico;
	@EJB
	private FilmeServico filmeServico;
	@Inject
	private LoginBean loginBean;
	
	public SessaoBean() {
		
	}
	
	@PostConstruct
	public void init() {
		sessao.setQuantMaxIngressos(0);
		filmes = filmeServico.listarTodosFilmes();
		sessoes = new ArrayList<Sessao>();
	}
	
	public String cadastrar() {
		Filme filme = new Filme();
		filme.setId(filmeId);
		sessao.setFilme(filme);
		
		Cinema cinema = new Cinema();
		cinema.setId(cinemaId);
		sessao.setCinema(cinema);
		
		servico.cadastrar(sessao);
		
		sessao = new Sessao();
		
		return "/operador/listaDeSessoes?faces-redirect=true";
	}
	
	public String editar() {
		servico.atualizar(sessao);
		
		return "/operador/listaDeSessoes?faces-redirect=true";
	}
	
	public void listarTodasSessoes() {
		sessoes = servico.filtrarSessoesPorFilme(filme);
	}
	
	public void recuperarCinemaId() {
		Usuario usuarioLogado = loginBean.getUsuarioLogado();
		
		if (usuarioLogado.getCinema() != null) {
			cinemaId = usuarioLogado.getCinema().getId();
		}
		
	}
	
	public void recuperarSessoesDoCinema() {
		this.setSessoes(servico.filtrarSessoesPorCinema(cinemaId));
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

	public Integer getCinemaId() {
		return cinemaId;
	}

	public void setCinemaId(Integer cinemaId) {
		this.cinemaId = cinemaId;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

}
