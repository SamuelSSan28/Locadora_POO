package locadora;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class testes {

	@Test
	public void testInserirCliente() throws ClassNotFoundException, SQLException, ClienteJaCadastrado {
		DAOCliente dao = new DAOCliente();
		dao.removerTodos();
		
		Cliente c1 = new Cliente(1, "Pedro");
		Cliente c2 = new Cliente(2, "João");
		Cliente c3 = new Cliente(3, "Raimundo");
		Cliente c4 = new Cliente(4, "Kelson");
		Cliente c5 = new Cliente(5, "Rodrigo");
		
		dao.inserir(c1);
		dao.inserir(c2);
		dao.inserir(c3);
		dao.inserir(c4);
		dao.inserir(c5);

		try {
			dao.inserir(c3);
			fail("Era pra ter gerado exceção de cliente já cadastrado.");
		} catch (ClienteJaCadastrado e) {
			// Exceção esperada!
		}
	}
	
	
	
	@Test
	public void testInserirVeiculo() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, VeiculoJaCadastrado {
		DAOVeiculo dao = new DAOVeiculo();
		dao.removerTodos();
		
		Veiculo moto = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "X-921", 200);
		Veiculo onibus = new Onibus("Joca Motores", "Kall'anggo", 1978, 40000, 70, "Q-123", 50);
		
		
		dao.inserir(moto);
		dao.inserir(carro);
		dao.inserir(caminhao);
		dao.inserir(onibus);
		
		try {
			dao.inserir(moto);
			fail("Era pra ter gerado exceção de cliente já cadastrado.");
		} catch (VeiculoJaCadastrado e) {
			// Exceção esperada!
		}
		
		
	}
	
	@Test
	public void testCadastrarAluguel() throws ClassNotFoundException, SQLException, ClienteJaCadastrado, VeiculoJaCadastrado, AluguelJaCadastrado, ClienteNaoCadastrado, VeiculoNaoCadastrado {
		DAOVeiculo dao = new DAOVeiculo();
		DAOCliente daoC = new DAOCliente();
		DAOAluguel daoA = new DAOAluguel();
		daoC.removerTodos();
		dao.removerTodos();
		daoA.removerTodos();
		
		Veiculo moto = new Moto("Estrela", "Andromeda", 1975, 15000, 40, "X-911", 50);
		Veiculo carro = new Carro("Estrela", "Antares", 1980, 20000, 50, "A-100", 1);
		Veiculo caminhao = new Caminhao("Estrela", "Betelgeuse", 1975, 30000, 70, "X-921", 200);
		Veiculo onibus = new Onibus("Joca Motores", "Kall'anggo", 1978, 40000, 70, "Q-123", 50);
		Cliente c1 = new Cliente(1, "Pedro");
		Cliente c2 = new Cliente(2, "João");
		Cliente c3 = new Cliente(3, "Raimundo");
		
		Aluguel a = new Aluguel(moto, c1, 10, moto.valorDiaria);
		Aluguel a2 = new Aluguel(carro, c3, 10, carro.valorDiaria);
		Aluguel a3 = new Aluguel(onibus, c2, 10, onibus.valorDiaria);
		Aluguel a4 = new Aluguel(caminhao, c1, 10, caminhao.valorDiaria);
		Aluguel a5 = new Aluguel(moto, c3, 10, moto.valorDiaria);
		
		dao.inserir(moto);
		dao.inserir(carro);
		dao.inserir(caminhao);
		dao.inserir(onibus);
		daoC.inserir(c1);
		daoC.inserir(c2);
		daoC.inserir(c3);
		
		daoA.inserir(a);
		daoA.inserir(a2);
		daoA.inserir(a3);
		daoA.inserir(a4);
		daoA.inserir(a5);
	}

}
