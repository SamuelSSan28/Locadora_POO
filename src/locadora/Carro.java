package locadora;

public class Carro extends Veiculo {
		
	public final int PASSEIO = 1;
	public final int SUV = 2;
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public final int PICKUP = 3;
	
	private int tipo;

	public double seguro() {
		return (getValorAvaliado()*0.03)/365;
	}

	public Carro(String marca, String modelo, int anoDeFabricacao,  double valorAvaliado,  double valorDiaria, String placa, int tipo) {
			this.marca = marca;
			this.modelo = modelo;
			this.anoDeFabricacao = anoDeFabricacao;
			this.placa = placa;
			this.valorAvaliado = valorAvaliado;
			this.valorDiaria = valorDiaria;
			this.tipo = tipo;
	}
}
