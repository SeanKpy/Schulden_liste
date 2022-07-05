package schulden.list;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Schuldenliste {
	
	public static void allSchulden(Stage hauptfenster) {
		
		VBox vbox = new VBox();
		vbox.setPadding(new Insets(10,80,10,10));
		
		
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_LEFT);
		//grid.setGridLinesVisible(true); //Damit man das NETZ sieht und so einfacher anorden kann.
		grid.setHgap(20);
		grid.setVgap(20);
		grid.setPadding(new Insets(10,10,10,10));
		
		grid.add(vbox,0,1,15,20);
		
		
		int anzahl = Datei.zaehlen();
		Person[] personen = new Person[anzahl];
		personen = Datei.einlesen(anzahl);
		String ausgabe = "";
		int totaleSchuld = 0;
		
		for(Person elemente : personen) { 	//damit die Objekte von dem Array personen ausgelesen werden muss eine neues Objekte erzeugt werden, das wird angebenen als Person (für das Objekt) dann die variable die das ausgegeben objekt aufnimmt.
			ausgabe += elemente.getNachname()+"\n";
			ausgabe += elemente.getVorname()+"\n";
			ausgabe += elemente.getSchuld()+" CHF"+"\n";
			totaleSchuld += elemente.getSchuld(); //Die Schulden der Objekte werden nochmal abgespeichert aber alle zusammengezählt und am Schulss ausgegeben im Label
			ausgabe += elemente.getDatum()+"\n"+"\n";		
		}
		
		Text auflistung = new Text(ausgabe);
		
		Label totalSchuld = new Label("Alle Schulden zu Ihren Gunsten sind: "+totaleSchuld);
		totalSchuld.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
		grid.add(totalSchuld, 1, 0);
		
		Button btn = new Button("OK");
		btn.setMaxWidth(70);
		grid.add(btn, 1, 1);
		
		vbox.getChildren().add(auflistung);//
		
		
		ScrollPane scrollPane = new ScrollPane(vbox); //vbox
		scrollPane.setFitToHeight(true);
		scrollPane.setPadding(new Insets(10,10,10,10));
		grid.getChildren().add(scrollPane);

		
		Stage allSchuld = new Stage();
		allSchuld.initModality(Modality.WINDOW_MODAL);
		allSchuld.initOwner(hauptfenster);
		
		allSchuld.setX(hauptfenster.getX()+200);
		allSchuld.setY(hauptfenster.getY()+100);
		
		Scene scene = new Scene(grid,500,400);
		allSchuld.setScene(scene);
		
		
		//Aktion für den OK Button
		btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						allSchuld.close();
						
					}
				});
		
		allSchuld.show();
	}

}
