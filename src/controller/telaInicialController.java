package controller;

import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import application.AppNavigator;
import application.JanelaAuxiliar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import session.Sessao;
//Classe controller da tela inicial
public class telaInicialController implements Initializable{

	@FXML
    private ImageView imgLogo;

    @FXML
    private Label labelBemVindo;

    @FXML
    private Label labelData;

    @FXML
    private Label labelGestos;

    @FXML
    private Label labelGos;

    @FXML
    private Label labelUsuario;

    @FXML
    private Menu menuBusca;
    
    @FXML
    private MenuItem menuBuscar;

    @FXML
    private MenuItem menuCadastraCliente;

    @FXML
    private MenuItem menuCadastraOS;

    @FXML
    private MenuItem menuCadastraServico;

    @FXML
    private MenuItem menuCadastraUsuario;

    @FXML
    private Menu menuCadastro;

    @FXML
    private MenuItem menuDeslogar;

    @FXML
    private Menu menuRelatorio;
    
    @FXML
    private MenuItem menuGeraRelatorio;

    @FXML
    private Menu menuSair;

    @FXML
    private Menu menuSobre;

    @FXML
    private MenuBar menuTelaInicial;

    @FXML
    private VBox telaInicial;


  //-----------------------------------------------------------------  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Configurando as informações da tela inicial
    	Date data = new Date();
    	DateFormat formatar = DateFormat.getDateInstance(DateFormat.SHORT);
    	labelData.setText(formatar.format(data));
    	labelUsuario.setText(Sessao.getUsuarioLogado().getUsuario()); //Pega o nome do usuário logado
    	
    }
    
    @FXML
    private void sair(ActionEvent event) { //Evento botão sair
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Saída");
        alert.setHeaderText("Deseja realmente sair?");
        alert.setContentText("Clique em OK para sair ou Cancelar para permanecer.");

        // Mostra o alerta e espera pela resposta do usuário
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lógica caso o usuário confirme a saída
        	AppNavigator.mostrarTelaLogin(); //Volta pra tela de login	
        	Sessao.limpar(); //Limpa a sessão
        } else {
            // Usuário cancelou — nenhuma ação necessária
            
        }
    }
//Validação de permissão
    public void permisaoAdmin() {
    	
    	if(Sessao.getUsuarioLogado().getPerfil().equals("admin")) {
    		menuCadastraUsuario.setVisible(true);
    		menuCadastraUsuario.setDisable(false);
    		
    		
        	menuGeraRelatorio.setVisible(true);
        	menuGeraRelatorio.setDisable(false);
		}else {
			menuCadastraUsuario.setVisible(false);
    		menuCadastraUsuario.setDisable(true);
    		
        	menuGeraRelatorio.setVisible(false);
        	menuGeraRelatorio.setDisable(true);
		}
    }
    
    @FXML
    public void cadastroUsuario() {
    	JanelaAuxiliar.abrirJanela("/view/telaCadastroAcao.fxml", "Cadastro", true); //Abrir tela cadastro ação
    }
    @FXML
    public void cadastroCliente() {
    	JanelaAuxiliar.abrirJanela("/view/telaClienteAcao.fxml", "Cadastro", true); //Abrir tela cadastro ação
    }
}


