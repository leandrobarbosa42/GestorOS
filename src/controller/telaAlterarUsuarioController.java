package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.Usuario;
import model.dao.UsuarioDAO;
import util.AlertaUtil;
import util.ImageUtil;
//Controles da tela alterar usuário
public class telaAlterarUsuarioController implements Initializable {

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoRemover;

    @FXML
    private ComboBox<String> comboBoxPerfil;

    @FXML
    private ImageView imagemAlterar;

    @FXML
    private Label labelAlterarCadastro;

    @FXML
    private Label labelAtualizar;

    @FXML
    private Label labelBuscar;

    @FXML
    private Label labelBuscarID;

    @FXML
    private Label labelBuscarNome;

    @FXML
    private Label labelID;

    @FXML
    private Label labelNome;

    @FXML
    private Label labelPerfil;

    @FXML
    private Label labelRemover;

    @FXML
    private Label labelSenha;

    @FXML
    private Label labelUsuario;

    @FXML
    private TextField textoBuscarID;

    @FXML
    private TextField textoBuscarNome;

    @FXML
    private TextField textoID;

    @FXML
    private TextField textoNome;

    @FXML
    private PasswordField textoSenha;

    @FXML
    private TextField textoUsuario;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	//Setando opções combo box
    	comboBoxPerfil.getItems().add("user");
    	comboBoxPerfil.getItems().add("admin");
    	
    	//Setando icones dos botões
    	 botaoAtualizar.setGraphic(ImageUtil.configurarImagem("/icones/update.png", 70, 70));  	  
    	 botaoBuscar.setGraphic(ImageUtil.configurarImagem("/icones/search.png", 40, 40));
    	 botaoRemover.setGraphic(ImageUtil.configurarImagem("/icones/delete.png", 70, 70));
		
    	 //Setando id
    	 textoBuscarID.setText("");
    	 textoID.setText("");
	}
	//Busca usuario e preenche os campos com suas informações
	public void buscarUsuario(ActionEvent event) {
		UsuarioDAO daoUsuario = new UsuarioDAO();
		Usuario usuario = daoUsuario.buscar(textoBuscarID.getText(),textoBuscarNome.getText());
		
		textoBuscarID.setText(""); //Limpa os campos de busca
		textoBuscarNome.setText(null);
		
		textoID.setText(String.valueOf(usuario.getIduser()));
		textoNome.setText(usuario.getNome());
		textoUsuario.setText(usuario.getUsuario());
		textoSenha.setText(usuario.getSenha());
		comboBoxPerfil.setValue(usuario.getPerfil());
	}
	
	public void deletarUsuario(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão usuário");
        alert.setHeaderText("Deseja realmente excluir o registro do usuário?");
        alert.setContentText("Após confirmar não será possível desfazer a operação. Se realmente deseja excluir o usuário, pressione OK.");
		
        // Mostra o alerta e espera pela resposta do usuário
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Lógica caso o usuário confirme a saída    		        	
        	if(textoID.getText().isEmpty() || textoID.getText().equals("0")) {
        		AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Nenhum usuário selecionado.");
        		textoID.setText("");
        	}else {
            	try {
            		Usuario usuario = new Usuario();	
            		usuario.setIduser(Integer.parseInt(textoID.getText()));
            		
            		UsuarioDAO dao = new UsuarioDAO();
            		dao.deletar(usuario);
            		
            		textoID.setText("");
            		textoNome.setText("");
            		textoUsuario.setText("");
            		textoSenha.setText("");
            		comboBoxPerfil.setValue("");
    			} catch (Exception e) {
    				AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Formato de ID inválido.");
    			}
        	}        	
        } else {
            // Usuário cancelou — nenhuma ação necessária           
        }
	}

	public void atualizaUsuario(ActionEvent event) {
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
            		
            		//Chamando metodo para atualizar dados
            		uDao.atualizar(usuario);
            		
            		//Limpando os campos
            		textoID.setText("");
            		textoNome.setText("");
            		textoUsuario.setText("");
            		textoSenha.setText("");
            		comboBoxPerfil.setValue(null);
    			}
    		
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
