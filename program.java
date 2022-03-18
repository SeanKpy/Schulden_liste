package schulden.list;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class program extends Application{

	public static void main(String[] args) {
		launch();

	}
	
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Schulden-Liste");
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		
		Text titel = new Text("Herzlich Willkommen");
		titel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(titel,0,0,2,1);
		
		Button hinzu = new Button("Hinzufügen");
		grid.add(hinzu, 0, 1);
		
		
		Button remove = new Button("Entfernen");
		grid.add(remove, 0, 3);
		
		
		Button allSchuld = new Button("Schuldenliste");
		grid.add(allSchuld, 0, 5);
		
		
		hinzu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				Hinzu_window.hinzuWindow(stage);;
				
			}
		});
		
		remove.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				Entfernen.delWindow(stage);
				
			}
		});
		
		allSchuld.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Schuldenliste.allSchulden(stage);
				
			}
		});
		
		
		
		
		Scene scene = new Scene(grid, 400, 300);
		stage.setScene(scene);
		stage.show();
	
	
	
	}

}
