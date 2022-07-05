package schulden.list;

import java.io.*;

public class Datei {
	
	
	//Funktion zum Zählen von dem Inhalt der Textdatei Schuldenliste.
	public static int zaehlen() {
		int zaehler = 0;
		String linie;
		try {
			File datei = new File("Schuldenliste.txt");//Wenn die Datei noch nicht existiert wird als anzahlt datein 0 zurück gegeben
			if(!datei.exists()) {
				return 0;
			}
			FileInputStream fis = new FileInputStream("Schuldenliste.txt");//Wenn die Datei existieren sollte wird hier die Datei in fis gespeichert
			InputStreamReader isr = new InputStreamReader(fis);//der isr liest die (fis) datei
			BufferedReader br = new BufferedReader(isr);// Der BuffereaderReader enhält die funktion readLine() der gibt jede zeile einzeln aus von (isr)
			
			
			linie = br.readLine();
			
			while(linie != null) {//solange linie ungleich 0 ist geht sie weiter
				if("".equals(linie)) { //hier wird überprüft ob das Objekt String ("" etspricht einer leeren zeile) gleich ist wie das Objekt linie (geht nicht mit == da es eben Objekte sind vom Typ String
					zaehler ++;// wenn sie nicht gleich sind wird der zähler um 1 erhöht.
				}
				linie = br.readLine(); // Dann eine neue Zeile eingelesen und die Schleife fängt von vorne an.
			}
			br.close();
		}
		catch (IOException ioAusnahme) {
			System.out.println("Datei konnte nicht gefunden werden.");
		}
		
		return zaehler;
		
	}
	// Hier wird die Textdatei komplett in ein Array eingelesen und returnt (und wenn die Textdatei nicht besteht auch gleich erstellt)
	public static Person[] einlesen(int laenge) {
		Person[] a = new Person[laenge];
		try {
			File datei = new File("Schuldenliste.txt");//Text Datei wird geöffnet oder versucht zu öffnen und in der Variable datei gespeichert.
			if(!datei.exists()) { //Danach wird überprüft ob die Textdatei bereits besteht wenn das nicht.
				datei.createNewFile(); //Wenn die datei nicht bestehen sollte wird mit dem Befehl createNewFile() die Textdatei erstellt.
			}
			
			FileInputStream fis = new FileInputStream("Schuldenliste.txt");
			InputStreamReader isr = new InputStreamReader(fis);
			BufferedReader br = new BufferedReader(isr);
			
			String linie;
			
			
			for(int i=0; i<laenge; i++) {
				linie = br.readLine();
				linie = br.readLine();
				String nachName = linie;
				linie = br.readLine();
				String vorName = linie;
				linie = br.readLine();
				int betrag = Integer.parseInt(linie);
				linie = br.readLine();
				String datum = linie;
				a[i] = new Person(nachName, vorName, betrag, datum);//Hier wird zeile für Zeile eingelsen und in den verschieden Variablen abgelegt. 
																	//Danach wird eine neues Objekt vom Typ Person erstellt und im Array an der Position der Höhe von i gespeichert
			}
			br.close();
			}
		catch(IOException ioAusnahme) {
			System.out.println("Datei konnte nicht gefunden werden.");
		}
		return a; //Das Array wird zurück gegeben
		
	}
	//Eine Funktion um die neue Einträge Alphabetisch zu sortieren
	public static Person[] einfuegen(Person[] pArray, Person p, int size) {  //Erhält ein Array vom Typ Person, ein Objekt vom typ Person und eine Zahl.
		Person[] schuldnerList = new Person[size+1];  //neues Array wird erstellt mit der grösse die übergeben wurde plus 1
		
		for(int i=0; i<size;i++) {  // Das alte Array wird komplett eingelsen
			schuldnerList[i] = pArray[i]; 
		}
		schuldnerList[size] = p; //und an der letzten Stelle wird die übergebene Person eingfügt
		
		for(int i=size; i>0;i--) {  //in dieser for Schleife wird die neue person anhand vom nachhame verglichen mit der Person vor ihm im Array
			int vergleich = schuldnerList[i].getNachname().compareToIgnoreCase(schuldnerList[i-1].getNachname());
			
			if(vergleich < 0) { //die Methode compareToIgnoreCase liefert einen negativen wert wenn die zwei namen nicht ¨übereinstimmen
				Person tempP = schuldnerList[i-1]; //in dem Fall tauschen in diesen 3 Schritten die Personen den Platz dazu wird immer eine Person temporär zwischen gespeichert
				schuldnerList[i-1] = schuldnerList[i];
				schuldnerList[i] = tempP;
			}
		}
		return schuldnerList;
		
		
	}
	
	//Eine Funktion um die neue Person zu  speichern
	public static void speichern(Person[] mitNeuePerson) {
		
		try {
			File datei = new File("Schuldenliste.txt");//Text Datei wird geöffnet oder versucht zu öffnen und in der Variable datei gespeichert.
			boolean neu = true;
			if(!datei.exists()) { //Danach wird überprüft ob die Textdatei bereits besteht wenn das nicht.
				datei.createNewFile();
		
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("Schuldenliste.txt", false)); //Das fals am Ende bedeutet das alles was in der datei ist gelöscht wird, weil die methode ein array erhält mit allen neuen Personen drin
																								// würde ein true darin stehen würde es das komplette erhaltene array am schluss der datei einschreiben und dann wäre alles doppelt.
			
			for(Person elements : mitNeuePerson) {
				if(!neu) {
					bw.write("\n");
				}
				bw.write("\n"+elements.getNachname());
				bw.write("\n"+elements.getVorname());
				bw.write("\n"+elements.getSchuld());
				bw.write("\n"+elements.getDatum());
				neu = false;
			}
			bw.close();
		
		}
		catch(IOException ioAusnamen) {
			System.out.println("Fehler beim öffnen der Datei");
		}
			
	}
	
	public static void loeschen(int x, Person[] inhalt) {
		try {
			boolean neu = true;
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("Schuldenliste.txt" , false));
			int zaehler = 1;
			
			for(Person elemente : inhalt) {
				if(zaehler != x) {
					if(!neu) {
						bw.write("\n");
					}
					
					else {
						neu = false;
					}
					bw.write("\n"+elemente.getNachname());
					bw.write("\n"+elemente.getVorname());
					bw.write("\n"+elemente.getSchuld());
					bw.write("\n"+elemente.getDatum());
				}
				
				zaehler++;
			}
			bw.close();
		}
		catch(IOException ioAusnahmen) {
			System.out.println("Datei konnte nicht geöffnet werden");
		}
	}
	

}
