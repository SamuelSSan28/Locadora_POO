package locadora;

public class VeiculoJaCadastrado extends Exception {

	public VeiculoJaCadastrado() {
		super("O veículo já encontra-se cadastrado");
	}
}
