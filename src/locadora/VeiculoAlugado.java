package locadora;

public class VeiculoAlugado extends Exception {

	public VeiculoAlugado() {
		super("O veículo já encontra-se alugado");
	}
}