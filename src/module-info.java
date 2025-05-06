module GestorOS {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires transitive javafx.base;
	
	exports controller;
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.fxml;
	opens model to javafx.base;
}
