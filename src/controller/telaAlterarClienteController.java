package controller;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Cliente;
import model.Endereco;
import model.Telefone;
import model.dao.ClienteDAO;
import model.dao.EnderecoDAO;
import model.dao.TelefoneDAO;
import util.AlertaUtil;
import util.ImageUtil;

public class telaAlterarClienteController implements Initializable{

    @FXML
    private Button botaoAtualizar;

    @FXML
    private Button botaoDeletar;

    @FXML
    private TableView<Cliente> tabelaClientes;
    @FXML
    private TableColumn<Cliente, Integer> colunaID;
    @FXML
    private TableColumn<Cliente, String> colunaNome;
    @FXML
    private TableColumn<Cliente, String> colunaEmail;
    @FXML
    private TableColumn<Cliente, String> colunaTelefone;

    @FXML
    private ImageView imagemCadastro;

    @FXML
    private Label labelCadastro;

    @FXML
    private TextField textoBairro;

    @FXML
    private TextField textoBuscaNome;

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
    private TextField textoID;

    @FXML
    private TextField textoTelefone1;

    @FXML
    private TextField textoTelefone2;

    @FXML
    private TextField textoUF;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Imagens botões
		botaoAtualizar.setGraphic(ImageUtil.configurarImagem("/icones/update.png", 60, 60));
		botaoDeletar.setGraphic(ImageUtil.configurarImagem("/icones/delete.png", 60, 60));
		
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
			    

		//Configurando celulas tabela
		colunaID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colunaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone1")); 
		ClienteDAO dao = new ClienteDAO();
		
		textoBuscaNome.textProperty().addListener((obs, oldVal, newVal) -> {			
		    List<Cliente> lista = dao.buscaNome(newVal);
		    tabelaClientes.setItems(FXCollections.observableArrayList(lista));
		});

	}
	
	private void atualizarTabela() {
		ClienteDAO dao = new ClienteDAO();
	    String filtro = textoBuscaNome.getText();
	    List<Cliente> lista = dao.buscaNome(filtro);
	    tabelaClientes.setItems(FXCollections.observableArrayList(lista));
	}
	
	public void clienteSelecionado() {
		//Pega informções do cliente selecionado da tabela
		Cliente selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
		Endereco endereco = new Endereco();
		EnderecoDAO daoEnd = new EnderecoDAO();
		
		if(selecionado != null) {
			endereco = daoEnd.buscaEndereco(selecionado.getIdEndereco());
			//Seta os campos com as informações endereco
			textoBairro.setText(endereco.getBairro());;
			textoCidade.setText(endereco.getCidade());;
			textoUF.setText(endereco.getUf());;
			textoRua.setText(endereco.getRua());;
			textoNumero.setText(endereco.getNumero());;
			
			//Seta os campos com as informações
			textoID.setText(String.valueOf(selecionado.getId()));
			textoDatePicker.setValue(selecionado.getDataNascimento());;
			textoEmail.setText(selecionado.getEmail());;
			textoNome.setText(selecionado.getNome());;

			textoTelefone1.setText(selecionado.getTelefone1());
			textoTelefone2.setText(selecionado.getTelefone2());
		}	
	}
	
	
	public void setaCamposVazio() {
   		textoBairro.setText("");
   		textoCidade.setText("");
   		textoUF.setText("");
   		textoRua.setText("");
   		textoNumero.setText("");

   		textoDatePicker.setValue(null);
   		textoEmail.setText("");
   		textoNome.setText("");
   		textoID.setText("");

   		textoTelefone1.setText("");
   		textoTelefone2.setText("");
   		
   		textoBuscaNome.setText("");
	}

	public void removerCliente(ActionEvent event) {
		Cliente selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
		ClienteDAO daoCliente = new ClienteDAO();
		TelefoneDAO daoTelefone = new TelefoneDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirmação de exclusão cliente");
	    alert.setHeaderText("Deseja realmente excluir o registro do cliente?");
	    alert.setContentText("Após confirmar não será possível desfazer a operação. Se realmente deseja excluir o usuário, pressione OK.");
			
	    // Mostra o alerta e espera pela resposta do usuário
	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.isPresent() && result.get() == ButtonType.OK) {
	           // Lógica caso o usuário confirme a saída    		        	
	        if(textoID.getText().trim().isEmpty() || textoID.getText().equals("0")) {
	        	AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Nenhum cliente selecionado.");
	        }else {
	           	try {
	           		daoCliente.removerCliente(selecionado.getId());
	           		daoTelefone.deletaTelefone(selecionado.getIdTelefone());
	           		daoEndereco.deletaEndereco(selecionado.getIdEndereco());
	           		
	           		setaCamposVazio();	       
	           		atualizarTabela();
	    		} catch (Exception e) {
	    			AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Erro ao remover cliente: ."+e);
	    		}
	        }        	
	    } else {
	            // Usuário cancelou — nenhuma ação necessária           
	        }
	}

	public void atualizaCliente(ActionEvent event) {
		Cliente selecionado = tabelaClientes.getSelectionModel().getSelectedItem();
		
		Cliente novosDados = new Cliente();
		Telefone telefone = new Telefone();
        Endereco endereco = new Endereco();
		ClienteDAO daoCliente = new ClienteDAO();
		TelefoneDAO daoTelefone = new TelefoneDAO();
		EnderecoDAO daoEndereco = new EnderecoDAO();
		
		
		if(textoID.getText().trim().isEmpty() || textoID.getText().equals("0")) {
        	AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Nenhum cliente selecionado.");
        }else {
        	
        	if(textoNome.getText().trim().isEmpty() || textoEmail.getText().trim().isEmpty() || textoDatePicker == null || textoTelefone1.getText().trim().isEmpty()){
    			AlertaUtil.exibir(AlertType.INFORMATION, "Campo vazio!", "Preencha todos os campos obrigatórios!.");
    		}else {
    			try {
    				//Pegando novas informações
    				novosDados.setId(selecionado.getId()); //ID selecionado, o que irá sofrer as mudanças
    				novosDados.setNome(textoNome.getText());
    				novosDados.setDataNascimento(textoDatePicker.getValue());
    				novosDados.setEmail(textoEmail.getText());
    				
            		telefone.setId(selecionado.getIdTelefone());
            		telefone.setTelefone1(textoTelefone1.getText());
            		telefone.setTelefone2(textoTelefone2.getText());
            		
            		endereco.setId(selecionado.getIdEndereco());
            		endereco.setRua(textoRua.getText());
            		endereco.setNumero(textoNumero.getText());
            		endereco.setBairro(textoBairro.getText());
            		endereco.setCidade(textoCidade.getText());
            		endereco.setUf(textoUF.getText());
            		
            		//Atualizando o banco
               		daoCliente.atualizarCliente(novosDados);
               		daoTelefone.atualizaTelefone(telefone);
               		daoEndereco.atualizaEndereco(endereco);
               		
               		setaCamposVazio();
               		atualizarTabela();
        		} catch (Exception e) {
        			AlertaUtil.exibir(AlertType.INFORMATION, "Atenção!", "Erro ao atualizar cliente: ."+e);
        		}
    		}
        
        }
		
	}
}
