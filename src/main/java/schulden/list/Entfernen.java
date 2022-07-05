package schulden.list;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Entfernen {
	
	public static void delWindow(Stage hauptfenster) {
		//Hauptfenster bauen (Stage = Bühne)
		Stage delWindow = new Stage();
		delWindow.setTitle("Schuld entfernen");
		delWindow.initModality(Modality.WINDOW_MODAL);
		delWindow.initOwner(hauptfenster);
		delWindow.setX(delWindow.getX()+200);
		delWindow.setY(delWindow.getX()+100);
		
		//Eine Box die die Liste enthählt
		VBox vbox = new VBox();
		
		//Titel im Fenster
		Text titel = new Text("Welche Schuld entfernen?");
		titel.setFont(Font.font("Arial",FontWeight.NORMAL,14));
		
				
		//Eine Scrollbarne erzeugen um die Vbox nach oben und unten zu bewegen
		ScrollPane scp = new ScrollPane(vbox);
		scp.setFitToHeight(true);
		scp.setPadding(new Insets(10));
		
		//Das Netz oder Container für alle Komponeten die in dem Fenster erscheinen.
		BorderPane grid = new BorderPane(scp);
		grid.setPadding(new Insets(30));
		grid.setTop(titel);
		
		
		//Eine Scene oder Show erschaffen so das man auch das Fenster sieht
		Scene scene = new Scene(grid, 500,400);
		delWindow.setScene(scene);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////
		
		int anzahl = Datei.zaehlen();
		Person[] personen = new Person[anzahl];
		personen = Datei.einlesen(anzahl);
		Button[] btns = new Button[anzahl];
		String ausgabe;
		Text text;
		
		for(int i=0; i<anzahl;i++) {
			ausgabe = "\n\n"+personen[i].getNachname()+"\n";
			ausgabe += personen[i].getVorname()+"\n";
			ausgabe += personen[i].getSchuld()+"\n";
			ausgabe += personen[i].getDatum()+"\n"+"\n";
			
			text = new Text(ausgabe);
			
			vbox.getChildren().add(text);
			
			btns[i] = new Button("Eintrag "+(i+1)+" löschen.");
			vbox.getChildren().add(btns[i]);
		}
		
		final Person[] liste = personen;
		
		for(int i=0; i<anzahl;i++) {
			
			btns[i].setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent e) {
					String text = ((Button)e.getSource()).getText();
					String text2 = text.replaceAll("[^0-9]", "");
					int pos = Integer.parseInt(text2);
					Datei.loeschen(pos, liste);
					delWindow.close();
					Alert meldung = new Alert(AlertType.INFORMATION);
					meldung.setTitle("Information");
					meldung.setHeaderText("Eintrag gelösch!");
					meldung.setContentText("Der Ausgewählte Eintrag wurde gelöscht!");
					meldung.showAndWait();
					
				}
			});
		}

		
		delWindow.show();
	}

}
