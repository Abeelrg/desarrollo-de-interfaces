package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {

	@FXML
	private TextField txtNombre;

	@FXML
	private TextField txtPrecio;

	@FXML
	private ChoiceBox cbConsola;

	@FXML
	private ChoiceBox cbPEGI;

	@FXML
	private TableView<Videojuego> tableVideojuegos;

	@FXML
	private TableColumn<Videojuego, String> columnNombre;

	@FXML
	private TableColumn<Videojuego, Float> columnPrecio;

	@FXML
	private TableColumn<Videojuego, String> columnConsola;

	@FXML
	private TableColumn<Videojuego, String> columnPEGI;

	@FXML
	private Button btnAnadir;


	public ObservableList<String> listaConsolas = FXCollections.observableArrayList("Playstation 5", "Playstation 4",
			"Xbox 360", "Xbox X", "PSP Vita", "Nintendo 3DS", "Nintendo Switch");
	
	public ObservableList<Integer> listaPegi = FXCollections.observableArrayList(3,5,12,16,18);

	private ObservableList<Videojuego> listaVideojuegos =
			FXCollections.observableArrayList(
					new Videojuego("Fifa23", (float) 65.78, "Playstation 5", "5")
			);
	@FXML
	private void initialize() {

		cbConsola.setItems(listaConsolas);
		cbPEGI.setItems(listaPegi);

		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columnPEGI.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		tableVideojuegos.setItems(listaVideojuegos); 
	}
	
	@FXML
	public void anadirVideojuego(ActionEvent event) {

			Videojuego v = new Videojuego (
					txtNombre.getText(),
					Float.parseFloat(txtPrecio.getText()),
					cbConsola.getValue().toString(),
					cbPEGI.getValue().toString()
			);
			
			listaVideojuegos.add(v);
			
			txtNombre.clear();
			txtPrecio.clear();
			cbConsola.getSelectionModel().clearSelection();
			cbPEGI.getSelectionModel().clearSelection();
			
	}
	
	@FXML
	public void borrarVideojuego(ActionEvent event) { 
		System.out.println("Borrando un videojuego");
	}
	
	public boolean esNumero (String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
}
