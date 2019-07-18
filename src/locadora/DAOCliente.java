package locadora;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOCliente {

	public void inserir(Cliente c) throws SQLException, ClassNotFoundException, ClienteJaCadastrado {
        Connection con;
	    try {
			Cliente cli = pesquisarPor(c.getCpf());
			throw new ClienteJaCadastrado();
		} catch (ClienteNaoCadastrado e1) {		
			con = Conexao.getConnection();
	        Statement st = con.createStatement();
	        	String cmd = "insert into cliente values (" +
	                   c.getCpf() + ", \'" + c.getNome() + "\')"; 
	        	st.execute(cmd);
		}
	}

	public void alterar() {
		
	}

	public void remover(int cpf) {
		
	}
	
	public void removerTodos() throws ClassNotFoundException, SQLException {
		Connection con = Conexao.getConnection();
        Statement st = con.createStatement();
    	    String cmd = "delete from cliente"; 
    	    st.execute(cmd);
	}

	public Cliente pesquisarPor(int cpf) throws ClienteNaoCadastrado {
        Connection con;
        try {
        	con = Conexao.getConnection();
            Statement st = con.createStatement();
            
        	    String cmd = "select * from cliente where cpf = " + cpf; 
        	    ResultSet rs = st.executeQuery(cmd);
        	    if (rs.next()) {
        	    	   String nome = rs.getString("nome");
        	    	   Cliente c = new Cliente(cpf, nome);
        	    	   return c;
        	    } 	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new ClienteNaoCadastrado();
	}

	public ArrayList<Cliente> pesquisarPor(String parteDoNome) throws ClienteNaoCadastrado {
		Connection con;
        try {
			con = Conexao.getConnection();
            Statement st = con.createStatement();
        	    String cmd = "select * from cliente where nome like \'%" + parteDoNome + "%\'"; 
        	    ResultSet rs = st.executeQuery(cmd);
        	    ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        	    while (rs.next()) {
     	    	   String nome = rs.getString("nome");
     	    	   int cpf = rs.getInt("cpf");
        	    	   Cliente c = new Cliente(cpf, nome);
        	    	   clientes.add(c);
        	    } 
        	    st.close();
        	    return clientes;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new ClienteNaoCadastrado();		
	}

}
