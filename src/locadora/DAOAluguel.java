package locadora;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAluguel {
	public void inserir(Aluguel a) throws SQLException, ClassNotFoundException, ClienteJaCadastrado, AluguelJaCadastrado, ClienteNaoCadastrado, VeiculoNaoCadastrado {
        Connection con = null;
	    
			Aluguel alu;
			try {
				alu = pesquisarAluguel(a.getCliente().getCpf(),a.getVeiculo().getPlaca());
				throw new AluguelJaCadastrado();
			} catch (AluguelNaoCadastrado e) {
				con = Conexao.getConnection();
				String sql = "INSERT INTO aluguel(idCliente,idVeiculo,valor,dias,fechado) VALUES(?,?,?,?,?)";
				PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
				
				stmt.setString(1, String.valueOf(a.getCliente().getCpf()));    
	            stmt.setString(2, a.getVeiculo().getPlaca());    
	            stmt.setString(3, String.valueOf(a.getDias()));   
	            stmt.setString(4, String.valueOf(a.getValor()));    
	            stmt.setString(5,"0");  
	            
	            stmt.execute(); //executa comando   
	            stmt.close(); 
			}
			
	    
			
		
	}

	public void alterar(Aluguel a) {
		
	}

	public void remover(int cpf) {
		
	}
	
	public void removerTodos() throws ClassNotFoundException, SQLException {
		Connection con = Conexao.getConnection();
        Statement st = con.createStatement();
    	    String cmd = "delete from aluguel"; 
    	    st.execute(cmd);
	}

	public Aluguel pesquisarAluguel(int cpf,String placa) throws ClienteNaoCadastrado, VeiculoNaoCadastrado, AluguelNaoCadastrado  {
		DAOCliente daoC = new DAOCliente();
		DAOVeiculo daoV = new DAOVeiculo();
		Connection con;
		
		Cliente c = daoC.pesquisarPor(cpf);
		//System.out.println(placa);
		Veiculo v = daoV.pesquisarPor(placa);
		try {
        	con = Conexao.getConnection();
            Statement st = con.createStatement();
        	    String cmd = "select * from aluguel where idVeiculo = '" + placa + "' AND idCliente = " + cpf; 
        	    ResultSet rs = st.executeQuery(cmd);
        	    if (rs.next()) {
        	    	   int dias;
        	    	   double valor;
        	    	   dias = Integer.parseInt(rs.getString("dias"));
        	    	   valor = Double.parseDouble( rs.getString("valor"));
        	    	   
        	    	   Aluguel alu = new Aluguel(v,c,dias,valor);
        	    	   
        	    	   
        	    	   return alu;
        	    	   
        	    } 	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		       
        
        throw new AluguelNaoCadastrado();
	}

	public ArrayList<Cliente> pesquisarPor(String parteDoNome) {
		return null;
		
	}
}
