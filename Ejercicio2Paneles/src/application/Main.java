package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		try {
			// EJERCICIO 2.1
			StackPane panel = new StackPane();

			Rectangle r1 = new Rectangle(400, 400, Color.DARKGREEN);
			Rectangle r2 = new Rectangle(300, 300, Color.GREEN);
			Rectangle r3 = new Rectangle(200, 200, Color.LIGHTGREEN);

			panel.getChildren().addAll(r1, r2, r3);

			Scene scene = new Scene(panel, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
			// EJERCICIO 2.2
			BorderPane panel2 = new BorderPane();

			Rectangle centro = new Rectangle(300, 300, Color.BLUE);
			Rectangle arriba = new Rectangle(400, 50, Color.DARKGREEN);
			Rectangle abajo = new Rectangle(400, 50, Color.DARKGREEN);
			Rectangle izquierda = new Rectangle(50, 300, Color.LIGHTGREEN);
			Rectangle derecha = new Rectangle(50, 300, Color.LIGHTBLUE);

			
			panel2.setTop(arriba);
			panel2.setBottom(abajo);
			panel2.setCenter(centro);
			panel2.setRight(derecha);
			panel2.setLeft(izquierda);

			Scene scene2 = new Scene(panel2, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene2);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
