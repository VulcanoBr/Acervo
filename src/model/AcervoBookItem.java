package model;

public class AcervoBookItem {
	
	private String tipo, codigoCatalogo, formato, descCaptulo;  
			
	private Integer numCaptulo, qtdPage, numPage;

	
	public AcervoBookItem(String tipo, String codigoCatalogo, Integer numCaptulo, String descCaptulo, 
						  Integer numPage, Integer qtdPage,  String formato) {
		super();
		this.tipo = tipo;
		this.codigoCatalogo = codigoCatalogo;
		this.numCaptulo = numCaptulo;
		this.descCaptulo = descCaptulo;
		this.qtdPage = qtdPage;
		this.numPage = numPage;
		this.formato = formato;
	}

	public AcervoBookItem () {
		
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

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public String getDescCaptulo() {
		return descCaptulo;
	}

	public void setDescCaptulo(String descCaptulo) {
		this.descCaptulo = descCaptulo;
	}

	public Integer getNumCaptulo() {
		return numCaptulo;
	}

	public void setNumCaptulo(Integer numCaptulo) {
		this.numCaptulo = numCaptulo;
	}

	public Integer getQtdPage() {
		return qtdPage;
	}

	public void setQtdPage(Integer qtdPage) {
		this.qtdPage = qtdPage;
	}

	public Integer getNumPage() {
		return numPage;
	}

	public void setNumPage(Integer numPage) {
		this.numPage = numPage;
	}

	
	
	

}
