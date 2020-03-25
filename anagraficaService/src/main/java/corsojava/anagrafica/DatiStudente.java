package corsojava.anagrafica;



import corsojava.storage.IllegaIndexException;

public class DatiStudente extends DatiPersonali {
	private String matricola;
	
	public DatiStudente(){
		super();
	};
	public DatiStudente(String username, String password, String mail, String indirizzo, String citta, String nome,
			String cognome, String cod_fiscale, String matricola) {
		super(username, password, mail, indirizzo, citta, nome, cognome, cod_fiscale);
		this.matricola = matricola;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	@Override
	public String getIndex() throws IllegaIndexException {
		return this.getClass().getSimpleName()+"-"+this.getNome()+"-"+this.getCognome()+"-"+matricola;
	}
	

}
