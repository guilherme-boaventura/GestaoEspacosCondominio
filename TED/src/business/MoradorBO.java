package business;

import java.util.ArrayList;
import data.*;

public class MoradorBO {
	public static ArrayList<Morador> moradores = new ArrayList<>();

	public static Morador cadastrarMorador() {
		Morador morador = new Morador();
		moradores.add(morador);
		return morador;
	}

	public static Apartamento setApartamentoAux(int num, String bloco) {
		if(bloco.equalsIgnoreCase("a"))
			for (int i = 0; i < ApartamentoBO.blocoA.size(); i++) {
				if(ApartamentoBO.blocoA.get(i).getNumero() == num) {
					return ApartamentoBO.blocoA.get(i);
				}
			}else {
				for (int i = 0; i < ApartamentoBO.blocoB.size(); i++) {
					if(ApartamentoBO.blocoB.get(i).getNumero() == num) {
						return ApartamentoBO.blocoB.get(i);
					}
				}
			}
		return null;
	}
}
