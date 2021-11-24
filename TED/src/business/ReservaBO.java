package business;

import domain.Espaco;
import domain.GarageBand;
import domain.Morador;
import domain.Quadra;
import domain.Reserva;
import domain.SalaoDeFestas;

public class ReservaBO {
	
	private ReservaBO() {}
	
	public static Quadra quadra = new Quadra();
	public static SalaoDeFestas salao = new SalaoDeFestas();
	public static GarageBand gband = new GarageBand();

	public static void createReservaQuadra (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, quadra, dia, mes, ano, id);
		quadra.setReserva(rsv);
	}
	
	public static void createReservaSalao (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, salao, dia, mes, ano, id);
		salao.setReserva(rsv);
	}
	
	public static void createReservaGarage (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, gband, dia, mes, ano, id);
		gband.setReserva(rsv);
	}
	
	public static int checkId(int id) {
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			while(id <= Espaco.reservas.get(i).getId()) {
				id++;
			}
		}
		return id;
	}
	
	public static boolean checkData(int ano, int mes, int dia, Espaco espaco) {
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			if(ano == Espaco.reservas.get(i).getAno() && mes == Espaco.reservas.get(i).getMes() && 
			dia == Espaco.reservas.get(i).getDia() && espaco.equals(Espaco.reservas.get(i).getEspaco())) {
				return true;
			}
		}
		return false;
	}
}
