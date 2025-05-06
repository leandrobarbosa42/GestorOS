package dal;

import java.sql.Connection;
//Checar conexão com o banco
public class CheckConexao {
	public boolean conexaoOK() {
		Connection conexao = null;
		boolean check = false;
		
		try {
			conexao = CriarConexao.conector();
			if(conexao != null) {check = true;}
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	};
	
}
