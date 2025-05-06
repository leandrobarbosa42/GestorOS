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

public class telaClienteAcaoController implements Initializable {

    @FXML
    private Button botaoClienteExistente;

    @FXML
    private Button botaoNovoCliente;

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
		botaoNovoCliente.setGraphic(ImageUtil.configurarImagem("/icones/useradd.png", 80, 80));
		botaoClienteExistente.setGraphic(ImageUtil.configurarImagem("/icones/update.png", 80, 80));
	}

    public void novoCliente(ActionEvent event) {
		JanelaAuxiliar.abrirJanela("/view/telaCadastroCliente.fxml", "Cadastrar Novo Cliente", true);
		// Fecha a janela atual
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
    }
    
    public void alterarCliente(ActionEvent event) {
		JanelaAuxiliar.abrirJanela("/view/telaAlterarCliente.fxml", "Alterar Dados Cliente", true);
		// Fecha a janela atual
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.close();
    }

}
