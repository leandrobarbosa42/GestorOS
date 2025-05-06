package application;


import controller.telaInicialController;
import controller.telaLoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//Classe para controle de navegação de telas da aplicação
public class AppNavigator {
	private static Stage mainStage;

	
	public static void setMainStage(Stage stage) {
        mainStage = stage;
    }
	
    public static Stage getMainStage() {
        return mainStage;
    }
	//Metodo para carregar telas
	public static void carregarTela(Scene scene, String title, String icone) {
		try {
				
			mainStage.setScene(scene);
			mainStage.setResizable(false); 
			mainStage.centerOnScreen();
			mainStage.setTitle(title);
			mainStage.getIcons().add(new Image(AppNavigator.class.getResourceAsStream(icone)));
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	//Abrir a tela de login
	public static void mostrarTelaLogin() {
		try {
			FXMLLoader loader = new FXMLLoader(AppNavigator.class.getResource("/view/telaLogin.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			
			carregarTela(scene,"GOS - Login","/icones/GOS22.png");
			
			telaLoginController controller = loader.getController();
			controller.ConexaoBD();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//Abrir a tela inicial
	public static void mostrarTelaInicial() {
		try {
			FXMLLoader loader = new FXMLLoader(AppNavigator.class.getResource("/view/telaInicial.fxml"));
			VBox root = loader.load();
			Scene scene = new Scene(root);
			
			telaInicialController controller = loader.getController();
			

			controller.permisaoAdmin();			
			carregarTela(scene,"GOS - Tela Inicial","/icones/GOS22.png");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
