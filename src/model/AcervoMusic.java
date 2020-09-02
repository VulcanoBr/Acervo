package model;

import java.util.Date;

public class AcervoMusic extends Acervo {
	
	private String estilo, tempoTotal, codigoId, mediaId;
	private Date dataAtualizacao;
	private Integer edicao;
	
	public AcervoMusic (String tipo, String codigoCatalogo,  String artista, String titulo,
			Integer anoLancamento,String genero, String estilo, Integer qtdTotal, 
			String tempoTotal, String formato,String patrocinador, String paisLancamento, 
			String codigoId, String mediaId, String codigoDeBarras, String capa, Integer edicao, 
			Date dataAtualizacao){ 
			 
				super(tipo, codigoCatalogo,  artista, titulo, anoLancamento, genero, qtdTotal,
				formato, patrocinador, paisLancamento, codigoDeBarras,
				capa);
				
		this.estilo = estilo;
		this.tempoTotal = tempoTotal;
		this.codigoId = codigoId;
		this.mediaId = mediaId;
		this.edicao = edicao;
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getTempoTotal() {
		return tempoTotal;
	}

	public void setTempoTotal(String tempoTotal) {
		this.tempoTotal = tempoTotal;
	}

	public String getCodigoId() {
		return codigoId;
	}

	public void setCodigoId(String codigoId) {
		this.codigoId = codigoId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	public Integer getEdicao() {
		return edicao;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}
	
	public Date getDataAtualizacao() {
		
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		
		this.dataAtualizacao = dataAtualizacao;
	}

}
