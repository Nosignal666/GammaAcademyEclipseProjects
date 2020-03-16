package strutturaDati;
public class Entry {
	private String id;
	private String dataRegistrazione;
	private String importoDare;
	private String importoAvere;
	public Entry(String dataRegistrazione, String importoDare, String importoAvere) {
		super();
		this.dataRegistrazione = dataRegistrazione;
		this.importoDare = importoDare;
		this.importoAvere = importoAvere;
	}
	public Entry() {
		super();
	}
	
}
