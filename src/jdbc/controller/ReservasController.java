package jdbc.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import jdbc.dao.ReservasDAO;
import jdbc.factory.ConnectionFactory;
import jdbc.model.Reservas;

public class ReservasController {
	private ReservasDAO reservasDAO;
	
	public ReservasController() {
		Connection connection = new ConnectionFactory().recuperaConexion();
		this.reservasDAO = new ReservasDAO(connection);		
	}
	
	public void createReserva(Reservas reservas) {
		reservasDAO.createReseva(reservas);
	}
	
	public List<Reservas> buscar(){
		return this.reservasDAO.buscar();
	}
	
	public List<Reservas> buscar(Integer id){
		return this.reservasDAO.buscar(id);
	}
	
	public void modificar(Date entry_date, Date out_date, String valor, String payment_method, Integer id) {
		this.reservasDAO.modificar(entry_date, out_date, valor, payment_method, id);
	}
	
	public void eliminar(Integer id) {
		this.reservasDAO.eliminar(id);
	}
}
