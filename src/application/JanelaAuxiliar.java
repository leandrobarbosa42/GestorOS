package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
//Abrir janelas secundarias
public class JanelaAuxiliar {
	public static void abrirJanela(String caminhoFXML, String titulo, boolean bloquearPrincipal) {
		try {
			FXMLLoader loader = new FXMLLoader(JanelaAuxiliar.class.getResource(caminhoFXML));
			Parent root = loader.load();
			
			Stage stage = new Stage();
			
			stage.setTitle(titulo);
			stage.setScene(new Scene(root));
			stage.setResizable(bloquearPrincipal);
			stage.centerOnScreen();
			
			stage.initOwner(AppNavigator.getMainStage());
			
			if(bloquearPrincipal) {
				stage.initModality(Modality.WINDOW_MODAL);;
			}
			
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
