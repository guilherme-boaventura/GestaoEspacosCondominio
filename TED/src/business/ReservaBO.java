package business;

import domain.GarageBand;
import domain.Morador;
import domain.Quadra;
import domain.Reserva;
import domain.SalaoDeFestas;

public class ReservaBO {
	
	private ReservaBO() {}
	
	static Quadra quadra = new Quadra();
	static SalaoDeFestas salao = new SalaoDeFestas();
	static GarageBand gband = new GarageBand();

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
}
