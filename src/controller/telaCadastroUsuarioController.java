package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import model.Usuario;
import model.dao.UsuarioDAO;
import util.AlertaUtil;
import util.ImageUtil;
//Classe de controle da tela cadastro usuário
public class telaCadastroUsuarioController implements Initializable{

    @FXML
    private ComboBox<String> comboBoxPerfil;

    @FXML
    private Label labelID;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelPerfil;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelUsuario;

    @FXML
    private TextField textoID;

    @FXML
    private TextField textoNome;

    @FXML
    private PasswordField textoSenha;

    @FXML
    private TextField textoUsuario;
    
    @FXML
    private Button botaoCreate;
    
    @FXML
    private Label labellDisponivel;
    
    @FXML
    private ImageView imagemDisponivel;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	//Setando opções combo box
    	comboBoxPerfil.getItems().add("user");
    	comboBoxPerfil.getItems().add("admin");
    	
    	//Configurações botão create
    	botaoCreate.setGraphic(ImageUtil.configurarImagem("/icones/confirm.png", 80, 80));
    	Tooltip tooltip = new Tooltip("Cadastrar");
    	botaoCreate.setTooltip(tooltip);
    	
    	//Mudando formato aceito pelo campo ID
    	textoID.setTextFormatter(new TextFormatter<String>(change -> {
    	    String newText = change.getControlNewText();
    	    if (newText.matches("\\d{0,3}")) {
    	        return change;
    	    }
    	    return null;
    	}));
    	
    	//Foco campo ID, para checar id disponível
    	textoID.focusedProperty().addListener((obs, oldVal, newVal) -> {
    	    if (!newVal) { // perdeu o foco
    	        idDisponivel(); // chama seu método
    	    }
    	});
    }
    
    public void cadastrarUsuario(ActionEvent event) {
    	
    	try {
    		
    		int id = Integer.parseInt(textoID.getText());
    		String nome =textoNome.getText().trim();
    		String user = textoUsuario.getText().trim();
    		String senha = textoSenha.getText().trim();
    		String perfil = comboBoxPerfil.getValue();
    		
    		if(nome.isEmpty() || user.isEmpty() || senha.isEmpty() || perfil == null) {
    			AlertaUtil.exibir(AlertType.INFORMATION, "Campo vazio!", "Preencha todos os campos.");
    		}else {
        		//Criando usuario com base nos campos preenchidos
        		Usuario usuario = new Usuario(id,nome,user,senha,perfil);
        		//Criando DAO do usário
        		UsuarioDAO uDao = new UsuarioDAO();
        		
        		//Chamando o metodo de cadastro e passando usuário como parâmetro	
        		uDao.cadastrar(usuario); 
        		
        		//Limpando os campos
        		textoID.setText("");
        		textoNome.setText("");
        		textoUsuario.setText("");
        		textoSenha.setText("");
        		comboBoxPerfil.setValue(null);
    		}			
		}catch(NumberFormatException ne) {
			String erro = "ID inválido!";
			AlertaUtil.exibir(AlertType.INFORMATION, "Erro!", erro); //Mensagem de erro
		} catch (Exception e) {
			String erro = "Erro ao tentar atualizar: " + e;
			AlertaUtil.exibir(AlertType.INFORMATION, "Erro!", erro); //Mensagem de erro
		}
    }

    
    public void idDisponivel() {
    	UsuarioDAO dao = new UsuarioDAO();
    	String id = textoID.getText();
    	if(id.equals("") || id.equals("0") || id.isEmpty()) {
    		labellDisponivel.setText("Preencha o campo ID!");
    		imagemDisponivel.setImage(null);
    	}else {
    		
        	if(dao.idDisponivel(id)) {
        		labellDisponivel.setText("N° de ID: "+id+" está disponível!");
        		imagemDisponivel.setImage(ImageUtil.configurarImagemNormal("/icones/dispo.png"));
        		
        	}else {
        		labellDisponivel.setText("N° de ID: "+id+" não está disponível!");
        		imagemDisponivel.setImage(ImageUtil.configurarImagemNormal("/icones/naoDispo.png"));
        		textoID.setText("");
        	}
        	
    	}
    	

    }
}
