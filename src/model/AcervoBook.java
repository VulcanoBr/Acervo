package model;

import java.util.Date;

public class AcervoBook extends Acervo {

	private String tituloOriginal, tradutor, categoria, linguagem, sinopse, 
			codigoCDD, codigoCDU, series, observacao; 
	private Integer anoPrimeiraEdicao, edicao;
	private Date dtAtualizacao;
		
	public AcervoBook(String tipo, String codigoCatalogo, String artista, String titulo, String tituloOriginal, 
			 String tradutor, Integer anoPrimeiraEdicao,Integer anoLancamento2, Integer edicao,
			 String linguagem, String genero, String categoria, String formato, Integer qtdTotal,  
			 String patrocinador, String paisLancamento, String codigoDeBarras, String codigoCDD,
			 String codigoCDU, String capa, String sinopse,   String series, String observacao, 
			 Date dtAtualizacao) {
			 super(tipo, codigoCatalogo, artista, titulo, anoLancamento2, genero, qtdTotal, formato, patrocinador,
				paisLancamento, codigoDeBarras, capa);
			 this.tituloOriginal = tituloOriginal;
			 this.tradutor = tradutor;
			 this.categoria = categoria;
			 this.linguagem = linguagem;
			 this.sinopse = sinopse;
			 this.codigoCDD = codigoCDD;
			 this.codigoCDU = codigoCDU;
			 this.series = series;
			 this.anoPrimeiraEdicao = anoPrimeiraEdicao;
			 this.edicao = edicao;
			 this.observacao = observacao;
			 this.dtAtualizacao = dtAtualizacao;
	}
	public String getTituloOriginal() {
		return tituloOriginal;
	}
	public void setTituloOriginal(String tituloOriginal) {
		this.tituloOriginal = tituloOriginal;
	}
	public String getTradutor() {
		return tradutor;
	}
	public void setTradutor(String tradutor) {
		this.tradutor = tradutor;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getLinguagem() {
		return linguagem;
	}
	public void setLinguagem(String linguagem) {
		this.linguagem = linguagem;
	}
	public String getSinopse() {
		return sinopse;
	}
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	public String getCodigoCDD() {
		return codigoCDD;
	}
	public void setCodigoCDD(String codigoCDD) {
		this.codigoCDD = codigoCDD;
	}
	public String getCodigoCDU() {
		return codigoCDU;
	}
	public void setCodigoCDU(String codigoCDU) {
		this.codigoCDU = codigoCDU;
	}
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	public Integer getAnoPrimeiraEdicao() {
		return anoPrimeiraEdicao;
	}
	public void setAnoPrimeiraEdicao(Integer anoPrimeiraEdicao) {
		this.anoPrimeiraEdicao = anoPrimeiraEdicao;
	}
	public Integer getEdicao() {
		return edicao;
	}
	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}
	public Date getDtAtualizacao() {
		return dtAtualizacao;
	}
	public void setDtAtualizacao(Date dtAtualizacao) {
		this.dtAtualizacao = dtAtualizacao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
}
