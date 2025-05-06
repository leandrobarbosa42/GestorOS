package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dal.CriarConexao;
import javafx.scene.control.Alert.AlertType;
import model.Usuario;
import session.Sessao;
import util.AlertaUtil;

//DAO usuarios
public class UsuarioDAO {

	//Realiza o login e seta o usuario logado
	public boolean login(String login, String senha) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	String sql = "select * from usuarios where login=? and senha=?";
    	boolean check = false;
    	try {
    		
    		conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			stmt.setString(2, senha);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				//Cria variavel usuario logado e o adciona na sessão
				Usuario usuario = new Usuario(rs.getInt(1),rs.getString(2),rs.getString(5));
				Sessao.setUsuarioLogado(usuario);
				check = true;
		        
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {CriarConexao.fecharConexao(conexao, stmt, rs);} //Fecha a conexao
    	
    return check;
    }

	//Cadastrar novo usuario - CREATE
	public void cadastrar(Usuario usuario) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql = "insert into usuarios(iduser, usuario, login, senha, perfil) values(?,?,?,?,?)";
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getIduser());
			stmt.setString(2, usuario.getNome());
			stmt.setString(3, usuario.getUsuario());
			stmt.setString(4, usuario.getSenha());
			stmt.setString(5, usuario.getPerfil());
			
			stmt.executeUpdate();			
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso","Usuário cadastrado com sucesso!");//Exibe mensagem de sucesso
			
		} catch (Exception e) {
			String erro = "Falha ao cadastrar usuário: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		} finally {CriarConexao.fecharConexao(conexao, stmt);} //Fecha a conexao

		
	}
	//Busca um usuário pelo nome ou id, retorna um objeto usuario - READ
	public  Usuario buscar(String id, String nome) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String buscarPor = null;
		
		if(id.isEmpty()){
			sql="select * from usuarios where usuario=?";
			buscarPor = nome;
		}else {
			sql="select * from usuarios where iduser=?";
			buscarPor =id;
		}
		
		Usuario usuarioEncontrado = new Usuario();
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, buscarPor);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				usuarioEncontrado.setIduser(rs.getInt(1));
				usuarioEncontrado.setNome(rs.getString(2));;
				usuarioEncontrado.setUsuario(rs.getString(3));;
				usuarioEncontrado.setSenha(rs.getString(4));;
				usuarioEncontrado.setPerfil(rs.getString(5));;
			}else {AlertaUtil.exibir(AlertType.ERROR, "Erro!", "Usuário não encontrado");}
			
			
		} catch (Exception e) {
			String erro = "Erro ao buscar usuário: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		} finally {CriarConexao.fecharConexao(conexao, stmt, rs);}

		return usuarioEncontrado;
	}
	//Apaga um usuáruio - DELETE
	public void deletar(Usuario u) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql ="delete from usuarios where iduser=?";

		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, u.getIduser());
			stmt.executeUpdate();
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso!", "Usuário deletado com sucesso.");//Exibe mensagem de sucesso
			
			
		} catch (Exception e) {
			String erro = "Erro: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}
		
	}
	
	
	//Atualiza dados do usuário - UPDATE
	public void atualizar(Usuario u) {
		Connection conexao = null;
		PreparedStatement stmt = null;
		String sql ="update usuarios set usuario=?, login=?, senha=?, perfil=? where iduser=?;";
		
		try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			
			stmt.setString(1,u.getNome());
			stmt.setString(2,u.getUsuario());
			stmt.setString(3,u.getSenha());
			stmt.setString(4,u.getPerfil());
			stmt.setInt(5,u.getIduser());
			
			stmt.executeUpdate();
			
			AlertaUtil.exibir(AlertType.CONFIRMATION, "Sucesso!", "Usuário atualizado com sucesso!");
		} catch (Exception e) {
			String erro = "Erro ao atualizar: " + e;
			AlertaUtil.exibir(AlertType.ERROR,"Falha",erro); //Exibe mensagem de erro
		}finally {CriarConexao.fecharConexao(conexao, stmt);}

	}
//Checa se o ID está disponivel 
	public boolean idDisponivel(String id) {
		boolean disponivel = true;
		Connection conexao = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
    	String sql = "select iduser from usuarios where iduser=?";
    	try {
			conexao = CriarConexao.conector();
			stmt = conexao.prepareStatement(sql);
			stmt.setString(1, id);
			rs = stmt.executeQuery();
			
			if(rs.next()) { disponivel = false;}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {CriarConexao.fecharConexao(conexao, stmt, rs);}

		
		return disponivel;
		
	}
}
