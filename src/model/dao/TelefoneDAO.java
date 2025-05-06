package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dal.CriarConexao;
import javafx.scene.control.Alert.AlertType;
import model.Telefone;
import util.AlertaUtil;

public class TelefoneDAO {
	public int cadastrarTelefone(Telefone t) {
		int idTelefone = -1;
		String sql = "insert into telefone(telefone1, telefone2)values(?,?);";
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet generatedKeys = null;
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, t.getTelefone1());
			stmt.setString(2, t.getTelefone2());

			
			int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                idTelefone = generatedKeys.getInt(1);	                
	            }
	        }
		} catch (Exception e) {
			String erro = "Falha ao cadastrar telefone: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, generatedKeys);}

		
		
		
		return idTelefone;
	}
	//Busca o telefone no banco
	public Telefone telefone(int id) {
		Telefone tel = new Telefone();
	
		String sql = "select telefone1, telefone2 from telefone where id=?";
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				tel.setTelefone1(rs.getString(2));
				tel.setTelefone1(rs.getString(3));
			}
			
			
	        
		} catch (Exception e) {
			String erro = "Falha ao buscar telefone: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, rs);}
		
		return tel;
	}

	public void deletaTelefone(int id) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "delete from telefone where id=?";		
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();			
			
		} catch (Exception e) {
			String erro = "Falha ao deletar telefone: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

		
	}
	
	
	public void atualizaTelefone(Telefone t) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "update telefone set telefone1=? , telefone2=? where id=?";		
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, t.getTelefone1());
			stmt.setString(2, t.getTelefone2());
			stmt.setInt(3, t.getId());
			
			stmt.executeUpdate();			
			
		} catch (Exception e) {
			String erro = "Falha ao atualizar telefone: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}
	}
	
}
