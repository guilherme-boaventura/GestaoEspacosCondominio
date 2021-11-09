package domain;

import java.util.Scanner;
import business.*;

public class Morador {

		private String nome;
		private String cpf;
		private Apartamento apartamento;
		private String bloco;
		
		public Morador() {
			setNome();
			setCpf();
			setBloco();
			setApartamento();
		}
		
		public void reservarSalao(Morador morador, int dia, int mes, int ano, int id) {
			ReservaBO reservaBO = new ReservaBO();
			reservaBO.createReservaSalao(morador, dia, mes, ano, id);
		}
		
		public void reservarQuadra(Morador morador, int dia, int mes, int ano, int id) {
			ReservaBO reservaBO = new ReservaBO();
			reservaBO.createReservaQuadra(morador, dia, mes, ano, id);
		}
		
		public void reservarGarage(Morador morador, int dia, int mes, int ano, int id) {
			ReservaBO reservaBO = new ReservaBO();
			reservaBO.createReservaGarage(morador, dia, mes, ano, id);
		}

		public String getNome() {
			return nome;
		}

		public void setNome() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Qual o nome do morador?");
			this.nome = sc.nextLine();
			System.out.println();
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Qual o CPF do morador?");
			this.cpf = sc.nextLine();
			System.out.println();
		}

		public int getApartamento() {
			return apartamento.getNumero();
		}

		public void setApartamento() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Em qual o apartamento?");
			int numero = sc.nextInt();
			System.out.println();
			this.apartamento = MoradorBO.setApartamentoAux(numero, this.bloco);
			while(this.apartamento == (null)) {
				System.out.println("Apartamento inexistente, insira novamente:");
				numero = sc.nextInt();
				System.out.println();
				this.apartamento = MoradorBO.setApartamentoAux(numero, this.bloco);
			}
		}

		public String getBloco() {
			return bloco;
		}

		public void setBloco() {
			Scanner sc = new Scanner(System.in);
			System.out.println("O Morador reside no bloco A ou B?");
			this.bloco = sc.next();
			System.out.println();
			while(!(this.bloco.equalsIgnoreCase("a") || this.bloco.equalsIgnoreCase("b"))) {
				System.out.println("Bloco inexistente, insira novamente:");
				this.bloco = sc.next();
				System.out.println();
			}
		}
}
