package domain;

public class Apartamento {
	private int numero;
	private String bloco;
	
	public Apartamento(int numero, String bloco) {
		setNumero(numero);
		setBloco(bloco);
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getBloco() {
		return bloco;
	}
	
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
}
