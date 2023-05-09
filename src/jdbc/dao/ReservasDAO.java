package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.model.Huespedes;
import jdbc.model.Reservas;

public class ReservasDAO {
	
	private final Connection con;
	
	public ReservasDAO(Connection con) {
		this.con = con;
	}
	
	public void createReseva(Reservas reserva) {
		try {
			String sql = "INSERT INTO reservas (entry_date, out_date, valor, payment_method) VALUES (?, ?, ?, ?)";
			
			try (PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setDate(1, reserva.getEntry_date());
				pstm.setDate(2, reserva.getOut_date());
				pstm.setString(3, reserva.getValor());
				pstm.setString(4, reserva.getPayment_method());

				pstm.executeUpdate();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						reserva.setId(rst.getInt(1));
					}
				}
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	public List<Reservas> buscar(){
		List<Reservas> resultados = new ArrayList<>();		
		
		try {
			String sql = "SELECT id,entry_date,out_date,valor,payment_method FROM reservas";
			try(PreparedStatement statement = con.prepareStatement(sql)){
				statement.execute();		
				
				// Encapsulamos y lo implmentamos en un metodo por separado
				transformarResultSetEnReserva(resultados,statement);
			}
			return resultados;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Busqueda por id
	public List<Reservas> buscar(Integer id){
		List<Reservas> reserva = new ArrayList<Reservas>();
		try {
			String sql = "SELECT id,entry_date,out_date,valor,payment_method FROM reservas WHERE id = ? ";
			try(PreparedStatement statement = con.prepareStatement(sql)){
				statement.setInt(1, id);
				statement.execute();
				
				transformarResultSetEnReserva(reserva, statement);				
			}
			return reserva;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void modificar(Date entry_date, Date out_date, String valor, String payment_method, Integer id) {
		try (PreparedStatement  statement = con.prepareStatement("UPDATE reservas SET "
					+ " entry_date = ?, "
					+ " out_date = ?, "
					+ " valor = ?, "
					+ " payment_method = ?"
					+ " WHERE id = ? ")){
			statement.setDate(1, entry_date);
			statement.setDate(2, out_date);
			statement.setString(3, valor);
			statement.setString(4, payment_method);
			statement.setInt(5, id);				
			statement.execute();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}		
	}
	
	public void eliminar(Integer id) {
		try {
			// Creamos la query para elminar emiante id
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE id=?");
			try(statement){
				statement.setInt(1, id);
				statement.execute();			
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	private void transformarResultSetEnReserva(List<Reservas> resultados, PreparedStatement statement) {
		try (ResultSet rst = statement.getResultSet()){
			while(rst.next()) {
				Reservas reserva = new Reservas(rst.getInt(1), rst.getDate(2), rst.getDate(3), rst.getString(4), rst.getString(5));
				resultados.add(reserva);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
