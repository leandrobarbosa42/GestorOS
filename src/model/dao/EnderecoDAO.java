package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dal.CriarConexao;
import javafx.scene.control.Alert.AlertType;
import model.Endereco;
import util.AlertaUtil;

public class EnderecoDAO {
	public int cadastrarEndereco(Endereco end) {
		int idEndereco = -1;
		String sql = "insert into endereco(rua, numero,bairro,cidade,uf)values(?,?,?,?,?);";
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet generatedKeys = null;
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1,end.getRua());
			stmt.setString(2,end.getNumero());
			stmt.setString(3,end.getBairro());
			stmt.setString(4,end.getCidade());
			stmt.setString(5,end.getUf());
			
			int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                idEndereco = generatedKeys.getInt(1);	                
	            }
	        }
	        
		}catch (Exception e) {
			String erro = "Falha ao cadastrar endereço: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, generatedKeys);}
	
		return idEndereco;
	}
	
	
	public Endereco buscaEndereco(int id) {
		Endereco endereco = new Endereco();
		String sql = "select * from endereco where id=?";
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				endereco.setRua(rs.getString(2));
				endereco.setNumero(rs.getString(3));
				endereco.setBairro(rs.getString(4));
				endereco.setCidade(rs.getString(5));
				endereco.setUf(rs.getString(6));
				
			}
			
		} catch (Exception e) {
			String erro = "Falha ao buscar endereço: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, rs);}

		
		return endereco;
	}

	public void deletaEndereco(int id) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "delete from endereco where id=?";		
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();			
			
		} catch (Exception e) {
			String erro = "Falha ao deletar Endereco: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

		
	}
	
	public void atualizaEndereco(Endereco t) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "update endereco set rua=?, numero=?,bairro=?, cidade=?, uf=? where id=?";		
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, t.getRua());
			stmt.setString(2, t.getNumero());
			stmt.setString(3, t.getBairro());
			stmt.setString(4, t.getCidade());
			stmt.setString(5, t.getUf());
			stmt.setInt(6, t.getId());
			
			stmt.executeUpdate();			
			
		} catch (Exception e) {
			String erro = "Falha ao atualizar endereço: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}
	}
}
