package jdbc.model;

import java.sql.Date;

public class Reservas {
	private Integer id;
	private Date entry_date;
	private Date out_date;
	private String valor;
	private String payment_method;
	
	
	public Reservas(Date entry_date, Date out_date, String valor, String payment_method) {
		this.entry_date = entry_date;
		this.out_date = out_date;
		this.valor = valor;
		this.payment_method = payment_method;
	}

		
	public Reservas(Integer id, Date entry_date, Date out_date, String value, String payment_method) {	
		this.id = id;
		this.entry_date = entry_date;
		this.out_date = out_date;
		this.valor = value;
		this.payment_method = payment_method;
	}

	public Date getEntry_date() {
		return entry_date;
	}


	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}


	public Date getOut_date() {
		return out_date;
	}


	public void setOut_date(Date out_date) {
		this.out_date = out_date;
	}


	public String getValor() {
		return valor;
	}


	public void setValor(String value) {
		this.valor = value;
	}


	public String getPayment_method() {
		return payment_method;
	}


	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	/*@Override
	public String toString() {
		return String.format("Reservas [id: %s, entry_date: %s, out_date: %s, value: %s, payment_method: %s",
				this.id,
				this.entry_date,
				this.out_date,
				this.payment_method);
	}*/


	
	
	
}
