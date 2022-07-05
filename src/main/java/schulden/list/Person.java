package schulden.list;


public class Person {
	private String nachname;
	private String vorname;
	private int schuld;
	private String datum;
	
	
	
	
	
	public Person(String lastName, String firstName, int value, String datum) {
		this.nachname = lastName;
		this.vorname = firstName;
		this.schuld = value;
		this.datum = datum;
		
	}





	public String getNachname() {
		return nachname;
	}





	public String getVorname() {
		return vorname;
	}





	public int getSchuld() {
		return schuld;
	}





	public String getDatum() {
		return datum;
	}
	

}
