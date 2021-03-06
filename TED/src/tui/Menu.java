package tui;

import java.time.LocalDate;
import java.util.Scanner;
import business.MoradorBO;
import business.ReservaBO;
import domain.*;


public class Menu {

	LocalDate data = LocalDate.now();
	int cadIndex;
	int dia;
	int mes;
	int ano;
	int id;

	public void iniciar() {
		OpcaoMenuEnum opcao;
		do {
			opcoes();
			opcao = escolha();
			executarOpcao(opcao);
		}while(!opcao.equals(OpcaoMenuEnum.SAIR));
	}

	private void opcoes() {
		System.out.println("-----------------MENU-----------------");
		for (OpcaoMenuEnum opcao : OpcaoMenuEnum.values()) {
			System.out.println(opcao.getCodeTxt());
		}
		System.out.println("Insira a op??o que deseja:");
	}

	private OpcaoMenuEnum escolha() {
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
		while((opcao < 1 || opcao > 6) && opcao != 9) {
			System.out.println("Op??o inv?lida, insira novamente: ");
			opcao = sc.nextInt();
		}
		System.out.println();
		return OpcaoMenuEnum.valueOfInt(opcao);	
	}

	private void executarOpcao(OpcaoMenuEnum opcao) {
		switch (opcao) {
		case CADASTRAR_MORADOR:
			System.out.println("----------CADASTRO DE MORADOR----------");
			Morador morador = MoradorBO.cadastrarMorador();
			System.out.println("Cadastro conclu?do!");
			System.out.println("Seu cadastro ? o n?mero " + MoradorBO.moradores.indexOf(morador));
			System.out.println();
			break;

		case RESERVAR_QUADRA:
			System.out.println("------------RESERVA QUADRA------------");
			cadIndex = cadIndex();
			if(confirmarCpfAgendamento(cadIndex)) {
				do{
					ano = anoReserva();
					mes = mesReserva(ano);
					dia = diaReserva(ano, mes);
					if(ReservaBO.checkData(ano, mes, dia, ReservaBO.quadra)) {
						System.out.println("Data j? ocupada para esse espa?o, escolha outra:");
						System.out.println();
					}
				}while(ReservaBO.checkData(ano, mes, dia, ReservaBO.quadra));
				id = ReservaBO.checkId(Espaco.reservas.size()+1);
				MoradorBO.moradores.get(cadIndex).reservarQuadra(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
				System.out.println("Reserva agendada para " + Espaco.reservas.get(Espaco.reservas.size()-1).getDia() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getMes() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getAno());
				System.out.println("O ID da sua reserva ?: " + id);
				id++;
				System.out.println();
			}
			break;

		case RESERVAR_GBAND: 
			System.out.println("-------------RESERVA GBAND-------------");
			cadIndex = cadIndex();
			if(confirmarCpfAgendamento(cadIndex)) {
				do{
					ano = anoReserva();
					mes = mesReserva(ano);
					dia = diaReserva(ano, mes);
					if(ReservaBO.checkData(ano, mes, dia, ReservaBO.gband)) {
						System.out.println("Data j? ocupada para esse espa?o, escolha outra:");
						System.out.println();
					}
				}while(ReservaBO.checkData(ano, mes, dia, ReservaBO.gband));
				id = ReservaBO.checkId(Espaco.reservas.size()+1);
				MoradorBO.moradores.get(cadIndex).reservarGarage(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
				System.out.println("Reserva agendada para " + Espaco.reservas.get(Espaco.reservas.size()-1).getDia() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getMes() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getAno());
				System.out.println("O ID da sua reserva ?: " + id);
				id++;
				System.out.println();
			}
			break;

		case RESERVAR_SALAO: 
			System.out.println("-------------RESERVA SALAO-------------");
			cadIndex = cadIndex();
			if(confirmarCpfAgendamento(cadIndex)) {
				do{
					ano = anoReserva();
					mes = mesReserva(ano);
					dia = diaReserva(ano, mes);
					if(ReservaBO.checkData(ano, mes, dia, ReservaBO.salao)) {
						System.out.println("Data j? ocupada para esse espa?o, escolha outra:");
						System.out.println();
					}
				}while(ReservaBO.checkData(ano, mes, dia, ReservaBO.salao));
				id = ReservaBO.checkId(Espaco.reservas.size()+1);
				MoradorBO.moradores.get(cadIndex).reservarSalao(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
				System.out.println("Reserva agendada para " + Espaco.reservas.get(Espaco.reservas.size()-1).getDia() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getMes() + "/"
						+ Espaco.reservas.get(Espaco.reservas.size()-1).getAno());
				System.out.println("O ID da sua reserva ?: " + id);
				id++;
				System.out.println();
			}
			break;	

		case LISTA_DE_RESERVAS:
			listarReservas();
			break;
			
		case LISTA_DE_MORADORES:
			listarMoradores();
			break;

		case CANCELAR_RESERVA:
			if(cancelarReserva()) {
				System.out.println("Reserva cancelada com sucesso!");
				System.out.println();
			}
			break;

		case SAIR:
			System.out.println();
			System.out.println("At? mais...");
			System.exit(0);
			break;
		}
	}

	private int cadIndex() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe o n?mero de cadastro do morador que faz a reserva:");
		int cadIndex = sc.nextInt();
		System.out.println();
		while(cadIndex < 0 || cadIndex >= MoradorBO.moradores.size()) {
			System.out.println("N?mero de cadastro inexistente, insira novamente: ");
			cadIndex = sc.nextInt();
			System.out.println();
		}
		return cadIndex;
	}


	private int anoReserva() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual ano ? essa reserva?"
				+ "\n(Reservas dispon?veis apenas para o ano atual e o seguinte)");
		int ano = sc.nextInt();
		System.out.println();
		while(ano < data.getYear() || ano > data.getYear()+1) {
			System.out.println("Ano inv?lido, reservas dispon?veis apenas para " + data.getYear() + " e " + (data.getYear()+1) + 
					"\nInsira novamente:");
			ano = sc.nextInt();
			System.out.println();
		}
		return ano;
	}


	private int mesReserva(int ano) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual m?s ? essa reserva? \n(informe o n?mero do m?s)");
		int mes = sc.nextInt();
		System.out.println();
		if(ano == data.getYear()) {
			while(mes < 1 || mes > 12 || mes < data.getMonthValue()) {
				System.out.println("M?s inv?lido, insira apenas meses de 1 a 12 que n?o antecederam o m?s atual " + "(" + data.getMonthValue() + "):");
				mes = sc.nextInt();
				System.out.println();
			}
		}else {
			while(mes < 1 || mes > 12) {
				System.out.println("M?s inv?lido, insira apenas meses de 1 a 12:");
				mes = sc.nextInt();
				System.out.println();
			}
		}
		return mes;
	}


	private int diaReserva(int ano, int mes) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual dia do m?s ? essa reserva? \n(informe o n?mero do dia)");
		int dia = sc.nextInt();
		System.out.println();
		while((ano == data.getYear() && mes == data.getMonthValue() && dia <= data.getDayOfMonth())) {
			System.out.println("Dia igual ou anterior ao atual (" + data.getDayOfMonth() + "), insira novamente:");
			dia = sc.nextInt();
			System.out.println();
		}
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			while((dia < 1 || dia > 31)) {
				System.out.println("Dia inv?lido, insira apenas n?meros de 1 a 31:");
				dia = sc.nextInt();
				System.out.println();
			}
		}else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
			while(dia < 1 || dia > 30) {
				System.out.println("Dia inv?lido, insira apenas n?meros de 1 a 30:");
				dia = sc.nextInt();
				System.out.println();
			}
		}else {
			if((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
				while(dia < 1 || dia > 29) {
					System.out.println("Dia inv?lido, insira apenas n?meros de 1 a 29:");
					dia = sc.nextInt();
					System.out.println();
				}
			}else {
				while(dia < 1 || dia > 28) {
					System.out.println("Dia inv?lido, insira apenas n?meros de 1 a 28:");
					dia = sc.nextInt();
					System.out.println();
				}
			}
		}
		return dia;
	}


	private void listarReservas() {
		System.out.println("------------LISTA DE RESERVAS------------");
		System.out.println("Reservas da quadra:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof Quadra) {
				System.out.println(rsv.getMorador().getNome() + " " + rsv.getMorador().getApartamento() + rsv.getMorador().getBloco().toUpperCase()
						+ " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
		System.out.println();
		System.out.println("Reservas da garage band:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof GarageBand) {
				System.out.println(rsv.getMorador().getNome() + " " + rsv.getMorador().getApartamento() + rsv.getMorador().getBloco().toUpperCase()
						+ " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
		System.out.println();
		System.out.println("Reservas do sal?o de festas:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof SalaoDeFestas) {
				System.out.println(rsv.getMorador().getNome() + " " + rsv.getMorador().getApartamento() + rsv.getMorador().getBloco().toUpperCase()
						+ " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
		System.out.println();
	}
	
	private void listarMoradores() {
		System.out.println("------------LISTA DE MORADORES------------");
		for (int i = 0; i < MoradorBO.moradores.size(); i++) {
			System.out.println(MoradorBO.moradores.get(i).getNome() + " " + MoradorBO.moradores.get(i).getApartamento() + MoradorBO.moradores.get(i).getBloco().toUpperCase()
					+ " - Cadastro: " + MoradorBO.moradores.indexOf(MoradorBO.moradores.get(i)));
		}
		System.out.println();
	}




	private boolean cancelarReserva() {
		Reserva  rsv = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o ID da reserva que deseja cancelar");
		int id = sc.nextInt();
		System.out.println();
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			if(Espaco.reservas.get(i).getId() == id) {
				rsv = Espaco.reservas.get(i);
			}
		}
		if(confirmarCpfCancelamento(rsv)) {
			Espaco.reservas.remove(rsv);
			return true;
		}else {
			System.out.println("N?o foi poss?vel validar o CPF, retorne mais tarde com o dado correto.");
			System.out.println();
			return false;
		}
	}


	private boolean confirmarCpfCancelamento(Reserva rsv) {
		int counter = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Confirme o CPF do morador a realizar a a??o:");
		double cpf = sc.nextDouble();
		System.out.println();
		while(cpf != Double.valueOf(rsv.getMorador().getCpf())) {
			System.out.println("CPF divergente, insira novamente:");
			cpf = sc.nextDouble();
			System.out.println();
			if(cpf != Double.valueOf(rsv.getMorador().getCpf())) {
				counter++;
			}
			if(counter == 3) {
				return false;
			}
		}
		return true;
	}


	private boolean confirmarCpfAgendamento(int cadIndex) {
		int counter = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("Confirme o CPF do morador a realizar a a??o:");
		double cpf = sc.nextDouble();
		System.out.println();
		while(cpf != Double.valueOf(MoradorBO.moradores.get(cadIndex).getCpf())) {
			System.out.println("CPF divergente, insira novamente:");
			cpf = sc.nextDouble();
			System.out.println();
			if(cpf != Double.valueOf(MoradorBO.moradores.get(cadIndex).getCpf())) {
				counter++;
			}
			if(counter == 3) {
				System.out.println("N?o foi poss?vel validar o CPF, retorne mais tarde com o dado correto.");
				System.out.println();
				return false;
			}
		}
		return true;
	}
}
