package demonstracoesAula2;

public class IMC3 {

	float altura;
	float peso;

	public IMC3(float altura, float peso) {
		this.altura = altura;
		this.peso = peso;
	}

	float calcular() {
		if (this.altura > 0) {
			return this.peso / (this.altura * this.altura);
		}

		return -1.0f;
	}

	String obterSituacao() {
		float valorIMC = calcular();

		if (valorIMC < 0)
			return "Impossível calcular o valor do IMC";
		else if (valorIMC < 18.5)
			return "Abaixo do peso ideal";
		else if (valorIMC >= 18.5 && valorIMC < 25.0)
			return "Peso normal";
		else if (valorIMC >= 25.0 && valorIMC < 30.0)
			return "Sobrepeso";
		else if (valorIMC >= 30.0 && valorIMC < 35.0)
			return "Obesidade Grau I";
		else if (valorIMC >= 35.0 && valorIMC < 40.0)
			return "Obesidade Grau II";
		else
			return "Obesidade Grau III";
	}

}
