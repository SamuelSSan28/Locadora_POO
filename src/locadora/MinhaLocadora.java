package locadora;

import java.util.ArrayList;

public class MinhaLocadora implements Locadora {

	ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ArrayList<Aluguel> alugueis = new ArrayList<Aluguel>();
	
	
	@Override
	public void inserir(Veiculo v) throws VeiculoJaCadastrado {
		try {
			Veiculo veiculo = pesquisar(v.getPlaca());
			throw new VeiculoJaCadastrado();
		} catch (VeiculoNaoCadastrado e) {
			veiculos.add(v);
		}
	}

	@Override
	public void inserir(Cliente c) throws ClienteJaCadastrado {
		try {
			Cliente cliente = pesquisarCliente(c.getCpf());
			throw new ClienteJaCadastrado();
		} catch (ClienteNaoCadastrado e) {
			clientes.add(c);
		}
	}
	
	@Override
	public Veiculo pesquisar(String placa) throws VeiculoNaoCadastrado {
      for (Veiculo v : veiculos) {
		if (v.getPlaca() == placa) {
		   return v;
		}
      }
	  throw new VeiculoNaoCadastrado();		
	}
	
	public Cliente pesquisarCliente(int cpf) throws ClienteNaoCadastrado {
      for (Cliente c : clientes) {
		if (c.getCpf() == cpf) {
		   return c;
		}
      }
	  throw new ClienteNaoCadastrado();		
	}

	@Override
	public ArrayList<Veiculo> pesquisarMoto(int cilindrada) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
	    for (Veiculo v : veiculos) {
	      if (v instanceof Moto && 
	    		  ((Moto) v).getCilindrada() 
	    		     >= cilindrada) {
	 	   lista.add(v);
	      }
	    }
	    return lista;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
	    for (Veiculo v : veiculos) {
	      if (v instanceof Carro && 
	    		  ((Carro) v).getTipo()== tipoCarro) {
	 	   lista.add(v);
	      }
	    }
	    return lista;	
	}

	@Override
	public ArrayList<Veiculo> pesquisarCaminhao(int carga) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
	    for (Veiculo v : veiculos) {
	      if (v instanceof Caminhao && 
	    		  ((Caminhao) v).getCarga() 
	    		     >= carga) {
	 	   lista.add(v);
	      }
	    }
	    return lista;	
	}

	@Override
	public ArrayList<Veiculo> pesquisarOnibus(int passageiros) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
	    for (Veiculo v : veiculos) {
	      if (v instanceof Onibus && 
	    		  ((Onibus) v).getPassageiros() 
	    		     >= passageiros) {
	 	   lista.add(v);
	      }
	    }
	    return lista;	}

	@Override
	public double calcularAluguel(String placa, int dias) throws VeiculoNaoCadastrado {
        Veiculo v = pesquisar(placa);
        double valor = v.aluguel(dias);
		return valor;
	}

	@Override
	public void registrarAluguel(String placa, int dias, Cliente c)
			throws VeiculoNaoCadastrado, VeiculoAlugado, ClienteNaoCadastrado {
       Veiculo v = pesquisar(placa);
       Aluguel a = pesquisarAluguelAberto(v);
       if (a != null) {
    	      throw new VeiculoAlugado();
       }
       double valor = v.aluguel(dias);
       Aluguel aluguel = new Aluguel(v, c, dias, valor);
       alugueis.add(aluguel);
	}

	private Aluguel pesquisarAluguelAberto(Veiculo v) {
		for (Aluguel a : alugueis) {
			if (!a.isFechado() && a.getVeiculo().getPlaca() == v.getPlaca()) {
				return a;
			}
		}
		return null;
	}

	@Override
	public void registrarDevolucao(String placa, Cliente c)
			throws VeiculoNaoCadastrado, VeiculoNaoAlugado, ClienteNaoCadastrado {
	   Veiculo v = pesquisar(placa);
	   Aluguel a = pesquisarAluguelAberto(v);
	   if (a == null) {
	    	 throw new VeiculoNaoAlugado();
	   }
	   a.setFechado(true);
	}

	@Override
	public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
       for (Veiculo v : veiculos) {
			if (tipo == 0) {
				v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
			}
			if (tipo == 1 && v instanceof Moto) {
				v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
			}
			if (tipo == 2 && v instanceof Carro) {
				v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
			}
			if (tipo == 3 && v instanceof Caminhao) {
				v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
			}
			if (tipo == 4 && v instanceof Onibus) {
				v.setValorAvaliado(v.getValorAvaliado()*(1-taxaDepreciacao));
			}	
       }
	}

	@Override
	public void aumentarDiaria(int tipo, double taxaAumento) {
	       for (Veiculo v : veiculos) {
				if (tipo == 0) {
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
				}
				if (tipo == 1 && v instanceof Moto) {
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
				}
				if (tipo == 2 && v instanceof Carro) {
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
				}
				if (tipo == 3 && v instanceof Caminhao) {
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
				}
				if (tipo == 4 && v instanceof Onibus) {
					v.setValorDiaria(v.getValorDiaria()*(1+taxaAumento));
				}	
	       }
	   }

	@Override
	public double faturamentoTotal(int tipo) {
		double valor = 0;
		for (Aluguel aluguel : alugueis) {
			if (aluguel.isFechado()) {
				if (tipo == 0) {
					valor += aluguel.getValor();
				}
				if (tipo == 1 && aluguel.getVeiculo() instanceof Moto) {
					valor += aluguel.getValor();
				}
				if (tipo == 2 && aluguel.getVeiculo() instanceof Carro) {
					valor += aluguel.getValor();
				}
				if (tipo == 3 && aluguel.getVeiculo() instanceof Caminhao) {
					valor += aluguel.getValor();
				}
				if (tipo == 4 && aluguel.getVeiculo() instanceof Onibus) {
					valor += aluguel.getValor();
				}
			}
		}
		return valor;
	}

	@Override
	public int quantidadeTotalDeDiarias(int tipo) {
		int valor = 0;
		for (Aluguel aluguel : alugueis) {
			if (aluguel.isFechado()) {
				if (tipo == 0) {
					valor += aluguel.getDias();
				}
				if (tipo == 1 && aluguel.getVeiculo() instanceof Moto) {
					valor += aluguel.getDias();
				}
				if (tipo == 2 && aluguel.getVeiculo() instanceof Carro) {
					valor += aluguel.getDias();
				}
				if (tipo == 3 && aluguel.getVeiculo() instanceof Caminhao) {
					valor += aluguel.getDias();
				}
				if (tipo == 4 && aluguel.getVeiculo() instanceof Onibus) {
					valor += aluguel.getDias();
				}
			}
		}
		return valor;
	}
}
