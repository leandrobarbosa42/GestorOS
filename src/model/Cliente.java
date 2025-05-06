package model;

import java.time.LocalDate;

public class Cliente {
    private int id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private int idEndereco; 
    private int idTelefone;
    private String telefone1;
    private String telefone2;
  
    
    
    public Cliente(String nome, LocalDate data_nascimento, String email) {
		this.nome = nome;
		this.dataNascimento = data_nascimento;
		this.email = email;
	}
    
	public Cliente() {

	}

	// Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public int getIdEndereco() { return idEndereco; }
    public void setIdEndereco(int idEndereco) { this.idEndereco = idEndereco; }

    public int getIdTelefone() { return idTelefone; }
    public void setIdTelefone(int idTelefone) { this.idTelefone = idTelefone; }
    
	public String getEmail() {return email;	}
	public void setEmail(String email) {this.email = email;	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}
}
