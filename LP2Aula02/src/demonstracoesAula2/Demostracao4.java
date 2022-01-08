package demonstracoesAula2;

import java.util.Scanner;

public class Demostracao4 {

	public static void main(String[] args) {
		
		boolean continuar = true;
		String nome;
		short idade;
		float altura, peso;
		Scanner scan = new Scanner(System.in);
		
		while(continuar) {
			System.out.print("Digite o seu nome: "); nome = scan.nextLine();
			System.out.print("Digite a sua idade: "); idade = scan.nextShort();
			System.out.print("Digite a sua altura: "); altura = scan.nextFloat();
			System.out.print("Digite o seu peso: "); peso = scan.nextFloat();
			
			System.out.println("Os dados digitados foram: " + nome + ", " + 
			                   idade + " anos, " + altura + "m, " + peso + "kg.");
			
			IMC2 calculadoraIMC2 = new IMC2(altura, peso);
			
			System.out.println("O IMC calculado é " + calculadoraIMC2.calcular());
			System.out.println("A situação é " + calculadoraIMC2.obterSituacao());
			
			System.out.print("Deseja continuar (true/false)?"); continuar = scan.nextBoolean();
			scan.nextLine();
		}

		scan.close();
		System.out.print("Execução finalizada!");
	}
}
