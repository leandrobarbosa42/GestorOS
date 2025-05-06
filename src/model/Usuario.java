package model;
//Model usuário
public class Usuario {
	
	private int iduser;
	private String nome;
	private String usuario;
	private String senha;
	private String perfil;
	

	
	public Usuario(int id, String usuario, String perfil) {
        this.iduser = id;
		this.usuario = usuario;
        this.perfil = perfil;
    }
	
	public Usuario(int id, String nome, String usuario, String senha, String perfil) {
        this.iduser = id;
        this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
        this.perfil = perfil;
    }
	



	public Usuario() {
		
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public int getIduser() {
		return iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	


}
