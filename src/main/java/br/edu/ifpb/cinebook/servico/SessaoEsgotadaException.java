package br.edu.ifpb.cinebook.servico;

public class SessaoEsgotadaException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public SessaoEsgotadaException() {
		super("Alguns ingressos estão indisponíveis porque a sessão esgotou");
	}

}
