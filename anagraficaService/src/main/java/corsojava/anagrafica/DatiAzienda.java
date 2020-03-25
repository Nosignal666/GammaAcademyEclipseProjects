package corsojava.anagrafica;



import corsojava.storage.IllegaIndexException;

public class DatiAzienda extends DatiGenerali {

	@Override
	public String getIndex() throws IllegaIndexException {
		return this.getClass().getSimpleName()+"-"+nome+"-"+piva;
	}
	
	private String nome;
	private String piva;
	
	public DatiAzienda(){
		super();

	}
    public DatiAzienda(String username, String password, String mail, String indirizzo, String citta, String nome,
			String piva) {
		super(username, password, mail, indirizzo, citta);
		this.nome = nome;
		this.piva = piva;
		
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPIva() {
		return piva;
	}
	public void setPIva(String piva) {
		this.piva = piva;
	}
	

}
