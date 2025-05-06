package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.Cliente;
import model.Endereco;
import model.Telefone;
import model.dao.ClienteDAO;
import model.dao.EnderecoDAO;
import model.dao.TelefoneDAO;
import util.AlertaUtil;
import util.ImageUtil;

public class telaCadastroClienteController implements Initializable{
	@FXML
    private Button botaoCadastrar;

    @FXML
    private ImageView imagemCadastro;

    @FXML
    private Label labelCadastro;

    @FXML
    private TextField textoBairro;

    @FXML
    private TextField textoCidade;

    @FXML
    private DatePicker textoDatePicker;

    @FXML
    private TextField textoEmail;

    @FXML
    private TextField textoNome;

    @FXML
    private TextField textoNumero;

    @FXML
    private TextField textoRua;

    @FXML
    private TextField textoTelefone1;

    @FXML
    private TextField textoTelefone2;

    @FXML
    private TextField textoUF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Configurar imagem botão
		botaoCadastrar.setGraphic(ImageUtil.configurarImagem("/icones/confirm.png", 80, 80));
		
	    // Permite apenas dígitos numéricos
		textoTelefone1.setTextFormatter(new TextFormatter<>(change -> {
		    String novoTexto = change.getControlNewText();
		    return novoTexto.matches("\\d{0,11}") ? change : null;
		}));

		textoTelefone2.setTextFormatter(new TextFormatter<>(change -> {
		    String novoTexto = change.getControlNewText();
		    return novoTexto.matches("\\d{0,11}") ? change : null;
		}));
		
		textoNumero.setTextFormatter(new TextFormatter<>(change -> {
		    String novoTexto = change.getControlNewText();
		    return novoTexto.matches("\\d{0,11}") ? change : null;
		}));
		
		
		//Mudar formato da data 	
		// Configura o formato do prompt
	    textoDatePicker.setEditable(false); // Impede digitação manual
	    textoDatePicker.setPromptText("dd/MM/yyyy"); // Exibe o formato esperado como dica
	    
	    //Configura campo UF
	    textoUF.setTextFormatter(new TextFormatter<String>(change -> {
	        String novoTexto = change.getControlNewText();

	        // Aceita apenas letras, com no máximo 2 caracteres
	        if (novoTexto.matches("[a-zA-Z]{0,2}")) {
	            change.setText(change.getText().toUpperCase()); // Converte para maiúsculo
	            return change;
	        } else {
	            return null;
	        }
	    }));
		
	}
	
	
	//Cadastrar novo cliente
	public void cadastrarCliente(ActionEvent event) {
		//Informações do cliente
		String nome = textoNome.getText().trim();
		LocalDate data_nascimento = textoDatePicker.getValue();
		String email = textoEmail.getText().trim();
		//Telefones do cliente
		int idTelefone = -1;
		String telefone1 = textoTelefone1.getText().trim();
		String telefone2 = textoTelefone2.getText().trim();
		//Endereço do cliente
		int idEndereco = -1;
		String rua = textoRua.getText().trim();
		String numero = textoNumero.getText().trim();
		String bairro = textoBairro.getText().trim();
		String cidade = textoCidade.getText().trim();
		String uf = textoUF.getText().trim();
		
		//Checa se tem campo vazios
		if(nome.isEmpty() || email.isEmpty() || data_nascimento == null || telefone1.isEmpty()) {
			AlertaUtil.exibir(AlertType.INFORMATION, "Campo vazio!", "Preencha todos os campos obrigatórios!.");
		}else {
			//Cria novo cliente
			Cliente novoCliente = new Cliente(nome, data_nascimento, email);
			
			//Cria novo telefone e endereço, e seu DAOs
			Telefone telefone = new Telefone(telefone1, telefone2);			
			Endereco endereco = new Endereco(rua,numero,bairro,cidade,uf);
			TelefoneDAO daoTel = new TelefoneDAO();
			EnderecoDAO daoEnd = new EnderecoDAO();
			//Cadastra endereço e telefone no banco dados
			idTelefone = daoTel.cadastrarTelefone(telefone);
			idEndereco = daoEnd.cadastrarEndereco(endereco);
			
			//Atualiza o objeto novoCliente com telefone e endereço
			novoCliente.setIdTelefone(idTelefone);	
			novoCliente.setIdEndereco(idEndereco);
			
			ClienteDAO daoCliente = new ClienteDAO();
			//Cadastra cliente no banco
			daoCliente.cadastrarCliente(novoCliente);
			
			//Limpar os campos
			textoNome.setText("");
			textoDatePicker.setValue(null);
			textoEmail.setText("");
			textoTelefone1.setText("");
			textoTelefone2.setText("");
			textoRua.setText("");
			textoNumero.setText("");
			textoBairro.setText("");
			textoCidade.setText("");
			textoUF.setText("");
		}
		
	}

	
}
