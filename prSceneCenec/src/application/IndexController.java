package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IndexController {

	
	@FXML
	private TextField txtNombre;
	
	@FXML
	private PasswordField pswContrasena;
	
	@FXML
	private Button btnEntrar;
	
	@FXML
	private Label lblMensaje;
	
	@FXML
	private void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText("Bienvenido " + txtNombre.getText());
		txtNombre.clear();
		pswContrasena.clear();
	}
	
}
