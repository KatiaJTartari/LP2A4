package demonstracoesAula2;

import java.util.Scanner;

public class Demonstracao3 {

	public static void main(String[] args) {

		String nome;
		short idade;
		float altura;

		Scanner scan = new Scanner(System.in);

		System.out.print("Digite o seu nome: ");
		nome = scan.nextLine();

		System.out.print("Digite a sua idade: ");
		idade = scan.nextShort();

		System.out.print("Digite a sua altura: ");
		altura = scan.nextFloat();

		System.out.println("Os dados digitados foram: " + nome + ", " + idade + " anos, " + altura + "m.");

		scan.close();
	}
}
