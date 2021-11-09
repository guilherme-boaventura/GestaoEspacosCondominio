package tui;

import java.time.LocalDate;
import java.util.Scanner;
import business.MoradorBO;
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
		System.out.println("Insira a opção que deseja:");
	}

	private OpcaoMenuEnum escolha() {
		Scanner sc = new Scanner(System.in);
		int opcao = sc.nextInt();
		while(opcao<1 || opcao>5) {
			System.out.println("Opção inválida, insira novamente: ");
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
			System.out.println("Cadastro concluído!");
			System.out.println("Seu cadastro é o número " + MoradorBO.moradores.indexOf(morador));
			System.out.println();
			break;

		case RESERVAR_QUADRA:
			System.out.println("------------RESERVA QUADRA------------");
			cadIndex = cadIndex();
			ano = anoReserva();
			mes = mesReserva(ano);
			dia = diaReserva(ano, mes);
			id = Espaco.reservas.size() + 1;
			MoradorBO.moradores.get(cadIndex).reservarQuadra(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
			System.out.println("Reserva agendada para " + Espaco.reservas.get(id-1).getDia() + "/"
														+ Espaco.reservas.get(id-1).getMes() + "/"
														+ Espaco.reservas.get(id-1).getAno());
			System.out.println("O ID da sua reserva é: " + id);
			System.out.println();
			break;

		case RESERVAR_GBAND: 
			cadIndex = cadIndex();
			ano = anoReserva();
			mes = mesReserva(ano);
			dia = diaReserva(ano, mes);
			id = Espaco.reservas.size() + 1;
			MoradorBO.moradores.get(cadIndex).reservarGarage(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
			System.out.println("Reserva agendada para " + Espaco.reservas.get(id-1).getDia() + "/"
														+ Espaco.reservas.get(id-1).getMes() + "/"
														+ Espaco.reservas.get(id-1).getAno());
			System.out.println("O ID da sua reserva é: " + id);
			System.out.println();
			break;

		case RESERVAR_SALAO: 
			cadIndex = cadIndex();
			ano = anoReserva();
			mes = mesReserva(ano);
			dia = diaReserva(ano, mes);
			id = Espaco.reservas.size() + 1;
			MoradorBO.moradores.get(cadIndex).reservarSalao(MoradorBO.moradores.get(cadIndex), dia, mes, ano, id);
			System.out.println("Reserva agendada para " + Espaco.reservas.get(id-1).getDia() + "/"
														+ Espaco.reservas.get(id-1).getMes() + "/"
														+ Espaco.reservas.get(id-1).getAno());
			System.out.println("O ID da sua reserva é: " + id);
			System.out.println();
			break;	
			
		case LISTA_DE_RESERVAS:
			listarReservas();
			break;
		
		case SAIR:
			System.out.println();
			System.out.println("Até mais...");
			System.exit(0);
			break;
		}
	}

	private int cadIndex() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Informe o número de cadastro do morador que faz a reserva:");
		int cadIndex = sc.nextInt();
		System.out.println();
		while(cadIndex < 0 || cadIndex >= MoradorBO.moradores.size()) {
			System.out.println("Número de cadastro inexistente, insira novamente: ");
			cadIndex = sc.nextInt();
			System.out.println();
		}
		return cadIndex;
	}

	private int anoReserva() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual ano é essa reserva?"
				+ "\n(Reservas disponíveis apenas para o ano atual e o seguinte)");
		int ano = sc.nextInt();
		System.out.println();
		while(ano < data.getYear() || ano > data.getYear()+1) {
			System.out.println("Ano inválido, reservas disponíveis apenas para " + data.getYear() + " e " + (data.getYear()+1) + 
								"\nInsira novamente:");
			ano = sc.nextInt();
			System.out.println();
		}
		return ano;
	}

	private int mesReserva(int ano) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual mês é essa reserva? \n(informe o número do mês)");
		int mes = sc.nextInt();
		System.out.println();
		if(ano == data.getYear()) {
			while(mes < 1 || mes > 12 || mes < data.getMonthValue()) {
				System.out.println("Mês inválido, insira apenas meses de 1 a 12 que não antecederam o mês atual " + "(" + data.getMonthValue() + "):");
				mes = sc.nextInt();
				System.out.println();
			}
		}else {
			while(mes < 1 || mes > 12) {
				System.out.println("Mês inválido, insira apenas meses de 1 a 12:");
				mes = sc.nextInt();
				System.out.println();
			}
		}
		return mes;
	}

	private int diaReserva(int ano, int mes) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Para qual dia do mês é essa reserva? \n(informe o número do dia)");
		int dia = sc.nextInt();
		System.out.println();
		while((ano == data.getYear() && mes == data.getMonthValue() && dia <= data.getDayOfMonth())) {
			System.out.println("Dia igual ou anterior ao atual (" + data.getDayOfMonth() + "), insira novamente:");
			dia = sc.nextInt();
			System.out.println();
		}
		if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
			while((dia < 1 || dia > 31)) {
				System.out.println("Dia inválido, insira apenas números de 1 a 31:");
				dia = sc.nextInt();
				System.out.println();
			}
		}else if(mes == 4 || mes == 6 || mes == 9 || mes == 11){
			while(dia < 1 || dia > 30) {
				System.out.println("Dia inválido, insira apenas números de 1 a 30:");
				dia = sc.nextInt();
				System.out.println();
			}
		}else {
			if((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) {
				while(dia < 1 || dia > 29) {
					System.out.println("Dia inválido, insira apenas números de 1 a 29:");
					dia = sc.nextInt();
					System.out.println();
				}
			}else {
				while(dia < 1 || dia > 28) {
					System.out.println("Dia inválido, insira apenas números de 1 a 28:");
					dia = sc.nextInt();
					System.out.println();
				}
			}
		}
		return dia;
	}

	public String nameInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Insira o nome do morador que deseja reservar:");
		String nome = sc.nextLine();
		return nome;
	}

	public static int apInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Qual o apartamento desse morador?");
		int ap = sc.nextInt();
		return ap;
	}
	
	private void listarReservas() {
		System.out.println("------------LISTA DE RESERVAS------------");
		System.out.println("Reservas da quadra:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof Quadra) {
				System.out.println(rsv.getMorador().getNome() + " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
		System.out.println();
		System.out.println("Reservas da garage band:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof GarageBand) {
				System.out.println(rsv.getMorador().getNome() + " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
		System.out.println();
		System.out.println("Reservas do salão de festas:");
		for (int i = 0; i < Espaco.reservas.size(); i++) {
			Reserva rsv = Espaco.reservas.get(i);
			if(rsv.getEspaco() instanceof SalaoDeFestas) {
				System.out.println(rsv.getMorador().getNome() + " | " + rsv.getDia() + "-" + rsv.getMes() + "-" + rsv.getAno() + " | ID = " + rsv.getId());
			}
		}
	}
}
