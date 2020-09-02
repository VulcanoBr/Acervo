package model;

public class Acervo {

	private String tipo, codigoCatalogo, artista, titulo, genero, formato,
				   patrocinador, paisLancamento, codigoDeBarras, capa;
	private Integer anoLancamento, qtdTotal;
	
	
	public Acervo(String tipo, String codigoCatalogo,  String artista, String titulo, 
			Integer anoLancamento2,
			String genero, Integer qtdTotal, String formato,
			String patrocinador, String paisLancamento, String codigoDeBarras, String capa) 
		{
		super();
		this.tipo = tipo;
		this.codigoCatalogo = codigoCatalogo;
		this.artista = artista;
		this.titulo = titulo;
		this.anoLancamento = anoLancamento2;
		this.genero = genero;
		this.qtdTotal = qtdTotal;
		this.formato = formato;
		this.patrocinador = patrocinador;
		this.paisLancamento = paisLancamento;
		this.codigoDeBarras = codigoDeBarras;
		this.capa = capa;
		
		
	}
	
     public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	
	public void setCodigoCatalogo(String codigoCatalogo) {
		if (codigoCatalogo == null || codigoCatalogo.trim().isEmpty()) {
			String message = "Código do Catalogo  não pode ser vazio.";
			throw new IllegalArgumentException(message);
		}
		this.codigoCatalogo = codigoCatalogo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getPatrocinador() {
		return patrocinador;
	}

	public void setPatrocinador(String patrocinador) {
		this.patrocinador = patrocinador;
	}

	public String getPaisLancamento() {
		return paisLancamento;
	}

	public void setPaisLancamento(String paisLancamento) {
		this.paisLancamento = paisLancamento;
	}
	
	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public Integer getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public Integer getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(Integer qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		
}
