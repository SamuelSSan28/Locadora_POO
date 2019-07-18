package locadora;

public class VeiculoNaoCadastrado extends Exception {

	public VeiculoNaoCadastrado() {
		super("O veículo não encontra-se cadastrado");
	}
}