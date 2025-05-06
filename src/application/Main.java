package application;
	
import java.util.Locale;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	
//Inicia a aplicação
	public void start(Stage primaryStage) {
		try {
			Locale.setDefault(Locale.forLanguageTag("pt-BR"));
	        AppNavigator.setMainStage(primaryStage);
	        AppNavigator.mostrarTelaLogin();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
