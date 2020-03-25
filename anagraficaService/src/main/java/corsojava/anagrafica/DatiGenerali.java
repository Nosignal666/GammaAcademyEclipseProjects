package corsojava.anagrafica;


import corsojava.storage.IllegaIndexException;

public class DatiGenerali extends CredenzialiUtente {
	private String mail;
	private String indirizzo;
	private String citta;
	
	public DatiGenerali(){
		super();
	}
	public DatiGenerali(String username, String password, String mail, String indirizzo, String citta) {
		super(username, password);
		this.mail = mail;
		this.indirizzo = indirizzo;
		this.citta = citta;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	@Override
	public String getIndex() throws IllegaIndexException {
		throw new IllegaIndexException();
	}
	

}
