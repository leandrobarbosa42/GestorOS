package controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.JanelaAuxiliar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.ImageUtil;
//Tela controle da tela cadastro ação 
public class telaCadastroAcaoController implements Initializable{

    @FXML
    private Button botaoNovoUsuario;

    @FXML
    private Button botaoUsuarioExistente;

    @FXML
    private Label labelAcao;

    @FXML
    private Label labelAlterar;

    @FXML
    private Label labelCadastrar;

    @FXML
    private Label labelCadastro;

    @FXML
    private Label labelExisteUsuario;

    @FXML
    private Label labelNovoUsuario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Setando icones botões
		botaoNovoUsuario.setGraphic(ImageUtil.configurarImagem("/icones/useradd.png", 80, 80));
		botaoUsuarioExistente.setGraphic(ImageUtil.configurarImagem("/icones/update.png", 80, 80));
	}
	//Abre a tela de cadastro de usuário
	public void cadastrar(ActionEvent event) {
		JanelaAuxiliar.abrirJanela("/view/telaCadastroUsuario.fxml", "Cadastrar Novo Usuário", true);
		// Fecha a janela atual
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}
	//Abre a tela de alterações de dados de usuários
	public void alterar(ActionEvent event) {
		JanelaAuxiliar.abrirJanela("/view/telaAlterarUsuario.fxml", "Alterar Dados Usuário", true);
		// Fecha a janela atual
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
	}

}
