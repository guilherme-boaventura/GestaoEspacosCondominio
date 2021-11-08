package business;

import data.GarageBand;
import data.Morador;
import data.Quadra;
import data.Reserva;
import data.SalaoDeFestas;

public class ReservaBO {
	
	Quadra quadra = new Quadra();
	SalaoDeFestas salao = new SalaoDeFestas();
	GarageBand gband = new GarageBand();

	public void createReservaQuadra (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, quadra, dia, mes, ano, id);
		quadra.setReserva(rsv);
	}
	
	public void createReservaSalao (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, quadra, dia, mes, ano, id);
		salao.setReserva(rsv);
	}
	
	public void createReservaGarage (Morador morador, int dia, int mes, int ano, int id) {
		Reserva rsv = new Reserva(morador, quadra, dia, mes, ano, id);
		gband.setReserva(rsv);
	}
}
