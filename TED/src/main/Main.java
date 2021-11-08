package main;

import tui.Menu;
import business.ApartamentoBO;

public class Main {

	public static void main(String[] args) {
		ApartamentoBO.createApBlocoA();
		ApartamentoBO.createApBlocoB();
		Menu menu = new Menu();
		menu.menu();
	}

}
