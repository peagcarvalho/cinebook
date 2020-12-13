package br.edu.ifpb.cinebook.beans;

import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import br.edu.ifpb.cinebook.modelo.Cinema;
import br.edu.ifpb.cinebook.modelo.GeradorDeVoucher;
import br.edu.ifpb.cinebook.modelo.Ingresso;
import br.edu.ifpb.cinebook.modelo.Reserva;
import br.edu.ifpb.cinebook.modelo.Sessao;
import br.edu.ifpb.cinebook.servico.ReservaServico;
import br.edu.ifpb.cinebook.servico.SessaoServico;

@Named
@ViewScoped
public class ReservaIngressosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Reserva reserva = new Reserva();
	private Ingresso ingresso = new Ingresso();
	private Sessao sessao = new Sessao();
	private String tipoIngresso;
	private float valorTotal;
	
	private List<Reserva> reservas;
	private List<Ingresso> ingressos;
	
	@EJB
	private ReservaServico reservaServico;
	@EJB
	private SessaoServico sessaoServico;
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private FacesContext facesContext;
	
	public ReservaIngressosBean() {
		
	}
	
	@PostConstruct
	public void init() {
		setReservas(new ArrayList<Reserva>());
		setIngressos(new ArrayList<Ingresso>());
	}
	
	public String cadastrar() {
		System.out.println("Criando nova reserva");
		getReserva().setCliente(loginBean.getUsuarioLogado());
		getReserva().setSessao(sessao);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String dataStr = sdf.format(new Date(System.currentTimeMillis()));
		
		try {
			getReserva().setDataEmissao(sdf.parse(dataStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Data de Emissao adicionada " + getReserva().getDataEmissao().toString());
		
		getReserva().setQuantIngressos(getIngressos().size());
		System.out.println("Quantidade de ingressos adicionada " + getReserva().getQuantIngressos());
		
		getReserva().setIngressos(getIngressos());
		getReserva().setValorTotal(getReserva().calcularValorTotal());
		System.out.println("Valor total calculado: " + getReserva().getValorTotal());
		
		reservaServico.cadastrar(getReserva());
		
		setReserva(new Reserva());
		ingressos = new ArrayList<Ingresso>();
		
		return "/paginaInicial.xhtml?faces-redirect=true";
	}
	
	public void adicionarIngresso() {
		int ingressosDisponiveis = sessao.getQuantMaxIngressos() - sessao.getQuantIngressosVendidos();
		
		if (ingressosDisponiveis > 0 && ingressos.size() <= ingressosDisponiveis) {
			if (ingressos.size() < 10) {
			
				if (tipoIngresso.equals("Inteira")) {
					getIngresso().setValor(30);
				} else if (tipoIngresso.equals("Meia-entrada")) {
					getIngresso().setValor(15);
				}
				
				getIngresso().setTipo(tipoIngresso);
				
				System.out.println("Adicionando novo ingresso");
				getIngressos().add(getIngresso());
				
				System.out.println("Resetando a variavel ingresso");
				setIngresso(new Ingresso());
				
			} else {
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Só é permitido comprar 10 ingressos por reserva",null));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		} else {
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Esta sessão está se esgotando ou já se esgotou. Você não pode adicionar mais ingressos",null));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		}
	}
	
	public void buscarReservasDoCliente() {
		String emailCliente = loginBean.getUsuarioLogado().getEmail();
		
		reservas = reservaServico.listarReservasDoCliente(emailCliente);
	}
	
	public void concatenarEnderecoCinema() {
		Sessao sessao = reserva.getSessao();
		Cinema cinema = sessao.getCinema();
		
		if (sessao != null && cinema != null) {
			
			String enderecoConcatenado = cinema.getEndereco().getLogradouro() + ", " + cinema.getEndereco().getNumero() + ". " + 
										 cinema.getEndereco().getBairro() + " - " + cinema.getEndereco().getCidade() + ", " + 
										 cinema.getEndereco().getEstado();
			
			reserva.getSessao().getCinema().setEnderecoConcatenado(enderecoConcatenado);
		}
	}
	
	public void baixarVoucherDoIngresso(Ingresso ingresso) {
		Cinema cinema = ingresso.getReserva().getSessao().getCinema();
		
		if (sessao != null && cinema != null) {
			
			String enderecoConcatenado = cinema.getEndereco().getLogradouro() + ", " + cinema.getEndereco().getNumero() + ". " + 
										 cinema.getEndereco().getBairro() + " - " + cinema.getEndereco().getCidade() + ", " + 
										 cinema.getEndereco().getEstado();
			
			ingresso.getReserva().getSessao().getCinema().setEnderecoConcatenado(enderecoConcatenado);
		}
		
		GeradorDeVoucher gerador = new GeradorDeVoucher();
		
		if (ingresso.getVoucher() == null) {
			File arquivo = gerador.gerarVoucherDeIngresso(ingresso);
			ingresso.setVoucher(gerador.transformarArquivoEmBytes(arquivo));
			
			reservaServico.atualizarIngresso(ingresso);
		} else {
			gerador.transformarBytesEmArquivo(ingresso.getVoucher());
		}
	}

	public Ingresso getIngresso() {
		return ingresso;
	}

	public void setIngresso(Ingresso ingresso) {
		this.ingresso = ingresso;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public List<Ingresso> getIngressos() {
		return ingressos;
	}

	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}

	public String getTipoIngresso() {
		return tipoIngresso;
	}

	public void setTipoIngresso(String tipoIngresso) {
		this.tipoIngresso = tipoIngresso;
	}

	public float getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}