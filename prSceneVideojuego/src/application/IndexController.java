package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
	
	public ObservableList<String> listaPegi = FXCollections.observableArrayList("3","5","12","16","18");

	private ObservableList<Videojuego> listaVideojuegos =
			FXCollections.observableArrayList();
			
	@FXML
	private void initialize() {

		cbConsola.setItems(listaConsolas);
		cbPEGI.setItems(listaPegi);

		columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		columnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		columnConsola.setCellValueFactory(new PropertyValueFactory<>("consola"));
		columnPEGI.setCellValueFactory(new PropertyValueFactory<>("pegi"));

		ObservableList listaVideojuegosBD = getVideojuegosBD();
		tableVideojuegos.setItems(listaVideojuegosBD); 
	}
	@FXML
	private ObservableList<Videojuego> getVideojuegosBD () {
	
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los videojuegos obtenidos de la BD
		 */
		ObservableList<Videojuego> listaVideojuegosBD = 
				FXCollections.observableArrayList();
		
		//	Nos conectamos a la BD
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from videojuegos";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Videojuego videojuego = new Videojuego(
						rs.getString("nombre"),
						rs.getInt("precio"),
						rs.getString("consola"),
						rs.getString("pegi") 
				);
				listaVideojuegosBD.add(videojuego);
			}
			
			//	Cerramos la conexión
			connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaVideojuegosBD;
	}
	@FXML
	public void anadirVideojuego(ActionEvent event) {
		if(txtNombre.getText().isEmpty() || txtPrecio.getText().isEmpty() || cbConsola.getValue().toString().isEmpty() || cbPEGI.getValue().toString().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error no se ha insertado un campo");
			alerta.setHeaderText("No se ha introducido un campo");
			alerta.setContentText("Porfavor,no dejes ningun campo en blanco");
			alerta.showAndWait();
		}else {
				Videojuego v = new Videojuego (
						txtNombre.getText(),
						Integer.parseInt(txtPrecio.getText()),
						cbConsola.getValue().toString(),
						cbPEGI.getValue().toString()
				);
				
				listaVideojuegos.add(v);
				
				txtNombre.clear();
				txtPrecio.clear();
				cbConsola.getSelectionModel().clearSelection();
				cbPEGI.getSelectionModel().clearSelection();
				
				//Nos conectamos a la BD
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				try {
					//Aqui insertamos en la BD
					String query = "insert into videojuegos " +"(nombre,precio,consola, pegi) " + "VALUES ( ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, v.getNombre());
					ps.setInt(2, v.getPrecio());
					ps.setString(3, v.getConsola());
					ps.setString(4, v.getPegi());
					ps.executeUpdate();
					
					//Cerramos la sesion
					connection.close();
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ObservableList listaVideojuegosBD = getVideojuegosBD();
				tableVideojuegos.setItems(listaVideojuegosBD);
			
		}
			
	}
	
	@FXML
	public void borrarVideojuego(ActionEvent event) { 
		int indiceSeleccionado = tableVideojuegos.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar:" + indiceSeleccionado);
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("No has seleccionado ninguna tabla");
			alerta.setHeaderText("Ninguna tabla seleccionada");
			alerta.setContentText("Porfavor,selecciona una tabla para borrarla");
			alerta.showAndWait();
		}else {
			tableVideojuegos.getItems().remove(indiceSeleccionado);
			tableVideojuegos.getSelectionModel().clearSelection();  
		}
	}
	
	
}
