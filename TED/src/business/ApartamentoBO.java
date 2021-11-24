package business;

import java.util.ArrayList;

import domain.Apartamento;

public class ApartamentoBO {
	
	private ApartamentoBO() {}
	
	static ArrayList<Apartamento> blocoA = new ArrayList<>();
	static ArrayList<Apartamento> blocoB = new ArrayList<>();
	
	public static void createApBlocoA() {
		for (int i = 1, num = 101; i <= 16; i++) {
			Apartamento apart = new Apartamento(num, "a");
			blocoA.add(apart);
			if(i % 4 == 0) {
				num += 97;
			}else {
				num++;
			}
		}
	}
	
	public static void createApBlocoB() {
		for (int i = 1, num = 101; i <= 16; i++) {
			Apartamento apart = new Apartamento(num, "b");
			blocoB.add(apart);
			if(i % 4 == 0) {
				num += 97;
			}else {
				num++;
			}
		}
	}
}
