package model;

public class Telefone {
    private int id;
    private String telefone1;
    private String telefone2;
   

    // Construtores
    public Telefone(String tele1, String tele2) {
    	this.telefone1 = tele1;
    	this.telefone2 = tele2;
    }


    public Telefone() {
		
	}


	// Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


