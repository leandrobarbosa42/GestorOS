package controller;

import application.AppNavigator;
import dal.CheckConexao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.dao.UsuarioDAO;
//Classe controle tela login
public class telaLoginController {


	   @FXML
	    private Button botaoLogin;

	    @FXML
	    private ImageView iconeLogin;

	    @FXML
	    private ImageView imgBDO;

	    @FXML
	    private Label labelBDO;

	    @FXML
	    private Label labelLogin;

	    @FXML
	    private Label labelSenha;

	    @FXML
	    private PasswordField senhaLogin;

	    @FXML
	    private AnchorPane telalogin;

	    @FXML
	    private TextField textoLogin;

    
    //Imagem conexao banco de dados
    public void setConcluido() {
    	Image imagem = new Image(getClass().getResourceAsStream("/icones/49574_accept_database_icon.png"));
        imgBDO.setImage(imagem);
    }
    public void setFalha() {
    	Image imagem = new Image(getClass().getResourceAsStream("/icones/49610_database_from_remove_icon.png"));
        imgBDO.setImage(imagem);
    }
    

	//Checando conexão BD
    public void ConexaoBD() {
    	CheckConexao check = new CheckConexao();
		if(check.conexaoOK()) {setConcluido();}else {setFalha();}
    }
    
    
    @FXML
    public void logarBD(ActionEvent event) { //Evento botão de login
    		UsuarioDAO dao = new UsuarioDAO();
    		
		if(dao.login(textoLogin.getText(), senhaLogin.getText())) {
			 AppNavigator.mostrarTelaInicial();

		}else {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Erro de Login");
	        alert.setHeaderText("Falha na autenticação");
	        alert.setContentText("Usuário ou senha inválidos.");
	        alert.showAndWait();
		}
		textoLogin.clear();
	    senhaLogin.clear();
    }
    
    

    
}
