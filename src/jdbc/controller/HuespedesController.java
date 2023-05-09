package jdbc.controller;

import java.sql.Date;
import java.util.List;

import jdbc.dao.HuespedesDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Huespedes;

public class HuespedesController {
	private HuespedesDAO huespedesDAO;
	
	public HuespedesController() {
		var factory = new  ConnectionFactory();
		this.huespedesDAO = new HuespedesDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public void createHuesped(Huespedes huespedes) {
		huespedesDAO.createHuesped(huespedes);
	}
	
	public List<Huespedes> listarHuesped() {
		return this.huespedesDAO.listarHuesped();
	}
	
	public List<Huespedes> buscar(Integer id){
		return this.huespedesDAO.buscar(id);
	}
	
	public void modificar(String nombre, String last_name,Date birth_date, String nationality, String phone, Integer reservas_id, Integer id) {
		this.huespedesDAO.modificar(nombre, last_name, birth_date, nationality, phone, reservas_id, id);
	}
	
	public void eliminar(Integer id) {
		this.huespedesDAO.eliminar(id);
	}
	
}
