package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			// EJERCICIO 3
			
			Button btn7 = new Button("7");
			Button btn8 = new Button("8");
			Button btn9 = new Button("9");
			
			Button btn4 = new Button("4");
			Button btn5 = new Button("5");
			Button btn6 = new Button("6");
			
			Button btn1 = new Button("1");
			Button btn2 = new Button("2");
			Button btn3 = new Button("3");
			
			Button btn0 = new Button("0");
			Button btnLlamar = new Button("Llamar");
			Button btnColgar = new Button("Colgar");

			GridPane panel = new GridPane();
			panel.add(btn7, 0, 0);
			panel.add(btn8, 1, 0);
			panel.add(btn9, 2, 0);
			panel.add(btn4, 0, 1);
			panel.add(btn5, 1, 1);
			panel.add(btn6, 2, 1);
			panel.add(btnLlamar, 3, 1);
			panel.add(btn1, 0, 2);
			panel.add(btn2, 1, 2);
			panel.add(btn3, 2, 2);
			panel.add(btnColgar, 3, 2);
			panel.add(btn0, 1, 3);

			Scene scene = new Scene(panel, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculadora");

			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
