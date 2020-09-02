package model;

public class Tabelas {

	private int codigo;
	private String descricao;
	
	public Tabelas(int codigo, String descricao) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		
	}
	
	public Tabelas () {
		
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao;
	
	}

	
}
