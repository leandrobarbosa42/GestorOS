package dal;

import java.sql.*;
//Abrir conexão com o banco
public class CriarConexao {
	public static Connection conector() {
		java.sql.Connection conexao = null;
		//Chamar o driver do MySql
		String driver = "com.mysql.cj.jdbc.Driver"; 
		//Informações do Banco de dados
		String URL = "jdbc:mysql://localhost:3306/gestoros"; //Banco de dados
	    String USER = "root"; //Usuario DB
	    String PASSWORD = ""; //Senha DB
	    
	    try {
			Class.forName(driver);
			conexao = DriverManager.getConnection(URL,USER,PASSWORD);
			return conexao;
		} catch (Exception e) {
            return null;
        } 	}
	
	//Metodos para encerrar a conexão
	public static void fecharConexao(Connection con) {
		try {
			if(con!=null) {
				con.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void fecharConexao(Connection con, PreparedStatement stmt) {
		fecharConexao(con);
		try {
			if(stmt!=null) {
				stmt.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void fecharConexao(Connection con, PreparedStatement stmt, ResultSet rs) {
		fecharConexao(con, stmt);
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
}
