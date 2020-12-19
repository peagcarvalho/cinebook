package br.edu.ifpb.cinebook.modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeVoucher {
	
	public File gerarVoucherDeIngresso(Ingresso ingresso) {
		return escreverDocumentoPDF(ingresso);
	}
	
	public byte[] transformarArquivoEmBytes(File file) {
		byte[] conteudoArquivo = null;
		
		try {
			conteudoArquivo = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conteudoArquivo;
	}
	
	public File transformarBytesEmArquivo(byte[] conteudoArquivo) {
		
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		String caminho = path.toString();

		String nomeArquivo = caminho + "\\voucher.pdf";
		
		File arquivo = new File(nomeArquivo);
		
		try {
			FileOutputStream outputStream = new FileOutputStream(arquivo);
			
			outputStream.write(conteudoArquivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return arquivo;
	}
	
	public File escreverDocumentoPDF(Ingresso ingresso) {
		Reserva reserva = ingresso.getReserva();
		Sessao sessao = ingresso.getReserva().getSessao();
		Filme filme = ingresso.getReserva().getSessao().getFilme();
		Cinema cinema = ingresso.getReserva().getSessao().getCinema();
		
		Document document = new Document();
		Path path = Paths.get(System.getProperty("user.home"), "Downloads");
		String caminho = path.toString();

		String nomeArquivo = caminho + "\\voucher.pdf";
		
		try {
			PdfWriter.getInstance(document , new FileOutputStream(nomeArquivo));
			document.open();
			document.setPageSize(PageSize.A4);
			
			document.newPage();
			
			BaseFont bf = BaseFont.createFont(BaseFont.COURIER, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			Font fonteTitulo = new Font(bf, 14, Font.BOLD);
			Font fontePadrao = new Font(bf, 12, Font.NORMAL);
			
			Paragraph titulo = new Paragraph("CINEBOOK - Voucher do Ingresso");
			titulo.setFont(fonteTitulo);
			document.add(titulo);
			
			document.add(new Paragraph(" "));
			
			Paragraph p1 = new Paragraph("Reserva nº " + reserva.getId().toString());
			p1.setFont(fonteTitulo);
			document.add(p1);
			
			document.add(new Paragraph(" "));
			
			Paragraph p2 = new Paragraph(cinema.getNome());
			p2.setFont(fonteTitulo);
			document.add(p2);
			
			Paragraph p3 = new Paragraph(cinema.getEnderecoConcatenado());
			p3.setFont(fontePadrao);
			document.add(p3);
			
			document.add(new Paragraph(" "));
			
			Paragraph p4 = new Paragraph("Sessão: ");
			p4.setFont(fonteTitulo);
			document.add(p4);
			
			Paragraph p5 = new Paragraph(filme.getTitulo());
			p5.setFont(fonteTitulo);
			document.add(p5);
			
			String str = "";
			if (sessao.isTresDimensoes() == true) {
				str += "3D ";
			} else {
				str += "2D ";
			}
			
			if (sessao.isLegendado() == true) {
				str += "LEG ";
			} else {
				str += "DUB ";
			}
			
			if (filme.getClassificacao().equals("Livre")) {
				str += filme.getClassificacao();
			} else {
				str += filme.getClassificacao() + " anos";
			}
			
			Paragraph p6 = new Paragraph(str);
			p6.setFont(fonteTitulo);
			document.add(p6);
			
			document.add(new Paragraph(" "));
			
			Paragraph p7 = new Paragraph("Sala: " + sessao.getSala());
			p7.setFont(fontePadrao);
			document.add(p7);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
			
			Paragraph p8 = new Paragraph(sdf.format(sessao.getDataExibicao()) + " às " + sdf2.format(sessao.getHoraExibicao()));
			p8.setFont(fontePadrao);
			document.add(p8);
			
			document.add(new Paragraph(" "));
			
			Paragraph p9 = new Paragraph("Quem vai assistir?");
			p9.setFont(fonteTitulo);
			document.add(p9);
			
			Paragraph p10 = new Paragraph(ingresso.getNomeCompleto());
			p10.setFont(fontePadrao);
			document.add(p10);
			
			Paragraph p11 = new Paragraph(sdf.format(ingresso.getDataNascimento()));
			p11.setFont(fontePadrao);
			document.add(p11);
			
			document.add(new Paragraph(" "));
			
			Paragraph p12 = new Paragraph(ingresso.getTipo());
			p12.setFont(fontePadrao);
			document.add(p12);
			
			Paragraph p13 = new Paragraph("R$ " + Float.toString(ingresso.getValor()));
			p13.setFont(fontePadrao);
			document.add(p13);
			
		} catch (DocumentException | IOException e) {
			System.out.println(e.getMessage());
		}
		
		document.close();
		
		return new File(nomeArquivo);
	}

}
