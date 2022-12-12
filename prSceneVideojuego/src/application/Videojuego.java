package application;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Videojuego {
	
	private SimpleStringProperty nombre;
	private SimpleIntegerProperty precio;
	private SimpleStringProperty consola;
	private SimpleStringProperty pegi;
	
	public Videojuego(String nombre, Integer precio, String consola,
			String pegi) {
		super();
		this.nombre = new SimpleStringProperty(nombre);
		this.precio = new SimpleIntegerProperty(precio);
		this.consola = new SimpleStringProperty(consola);
		this.pegi = new SimpleStringProperty(pegi);
	}
	
	public String getNombre() {
		return nombre.get();
	}
	
	public void setNombre(String nombre) {
		this.nombre = new SimpleStringProperty(nombre);
	}
	
	public Integer getPrecio() {
		return precio.get();
	}
	
	public void setPrecio(int precio) {
		this.precio = new SimpleIntegerProperty(precio);
	}
	
	public String getConsola() {
		return consola.get();
	}
	
	public void setConsola(String consola) {
		this.consola = new SimpleStringProperty(consola);
	}
	
	public String getPegi() {
		return pegi.get();
	}
	
	public void setPegi(String pegi) {
		this.pegi = new SimpleStringProperty(pegi);
	}
	
}
