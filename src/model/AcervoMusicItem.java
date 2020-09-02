package model;

public class AcervoMusicItem {

	private String tipo, codigoCatalogo, formato, tituloItem, tempoDuracao, 
			compositor, participante;
	private Integer numSeq;
	
	public AcervoMusicItem(String tipo, String codigoCatalogo, Integer numSeq, 
			String tituloItem, String tempoDuracao, String compositor,
			String participante, String formato) {
		super();
		this.tipo = tipo;
		this.codigoCatalogo = codigoCatalogo;
		this.formato = formato;
		this.tituloItem = tituloItem;
		this.tempoDuracao = tempoDuracao;
		this.compositor = compositor;
		this.participante = participante;
		this.numSeq = numSeq;
	}
	
	public AcervoMusicItem () {
		
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}

	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}

	public Integer getNumSeq() {
		return numSeq;
	}

	public void setNumSeq(Integer numSeq) {
		this.numSeq = numSeq;
	}
	
	public String getTituloItem() {
		return tituloItem;
	}

	public void setTituloItem(String tituloItem) {
		this.tituloItem = tituloItem;
	}

	public String getTempoDuracao() {
		return tempoDuracao;
	}

	public void setTempoDuracao(String tempoDuracao) {
		this.tempoDuracao = tempoDuracao;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public String getParticipante() {
		return participante;
	}

	public void setPaticipante(String participante) {
		this.participante = participante;
	}
	
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	
}
