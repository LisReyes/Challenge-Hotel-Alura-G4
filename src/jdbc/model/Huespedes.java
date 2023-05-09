package jdbc.model;

import java.sql.Date;

public class Huespedes {
	private Integer id;
	private String nombre;
	private String last_name;
	private Date birth_date;
	private String nationality;
	private String phone;
	private Integer reservas_id;
	
	public Huespedes(String nombre, String last_name,Date birth_date, String nationality, String phone, Integer reservas_id) {
		this.nombre = nombre;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.nationality = nationality;
		this.phone = phone;
		this.reservas_id = reservas_id;
	}
	
	
	public Huespedes(Integer id, String nombre, String last_name, Date birth_date, String nationality, String phone,
			Integer reservas_id) {	
		this.id = id;
		this.nombre = nombre;
		this.last_name = last_name;
		this.birth_date = birth_date;
		this.nationality = nationality;
		this.phone = phone;
		this.reservas_id = reservas_id;
	}
		

	public void setId(Integer id) {
		this.id = id;
	}

	public void setReservas_id(Integer reservas_id) {
		this.reservas_id = reservas_id;
	}



	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getLast_name() {
		return last_name;
	}


	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getBirth_date() {
		return birth_date;
	}


	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}


	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Integer getId() {
		return id;
	}


	public Integer getReservas_id() {
		return reservas_id;
	}


	/*@Override
	public String toString() {
		return String.format("id: %d, nombre: %s, last_name: %s, birth_date: %s, nationality: %s, phone: %d, reservas_id: %d",
				this.id,
				this.nombre,
				this.last_name,
				this.birth_date,
				this.nationality,
				this.phone,
				this.reservas_id);				
	}*/	
}
