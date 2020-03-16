package corsojava.anagrafica;

import corsojava.storage.IllegaIndexException;

public class DatiPersonali extends DatiGenerali {
	private String nome;
	private String cognome;
	private String cod_fiscale;
	public DatiPersonali(){
		super();
	}
	public DatiPersonali(String username, String password, String mail, String indirizzo, String citta, String nome,
			String cognome, String cod_fiscale) {
		super(username, password, mail, indirizzo, citta);
		this.nome = nome;
		this.cognome = cognome;
		this.cod_fiscale = cod_fiscale;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getCod_fiscale() {
		return cod_fiscale;
	}
	public void setCod_fiscale(String cod_fiscale) {
		this.cod_fiscale = cod_fiscale;
	}
	@Override
	public String getIndex() throws IllegaIndexException {
		throw new IllegaIndexException();
	}
}
