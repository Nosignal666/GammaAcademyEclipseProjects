package strutturaDati;

import java.time.Year;
import java.util.ArrayList;
import java.util.Date;

import org.joda.money.Money;

public class Fattura {
	private int numeroProgressivo;
	private Year anno;
	private Date dataEmissione;
	private Date dataScadenza;
	private Money importo;
	private ArrayList<PagamentoParziale> pagamentiParziali;
	private TipoPagamento tipoPagamento;
	private String note;
	public Fattura(int numeroProgressivo, Year anno, Date dataEmissione, Date dataScadenza, Money importo,
			TipoPagamento tipoPagamento, String note) {
		super();
		this.numeroProgressivo = numeroProgressivo;
		this.anno = anno;
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
		this.importo = importo;
		this.tipoPagamento = tipoPagamento;
		this.note = note;
		this.pagamentiParziali=new ArrayList<PagamentoParziale>();
	}
	
	public void aggiungiPagamento(Money importo,Date data){
		
	}
	
	
	
	
	
	
	
}
