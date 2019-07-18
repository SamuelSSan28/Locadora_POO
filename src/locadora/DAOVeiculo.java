package locadora;

import java.sql.*;

public class DAOVeiculo {
	public void inserir(Veiculo v) throws SQLException, ClassNotFoundException, VeiculoJaCadastrado {
        Connection con;
	    try {
			Veiculo v2 = pesquisarPor(v.getPlaca());
			throw new VeiculoJaCadastrado();
		} catch (VeiculoNaoCadastrado e1) {		
			con = Conexao.getConnection();
			String sql = "INSERT INTO veiculo(placa,marca,modelo,ano,valorAvaliado,valorDiaria,tipo,cilindradas,carga,passageiros,identificador)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);    

            stmt.setString(1, v.getPlaca());    
            stmt.setString(2, v.getMarca());    
            stmt.setString(3, v.getModelo());   
            stmt.setString(4, String.valueOf(v.getAnoDeFabricacao()));    
            stmt.setString(5, String.valueOf(v.getValorAvaliado()));    
            stmt.setString(6, String.valueOf(v.getValorDiaria()));   
            //-------------------------------------
            
            if(v instanceof Carro) {
            	stmt.setString(7, String.valueOf(  ((Carro) v).getTipo()  ));   
            	stmt.setString(8, String.valueOf(0));    
                stmt.setString(9, String.valueOf(0));   
                stmt.setString(10, String.valueOf(0)); 
                stmt.setString(11, String.valueOf(1)); 
            }
            else if(v instanceof Moto) {
            	stmt.setString(7, String.valueOf(0));   
            	stmt.setString(8, String.valueOf(   ((Moto) v).getCilindrada()));    
                stmt.setString(9, String.valueOf(0));   
                stmt.setString(10, String.valueOf(0)); 
                stmt.setString(11, String.valueOf(2)); 
            }
            else if(v instanceof Caminhao) {
            	stmt.setString(7, String.valueOf(0));   
            	stmt.setString(8, String.valueOf(0));    
                stmt.setString(9, String.valueOf(  ((Caminhao) v).getCarga() ));   
                stmt.setString(10, String.valueOf(0)); 
                stmt.setString(11, String.valueOf(3)); 
            }
            else if(v instanceof Onibus) {
            	stmt.setString(7, String.valueOf(0));   
            	stmt.setString(8, String.valueOf(0));    
                stmt.setString(9, String.valueOf( 0));   
                stmt.setString(10, String.valueOf( ((Onibus) v).getPassageiros()  )); 
                stmt.setString(11, String.valueOf(4)); 
            }
            
              

            stmt.execute(); //executa comando   
            stmt.close();    
		}
	}
	
	
	public Veiculo pesquisarPor(String placa) throws VeiculoNaoCadastrado {
        Connection con;
        Veiculo v = null;
        try {
        	con = Conexao.getConnection();
            Statement st = con.createStatement();
        	    String cmd = "select * from veiculo where placa =  '" + placa + "'"; 
        	    //System.out.println(cmd);
        	    ResultSet rs = st.executeQuery(cmd);
        	    if (rs.next()) {
        	    	   String nPlaca,modelo,marca;
        	    	   int ano, tipo,cilidradas,carga,passageiros,identificador;
        	    	   double valorAvaliado,valorDiaria;
        	    	   nPlaca = rs.getString("placa");
        	    	   marca = rs.getString("marca");
        	    	   modelo = rs.getString("modelo");
        	    	   tipo = Integer.parseInt(rs.getString("tipo"));
        	    	   ano =  Integer.parseInt(rs.getString("ano"));
        	    	   cilidradas = Integer.parseInt(rs.getString("cilindradas"));
        	    	   carga = Integer.parseInt(rs.getString("carga"));
        	    	   passageiros = Integer.parseInt(rs.getString("passageiros"));
        	    	   valorAvaliado = Double.parseDouble( rs.getString("valorAvaliado"));
        	    	   valorDiaria = Double.parseDouble( rs.getString("valorDiaria"));
        	    	   identificador = Integer.parseInt(rs.getString("identificador"));
        	    	   
        	    	   if(identificador == 1) 
        	    		   v = new Carro(marca,modelo,ano,valorAvaliado,valorDiaria,placa,tipo);  
        	    	   else if(identificador == 2)
        	    		   v = new Moto(marca,modelo,ano,valorAvaliado,valorDiaria,placa,cilidradas);
        	    	   else if (identificador == 3)
        	    		   v = new Caminhao(marca,modelo,ano,valorAvaliado,valorDiaria,placa,carga);
        	    	   else if(identificador == 4)
        	    		   v = new Onibus(marca,modelo,ano,valorAvaliado,valorDiaria,placa,passageiros);
        	    	   
        	    	   
        	    	   return v;
        	    	   
        	    } 	    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        throw new VeiculoNaoCadastrado();
	}
	
	public void removerTodos() throws ClassNotFoundException, SQLException {
		Connection con = Conexao.getConnection();
	
        Statement st = con.createStatement();
    	    String cmd = "delete from veiculo"; 
    	    st.execute(cmd);
	}

}
