package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SampleController {
	
	@FXML
	private TextField txtTitulo;
	
	@FXML
	private ChoiceBox cdEditorial;
	
	@FXML
	private TextField txtAutor;
	
	@FXML
	private TextField txtPaginas;
	
	public ObservableList<String> listaEditoriales =
			FXCollections.observableArrayList(
					"Planeta",
					"Altaya",
					"Kadokawa",
					"Penguin Libros"
					);
	@FXML
	private void initialize() {
		cdEditorial.setItems(listaEditoriales);
	}
}
