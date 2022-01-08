package demonstracoesAula2;

public class Demonstracao2 {

	public static void main(String[] args) {

		IMC calculadoraIMC = new IMC(1.8f, 100.00f);

		float valorIMC = calculadoraIMC.calcular();

		System.out.println("O IMC calculado é " + valorIMC);

	}
}
