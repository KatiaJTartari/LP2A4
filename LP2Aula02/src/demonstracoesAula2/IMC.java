package demonstracoesAula2;

public class IMC {

	float altura;
	float peso;

	public IMC(float altura, float peso) {
		this.altura = altura;
		this.peso = peso;
	}

	float calcular() {
		return this.peso / (this.altura * this.altura);
	}
}
