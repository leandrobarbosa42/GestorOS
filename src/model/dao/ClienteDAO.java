package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dal.CriarConexao;
import javafx.scene.control.Alert.AlertType;
import model.Cliente;
import util.AlertaUtil;

public class ClienteDAO {
	
	public int cadastrarCliente(Cliente c) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "insert into cliente(nome_cliente, data_nascimento, endereco_id, email, telefone_id) values(?,?,?,?,?)";
		ResultSet generatedKeys = null; 
		int idGerado = -1;
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, c.getNome());
			// Converter LocalDate para java.sql.Date
			stmt.setDate(2, java.sql.Date.valueOf(c.getDataNascimento()));//Converte LocalDate para Date			
			stmt.setInt(3, c.getIdEndereco());
			stmt.setString(4, c.getEmail());
			stmt.setInt(5, c.getIdTelefone());
			
			int rowsAffected = stmt.executeUpdate();

	        if (rowsAffected > 0) {
	            generatedKeys = stmt.getGeneratedKeys();
	            if (generatedKeys.next()) {
	                idGerado = generatedKeys.getInt(1);	                
	            }
	        }
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso","Cliente cadastrado com sucesso!");//Exibe mensagem de sucesso
		} catch (Exception e) {
			String erro = "Falha ao cadastrar cliente: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, generatedKeys);}

		return idGerado;
	}
	// Não estou usando por enquanto
	public void atualizaTelefone(Cliente c) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "update cliente set telefone_id=? where id=?";
		
		try {
			conexao = CriarConexao.conector();
			stmt= conexao.prepareStatement(sql);
			stmt.setInt(1, c.getIdTelefone());
			stmt.setInt(2, c.getId());
			stmt.executeUpdate();
			
		}catch (Exception e) {
			String erro = "Falha ao atualizar telefone: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

	}
	// Não estou usando por enquanto
	public void atualizaEndereco(Cliente c) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "update cliente set endereco_id=? where id=?";
		
		try {
			conexao = CriarConexao.conector();
			stmt= conexao.prepareStatement(sql);
			stmt.setInt(1, c.getIdEndereco());
			stmt.setInt(2, c.getId());
			stmt.executeUpdate();
			
		}catch (Exception e) {
			String erro = "Falha ao atualizar endereço: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

	}

	public List<Cliente> buscaNome(String nome){
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.id, c.nome_cliente, c.data_nascimento, c.endereco_id, c.email, t.telefone1, t.telefone2, c.telefone_id FROM cliente c JOIN telefone t ON c.telefone_id = t.id WHERE c.nome_cliente LIKE ?";
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, nome+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente cliente = new Cliente();	
				cliente.setId(rs.getInt(1));
				cliente.setNome(rs.getString(2));
				cliente.setDataNascimento(rs.getDate(3).toLocalDate());
				cliente.setIdEndereco(rs.getInt(4));;
				cliente.setEmail(rs.getString(5));
				cliente.setTelefone1(rs.getString(6));;
				cliente.setTelefone2(rs.getString(7));
				cliente.setIdTelefone(rs.getInt(8));
				clientes.add(cliente);
			}
		}catch (Exception e) {
			String erro = "Falha ao buscar clientes: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt, rs);}

		
		return clientes;
	}

	public void removerCliente(int id) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "delete from cliente where id=?";
		
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso!", "Cliente deletado com sucesso.");//Exibe mensagem de sucesso
			
		} catch (Exception e) {
			String erro = "Falha ao deletar cliente: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

		
	}

	public void atualizarCliente(Cliente c) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "update cliente set nome_cliente=?, data_nascimento=?, email=? where id=?";
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, c.getNome());
			stmt.setDate(2, java.sql.Date.valueOf(c.getDataNascimento())); //Converte LocalDate para Date
			stmt.setString(3, c.getEmail());
			stmt.setInt(4, c.getId());
			
			stmt.executeUpdate();
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso","Cliente atualizado com sucesso!");//Exibe mensagem de sucesso
			
		} catch (Exception e) {
			String erro = "Falha ao atualizar cliente: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

		
	}
}
