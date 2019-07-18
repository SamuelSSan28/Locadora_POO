package locadora;

public class Aluguel {
	private Veiculo veiculo;
	private Cliente cliente;
	private int dias;
	private double valor;
	private boolean fechado;
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public int getDias() {
		return dias;
	}
	public void setDias(int dias) {
		this.dias = dias;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public boolean getFechado() {
		return fechado;
	}
	public Aluguel(Veiculo vei, Cliente c, int d, double v) {
		cliente = c;
		veiculo = vei;
		dias = d;
		valor  = v;
		fechado = false;
	}
	public boolean isFechado() {
		return fechado;
	}
	public void setFechado(boolean fechado) {
		this.fechado = fechado;
	}
}
