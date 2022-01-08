package demonstracoesAula2;

import java.util.Scanner;

public class Demonstracao5 {

	public static void main(String[] args) {

		boolean continuar = true;
		String nome;
		short idade;
		float altura, peso;
		Scanner scan = new Scanner(System.in);

		while (continuar) {
			try {
				System.out.print("Digite o seu nome: ");
				nome = scan.nextLine();
				System.out.print("Digite a sua idade: ");
				idade = scan.nextShort();
				System.out.print("Digite a sua altura: ");
				altura = scan.nextFloat();
				System.out.print("Digite o seu peso: ");
				peso = scan.nextFloat();

				System.out.println(
						"Os dados digitados foram: " + nome + ", " + idade + " anos, " + altura + "m, " + peso + "kg.");

				IMC3 calculadoraIMC = new IMC3(altura, peso);

				System.out.println("O IMC calculado é: " + calculadoraIMC.calcular());
				System.out.println("A situação é: " + calculadoraIMC.obterSituacao());

				System.out.println("Deseja continuar (true/false)?");
				continuar = scan.nextBoolean();
				scan.nextLine();
			} catch (Exception e) {
				System.out.println("Algo deu errado! Tente novamente.");
				continuar = true;
				scan.nextLine();
			}
		}

		scan.close();
		System.out.println("Execução finalizada!");

	}
}
