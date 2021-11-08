package data;

public class Reserva {
	private int id;
	private int dia;
	private int mes;
	private int ano;
	private Espaco espaco;
	private Morador morador;
	
	public Reserva (Morador morador, Espaco espaco, int dia, int mes, int ano, int id) {
		setId(id);
		setMorador(morador);
		setEspaco(espaco);
		setDia(dia);
		setMes(mes);
		setAno(ano);
	}
	
	public Espaco getEspaco() {
		return espaco;
	}
	public void setEspaco(Espaco espaco) {
		this.espaco = espaco;
	}
	public Morador getMorador() {
		return morador;
	}
	public void setMorador(Morador morador) {
		this.morador = morador;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
