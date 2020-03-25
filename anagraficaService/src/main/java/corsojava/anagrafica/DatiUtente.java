package corsojava.anagrafica;



import corsojava.storage.IllegaIndexException;

public class DatiUtente extends DatiPersonali {
	private String materia;
	
    public DatiUtente(){
    	super();

    }
	public DatiUtente(String username, String password, String mail, String indirizzo, String citta, String nome,
			String cognome, String cod_fiscale, String materia) {
		super(username, password, mail, indirizzo, citta, nome, cognome, cod_fiscale);
		this.materia = materia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	@Override
	public String getIndex() throws IllegaIndexException {
		return this.getClass().getSimpleName()+"-"+this.getNome()+"-"+this.getCognome()+"-"+materia;
	}
	

}
