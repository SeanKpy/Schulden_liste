package schulden.list;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Hinzu_window {
	
	
	
	//Erh�lt eine Stage(B�hne)
	public static void hinzuWindow(Stage hauptfenster){
		
		Stage hinzu_window = new Stage();
		
		GridPane grid = new GridPane();//Erzeugt ein neues Netz f�r die B�hne
		grid.setAlignment(Pos.CENTER);//Wird �ber der B�hne in der mitte positioniert
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10, 10, 10, 10));
		
		Scene scene = new Scene(grid, 500, 400);//Wird eine neue Leinwand gemacht und muss auf dem direkt Netz platziert werden
		hinzu_window.setScene(scene);//Die Leinwand muss dann direkt in das Hauptfenster gesetzt werden
		
		hinzu_window.initModality(Modality.WINDOW_MODAL);//Dieser Befehlt erstellt eine Abh�ngigkeit zu den ersten Hauptfenster, so dass das Hauptfenster nicht geschlossen werden kann.
		hinzu_window.initOwner(hauptfenster);//Das sagt aus welches Fenseter eine Abh�ngigkeit zum Hauptfenster hat.
		hinzu_window.setX(hinzu_window.getX()+200);//Macht dass das Fenster vom Hauptfenster 200 Pixel im X versetzt wird sonst ist es genau auf dem Hauptfenser, in der n�chsten Spalte wird das gleiche gemacht f�r die Y Pos.
		hinzu_window.setY(hinzu_window.getY()+100);
		
		//^
		//|
		//Zuerst das ganze Fenster erzeugen mit dem Netz.
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Alert alarm = new Alert(AlertType.WARNING);
		alarm.setTitle("Warnung");
		alarm.setHeaderText("Oops etwas wurde vergessen.");
		alarm.setContentText("Alle Felder m�ssen ausgef�hlt sein.");
		
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Information");
		info.setHeaderText("Gespeichert");
		info.setContentText("Die neue Schuldnerperson wurde in Ihre Liste eingef�gt.");
		
		
		hinzu_window.setTitle("Person hinzuf�gen");
	
		
		Label lastName = new Label("Nachname");//Im Fenster der Nachname 
		grid.add(lastName,0,1);//Paratmeter wo die Variable gesetzt werden soll erste 0f�r Spalte die zweite 0 f�r die Zeile
		
		TextField nachName = new TextField();//Eingabefeld f�r Nachname
		grid.add(nachName,1,1);
		
		Label firstName = new Label("Vorname");//Im Fenster der Vorname 
		grid.add(firstName,0,2);
		
		TextField vorName = new TextField();//Eingabefeld f�r Vorname
		grid.add(vorName,1,2);
		
		
		Label datum = new Label("Datum");//Im Fenster das Datum 
		grid.add(datum,0,3);
		
		TextField data = new TextField();//Eingabefeld f�r das Datum
		grid.add(data,1,3);
		
		Label betrag = new Label("Betrag");//Im Fenster der Betrag 
		grid.add(betrag,0,4);
		
		TextField betragMenge = new TextField();//Eingabefeld f�r den Betrag
		grid.add(betragMenge,1,4);
		
		
		Button speichern = new Button("Speichern"); //Button wird erstellt direkt mit Name
		grid.add(speichern, 4, 6);
		
		
		speichern.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				if((nachName.getText() != "") && (vorName.getText() != "") && (data.getText() != "") && (betragMenge.getText() != "")) {
					String nName = nachName.getText();
					String vName = vorName.getText();
					String datau = data.getText();
					int schuldBetrag = Integer.parseInt(betragMenge.getText());
					Person schuldner = new Person(nName, vName, schuldBetrag, datau);
					
					int anzahl = Datei.zaehlen();
					Person[] personenAlt = new Person[anzahl];
					personenAlt = Datei.einlesen(anzahl);
					Person[] personenNeu = new Person[anzahl+1];
					if (anzahl > 0) {
						personenNeu = Datei.einfuegen(personenAlt, schuldner, anzahl);
					}
					else {
						personenNeu[0] = schuldner;
					}
					
					Datei.speichern(personenNeu);
					hinzu_window.close();
					info.showAndWait();
					
					
					
				}
				else
					alarm.showAndWait();
				
			}
		});
		
		
		
		
		hinzu_window.show();//Zum Schluss kommt die show Methode die dann das ganze Fenster anzeigt.
	}

}
