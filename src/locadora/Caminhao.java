package locadora;

public class Caminhao extends Veiculo {
		
		private int carga;

		public double seguro() {
			return (getValorAvaliado()*0.08)/365;
		}

		public Caminhao(String marca, String modelo, int anoDeFabricacao,  double valorAvaliado,  double valorDiaria, String placa, int carga) {
			this.marca = marca;
			this.modelo = modelo;
			this.anoDeFabricacao = anoDeFabricacao;
			this.placa = placa;
			this.valorAvaliado = valorAvaliado;
			this.valorDiaria = valorDiaria;
			this.carga = carga;
		}

		public int getCarga() {
			return carga;
		}

		public void setCarga(int carga) {
			this.carga = carga;
		}
}
