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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class IndexController {

	@FXML
	private TextField txtTitulo;

	@FXML
	private ChoiceBox cbEditorial;

	@FXML
	private TextField txtAutor;

	@FXML
	private TextField txtPaginas;

	@FXML
	private TableView<Libro> tableLibros;

	@FXML
	private TableColumn<Libro, String> columnTitulo;

	@FXML
	private TableColumn<Libro, String> columnEditorial;

	@FXML
	private TableColumn<Libro, String> columnAutor;

	@FXML
	private TableColumn<Libro, Integer> columnPaginas;

	@FXML
	private Button btnAnadir;

	private ObservableList<Libro> listaLibros = FXCollections
			.observableArrayList();

	public ObservableList<String> listaEditoriales = FXCollections.observableArrayList("Planeta", "Altaya", "Kadokawa",
			"Penguin Libros");

	@FXML
	private void initialize() {

		cbEditorial.setItems(listaEditoriales);

		columnTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
		columnEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
		columnAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
		columnPaginas.setCellValueFactory(new PropertyValueFactory<>("paginas"));

		ObservableList listaLibrosBD = getLibrosBD();
		tableLibros.setItems(listaLibrosBD);
	}
	@FXML
	private ObservableList<Libro> getLibrosBD () {
	
		/*
		 * Creamos la ObservableList donde almacenaremos
		 * los libros obtenidos de la BD
		 */
		ObservableList<Libro> listaLibrosBD = 
				FXCollections.observableArrayList();
		
		//	Nos conectamos a la BD
		DatabaseConnection dbConnection = new DatabaseConnection();
		Connection connection = dbConnection.getConnection();
		
		String query = "select * from libros";
		try {
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Libro libro = new Libro(
						rs.getString("titulo"),
						rs.getString("editorial"),
						rs.getString("autor"),
						rs.getInt("paginas") 
				);
				listaLibrosBD.add(libro);
			}
			
			//	Cerramos la conexión
			connection.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return listaLibrosBD;
	}
	@FXML
	public void anadirLibro(ActionEvent event) {

		if (txtTitulo.getText().isEmpty() || txtAutor.getText().isEmpty() || cbEditorial.getValue().toString().isEmpty()
				|| txtPaginas.getText().isEmpty()) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error no se ha insertado un campo");
			alerta.setHeaderText("No se ha introducido un campo");
			alerta.setContentText("Porfavor,no dejes ningun campo en blanco");
			alerta.showAndWait();
		} else {
			if (esNumero(txtPaginas.getText())) {
				Libro l = new Libro(txtTitulo.getText(), cbEditorial.getValue().toString(), txtAutor.getText(),
						Integer.parseInt(txtPaginas.getText()));

				//listaLibros.add(l);
				//tableLibros.setItems(listaLibros);
				
				txtTitulo.clear();
				cbEditorial.getSelectionModel().clearSelection();
				txtAutor.clear();
				txtPaginas.clear();
				
				//Nos conectamos a la BD
				DatabaseConnection dbConnection = new DatabaseConnection();
				Connection connection = dbConnection.getConnection();
				
				try {
					//Aqui insertamos en la BD
					
					String query = "insert into libros (titulo, editorial, autor, paginas) values (?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(query);
					ps.setString(1, l.getTitulo());
					ps.setString(2, l.getEditorial());
					ps.setString(3, l.getAutor());
					ps.setInt(4, l.getPaginas());
					ps.executeUpdate();
					
					//Cerramos la sesion
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				ObservableList listaLibrosBD = getLibrosBD();
				tableLibros.setItems(listaLibrosBD);
			} else {
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error al insertar");
				alerta.setHeaderText("No se ha introducido un numero en las páginas");
				alerta.setContentText("Porfavor,introduzca un numero en las páginas");
				alerta.showAndWait();


			}
		}

	}

	@FXML
	public void borrarLibro(ActionEvent event) {

		int indiceSeleccionado = tableLibros.getSelectionModel().getSelectedIndex();
		
		System.out.println("Indice a borrar:" + indiceSeleccionado);
		if (indiceSeleccionado <= -1) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("No has seleccionado ninguna tabla");
			alerta.setHeaderText("Ninguna tabla seleccionada");
			alerta.setContentText("Porfavor,selecciona una tabla para borrarla");
			alerta.showAndWait();
		}else {
			tableLibros.getItems().remove(indiceSeleccionado);
			tableLibros.getSelectionModel().clearSelection();  
		}
		

		
	}

	public boolean esNumero(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
