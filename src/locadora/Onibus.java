package locadora;

public class Onibus extends Veiculo {

	private int passageiros;
	
	public double seguro() {
		return (getValorAvaliado()*0.20)/365;
	}

	public Onibus(String marca, String modelo, int anoDeFabricacao,  double valorAvaliado,  double valorDiaria, String placa, int passageiros) {
			this.marca = marca;
			this.modelo = modelo;
			this.anoDeFabricacao = anoDeFabricacao;
			this.placa = placa;
			this.valorAvaliado = valorAvaliado;
			this.valorDiaria = valorDiaria;
			this.passageiros = passageiros;
	}

	public int getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
	}
}
