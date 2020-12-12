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
	private boolean legendado;
	private boolean tresDimensoes;
	private String quantMaxIngressos;
	@EJB
	private SessaoServico servico;
	@EJB
	private FilmeServico filmeServico;
	@Inject
	private LoginBean loginBean;
	
	@PostConstruct
	public void init() {
		filmes = filmeServico.listarTodosFilmes();
		sessoes = new ArrayList<Sessao>();
	}
	
	public String cadastrar() {
		sessao.setQuantMaxIngressos(Integer.parseInt(quantMaxIngressos));
		sessao.setLegendado(legendado);
		sessao.setTresDimensoes(tresDimensoes);
		
		Filme filme = new Filme();
		filme.setId(filmeId);
		sessao.setFilme(filme);
		
		Cinema cinema = new Cinema();
		cinema.setId(cinemaId);
		sessao.setCinema(cinema);
		
		servico.cadastrar(sessao);
		
		sessao = new Sessao();
		
		return "/operador/listaDeSessoes.xhtml?faces-redirect=true";
	}
	
	public String editar() {
		sessao.setQuantMaxIngressos(Integer.parseInt(quantMaxIngressos));
		sessao.setLegendado(legendado);
		sessao.setTresDimensoes(tresDimensoes);
		
		if (Integer.parseInt(quantMaxIngressos) - sessao.getQuantIngressosVendidos() > 0) {
			sessao.setEsgotada(false);
		}
		
		servico.atualizar(sessao);
		
		return "/operador/listaDeSessoes.xhtml?faces-redirect=true";
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

	public String getQuantMaxIngressos() {
		return quantMaxIngressos;
	}

	public void setQuantMaxIngressos(String quantMaxIngressos) {
		this.quantMaxIngressos = quantMaxIngressos;
	}

}
