package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.util.Cores;

public class Menu {
	
	public static void main(String[] args) {
		
		ContaController contas = new ContaController();
			
		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("Criar Contas\n");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "Jo�o Silva", 1000f, 100.0f);
		contas.cadastrar(cc1);
		
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 456, 1, "Maria Neves", 2500f, 100.0f);
		contas.cadastrar(cc2);
		
		ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 987, 2, "Mariana dos Santos", 1000f, 12);
		contas.cadastrar(cp1);
		
		ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 654, 2, "Juliana Ramos", 8000f, 15);
		contas.cadastrar(cp2);

		while (true) {

			System.out.println(Cores.TEXT_GREEN + Cores.ANSI_BLACK_BACKGROUND +
							   "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO GUILHERME                   ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.print("            Entre com a op��o desejada:                ");

			try {
					opcao = leia.nextInt();
			}catch(InputMismatchException e) {
				System.out.println("Digite valores inteiros");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println("\nBanco do Guilherme - O seu Futuro come�a aqui!");
				leia.close();
				System.exit(0);
			}

			switch (opcao) {
				case 1:
					System.out.println("Criar Conta\n\n");
					
					System.out.print("Digite o Numero da Ag�ncia: ");
					agencia = leia.nextInt();
					System.out.print("Digite o Nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					do {
						System.out.print("Digite o Tipo da Conta (1-CC ou 2-CP): ");
						tipo = leia.nextInt();
					} while (tipo < 1 && tipo > 2);
					
					System.out.print("Digite o Saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					switch(tipo ) {
						case 1 -> {
							System.out.print("Digite o Limite de Cr�dito (R$): ");
							limite = leia.nextFloat();
							contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
						}
						case 2 -> {
							System.out.print("Digite o dia do Aniversario da Conta: ");
							aniversario = leia.nextInt();
							contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
						}
					}
					keyPress();
					break;
				case 2:
					System.out.println("Listar todas as Contas\n\n");
					contas.listarTodas();
					keyPress();
					break;
				case 3:
					System.out.println("Consultar dados da Conta - por n�mero\n\n");
					
					System.out.print("Digite o n�mero da conta: ");
					numero = leia.nextInt();
					
					contas.procurarPorNumero(numero);
					
					keyPress();
					break;
				case 4:
					System.out.println("Atualizar dados da Conta\n\n");
					
					System.out.print("Digite o n�mero da conta: ");
					numero = leia.nextInt();
					
					if ( contas.buscarNaCollection(numero) != null) {
						 
						System.out.print("Digite o numero da Ag�ncia: ");
						agencia = leia.nextInt();
						System.out.print("Digite o Nome do Titular: ");
						leia.skip("\\R?");
						titular = leia.nextLine();
						
						System.out.print("Digite o Saldo da Conta (R$): ");
						saldo = leia.nextFloat();
						
						tipo = contas.retornaTipo(numero);
						
						switch (tipo) {
							case 1 -> {
								System.out.print("Digite o Limite de Cr�dito (R$): ");
								limite = leia.nextFloat();
								contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
							}
							case 2 -> {
								System.out.print("Digite o dia do Aniversario da Conta: ");
								aniversario = leia.nextInt();
								contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
							}						
							default -> {
								System.out.print("Tipo de Conta inv�lido!");
							}
						}
					} else
						System.out.print("Conta n�o encontrada!");
					
					keyPress();
					break;
				case 5:
					System.out.println("Apagar a Conta\n\n");
					
					System.out.print("Digite o n�mero da Conta: ");
					numero = leia.nextInt();
					
					contas.deletar(numero);
					
					keyPress();
					break;
				case 6:
					System.out.println("Saque\n\n");
					
					System.out.print("Digite o N�mero da Conta: ");
					numero = leia.nextInt();
					
					do {
						System.out.print("Digite o Valor do Saque (R$): ");
						valor = leia.nextFloat();
					} while(valor <= 0);
					
					contas.sacar(numero, valor);
					
					keyPress();
					break;
				case 7:
					System.out.println("Dep�sito\n\n");
					
					System.out.println("Digite o N�mero da Conta: ");
					numero = leia.nextInt();
					
					do {
						System.out.print("Digite o Valor do Dep�sito (R$): ");
						valor = leia.nextFloat();
					} while(valor <= 0);
					
					 contas.depositar(numero, valor);
					
					keyPress();
					break;
				case 8:
					System.out.println("Transfer�ncia entre Contas\n\n");
					
					System.out.print("Digite o N�mero da Conta de Origem: ");
					numero = leia.nextInt();
					System.out.print("Digite o N�mero da Conta de Destino: ");
					numeroDestino = leia.nextInt();
					
					do {
						System.out.print("Digite o Valoe da Transfer�ncia (R$): ");
						valor = leia.nextFloat();
					} while(valor <= 0);
					
					contas.transferir(numero, numeroDestino, valor);
					
					keyPress();
					break;
				default:
					System.out.println("\nOp��o Inv�lida!\n");
					keyPress();
					break;
			}
		}
	}
	
	public static void keyPress() {
		
		try {
			
			System.out.println(Cores.TEXT_RESET + "\nPressione Enter para Continuar..");
			System.in.read();
		} catch(IOException e) {
			
			System.out.println("Voc� pressionou uma tecla diferente de Enter!");
		}
	}

}