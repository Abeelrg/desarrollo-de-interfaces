package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SampleController {

	
	@FXML
	private TextField txtMensaje;
	
	@FXML
	private Button btnEnviar;
	
	@FXML
	private Label lblMensaje;
	
	@FXML
	private void mostrarMensaje(ActionEvent event) {
		lblMensaje.setText(txtMensaje.getText());
	}
	
}
