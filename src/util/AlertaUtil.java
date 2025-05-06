package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
//Cria uma janela de aviso
public class AlertaUtil {
	public static void exibir(AlertType tipo, String titulo, String mensagem) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // Oculta o cabeçalho
        alert.setContentText(mensagem);
        alert.showAndWait();
	}
}
