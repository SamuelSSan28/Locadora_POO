package locadora;

public class Moto extends Veiculo {
	
	private int cilindrada;

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double seguro() {
		return (getValorAvaliado()*0.11)/365;
	}

	public Moto(String marca, String modelo, int anoDeFabricacao,  double valorAvaliado,  double valorDiaria, String placa, int cilindrada) {
		this.marca = marca;
		this.modelo = modelo;
		this.anoDeFabricacao = anoDeFabricacao;
		this.placa = placa;
		this.valorAvaliado = valorAvaliado;
		this.valorDiaria = valorDiaria;
		this.cilindrada = cilindrada;
	}
}
