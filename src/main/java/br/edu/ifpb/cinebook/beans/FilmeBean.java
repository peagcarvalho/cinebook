package br.edu.ifpb.cinebook.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.Filme;
import br.edu.ifpb.cinebook.modelo.Sessao;
import br.edu.ifpb.cinebook.servico.FilmeServico;
import br.edu.ifpb.cinebook.servico.SessaoServico;

@Named
@ViewScoped
public class FilmeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Filme filme = new Filme();
	private Integer filmeId;
	private String buscaFilme;
	private String genero;
	private String generoConcatenado;
	
	private List<Filme> filmes;
	private List<String> generos;
	private List<Sessao> sessoes;
	
	@EJB
	private FilmeServico servico;
	@EJB
	private SessaoServico sessaoServico;
	@Inject
	private PesquisasBean pesquisaBean;
	@Inject
	private FacesContext facesContext;
	
	public FilmeBean() {
		
	}
	
	@PostConstruct
	public void init() {
		filmes = new ArrayList<Filme>();
		generos = new ArrayList<String>();
		buscaFilme = "";
	}
	
	public String cadastrar() {
		filme.setGeneros(generos);
		
		servico.cadastrar(filme);
		filmes.add(filme);
		
		filme = new Filme();
		generos = new ArrayList<String>();
		
		return "admin/painelAdmin.xhtml?faces-redirect=true";
	}
	
	public String editar() {
		filme.setGeneros(generos);
		
		servico.atualizar(filme);
		
		return "admin/listaDeFilmes.xhtml?faces-redirect=true";
	}
	
	public String excluir(Filme filme) {
		servico.excluir(filme.getId());
		filmes.remove(filme);
		
		return "admin/listaDeFilmes.xhtml?faces-redirect=true";
	}
	
	public void adicionarGenero() {
		generos.add(genero);
		
		genero = "";
	}
	
	public void removerGenero(String genero) {
		System.out.println(genero);
		generos.remove(genero);
	}
	
	public void carregarSessoesFilme() {
		if (filme.getSessoes() != null && !filme.getSessoes().isEmpty()) {
			sessoes = sessaoServico.filtrarSessoesPorFilme(filme);
		} else {
			sessoes = new ArrayList<Sessao>();
		}
	}
	
	public void concatenarGeneros() {
		if (filme.getGeneros().size() > 0 && filme.getGeneros() != null) {
			String generosConcatenados = filme.getGeneros().get(0);
			
			for (int contador = 1; contador < filme.getGeneros().size(); contador++) {
				generosConcatenados += ", " + filme.getGeneros().get(contador);
			}
			
			generoConcatenado = generosConcatenados;
		}
	}
	
	public void concatenarEnderecoCinema() {
		if (sessoes != null && sessoes.size() > 0) {
			for (int contador = 0; contador < sessoes.size(); contador++) {
				Cinema cinema = sessoes.get(contador).getCinema();
				
				String enderecoConcatenado = cinema.getEndereco().getLogradouro() + ", " + cinema.getEndereco().getNumero() + ". " + 
											 cinema.getEndereco().getBairro() + " - " + cinema.getEndereco().getCidade() + ", " + 
											 cinema.getEndereco().getEstado();
				
				sessoes.get(contador).getCinema().setEnderecoConcatenado(enderecoConcatenado);
				System.out.println(enderecoConcatenado);
			}
			
		}
	}
	
	public void preencherTabelaGeneros() {
		if (filme.getGeneros() != null && filme.getGeneros().size() > 0) {
			generos = filme.getGeneros();
		}
	}
	
	public void buscarFilmesEmCartaz() {
		filmes = servico.listarFilmesEmCartaz();
	}
	
	public void buscarFilmePorTexto() {
		buscaFilme = pesquisaBean.getBuscaFilme();
		
		filmes = servico.buscarPorPalavra(buscaFilme);
		
		pesquisaBean.setBuscaFilme("");
	}
	
	public void listarTodosFilmes() {
		filmes = servico.listarTodosFilmes();
	}
	
	public Filme buscar() {
		return servico.buscarPeloId(filme.getId());
	}
	
	public Filme getFilme() {
		return filme;
	}
	
	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<String> getGeneros() {
		return generos;
	}

	public void setGeneros(List<String> generos) {
		this.generos = generos;
	}

	public Integer getFilmeId() {
		return filmeId;
	}

	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}

	public List<Sessao> getSessoes() {
		return sessoes;
	}

	public void setSessoes(List<Sessao> sessoes) {
		this.sessoes = sessoes;
	}

	public String getGeneroConcatenado() {
		return generoConcatenado;
	}

	public void setGeneroConcatenado(String generoConcatenado) {
		this.generoConcatenado = generoConcatenado;
	}

	public String getBuscaFilme() {
		return buscaFilme;
	}

	public void setBuscaFilme(String buscaFilme) {
		this.buscaFilme = buscaFilme;
	}

	public PesquisasBean getPesquisaBean() {
		return pesquisaBean;
	}

	public void setPesquisaBean(PesquisasBean pesquisaBean) {
		this.pesquisaBean = pesquisaBean;
	}

}
