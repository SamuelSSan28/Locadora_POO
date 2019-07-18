package locadora;

public class VeiculoNaoAlugado extends Exception {

	public VeiculoNaoAlugado() {
		super("O veículo não encontra-se alugado");
	}
}
