package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.factory.ConnectionFactory;
import jdbc.model.Huespedes;
import jdbc.model.Reservas;

public class HuespedesDAO {
	final private Connection con;
	
	public HuespedesDAO(Connection con) {
		this.con = con;
	}
	
	// Logica del negocio (CRUD)
	// Comenzamos con Create
	public void createHuesped(Huespedes huespedes) {
		try {
			PreparedStatement statement;
			statement = con.prepareStatement(
					"INSERT INTO huespedes (nombre, last_name, birth_date, nationality, phone, reservas_id) VALUES (?, ?, ?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
			try(statement){
				statement.setString(1, huespedes.getNombre());
				statement.setString(2,huespedes.getLast_name());
				statement.setDate(3, huespedes.getBirth_date());
				statement.setString(4, huespedes.getNationality());
				statement.setString(5, huespedes.getPhone());
				statement.setInt(6, huespedes.getReservas_id());	
				statement.execute();
				
				final ResultSet resultSet = statement.getGeneratedKeys();
			
				try(resultSet){
					while(resultSet.next()) {
						huespedes.setId(1);						
					}
				}
			}			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Creamos el READ
	public List<Huespedes> listarHuesped() {
		List<Huespedes> huespedes = new ArrayList<Huespedes>();		
		
		try {
			String sql = "SELECT id,nombre, last_name,birth_date, nationality, phone, reservas_id FROM huespedes";
			try(PreparedStatement statement = con.prepareStatement(sql)){			
				statement.execute();
				transformarResultSetEnReserva(huespedes, statement);				
			}		
			return huespedes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Busqueda por id
	public List<Huespedes> buscar(Integer id){
		List<Huespedes> huespedes = new ArrayList<Huespedes>();
		try {
			String sql = "SELECT id,nombre,last_name,birth_date,nationality,phone,reservas_id FROM huespedes WHERE id = ?";
			try(PreparedStatement statement = con.prepareStatement(sql)){
				statement.setInt(1, id);
				statement.execute();
				
				transformarResultSetEnReserva(huespedes, statement);
			}
			return huespedes;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	// Modificacion
	public void modificar(String nombre, String last_name,Date birth_date, String nationality, String phone, Integer reservas_id, Integer id) {
		try(PreparedStatement statement = con.prepareStatement("UPDATE huespedes SET "
				+ " nombre = ?, "
				+ " last_name = ?,"
				+ " birth_date = ?, "
				+ " nationality = ?, "
				+ " phone = ?, "
				+ " reservas_id = ? "
				+ " WHERE id = ?")){
			
			statement.setString(1, nombre);
			statement.setString(2, last_name);
			statement.setDate(3, birth_date);
			statement.setString(4, nationality);
			statement.setString(5, phone);
			statement.setInt(6, reservas_id);
			statement.setInt(7, id);
			
			statement.execute();			
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	// Eliminar
	public void eliminar(Integer id) {
		try {
			// query para eliminar mediante id
			final PreparedStatement statement = con.prepareStatement("DELETE FROM huespedes WHERE id = ? ");
			try(statement){
				statement.setInt(1, id);
				statement.execute();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void transformarResultSetEnReserva(List<Huespedes> resultados, PreparedStatement statement) {
		try (ResultSet rst = statement.getResultSet()){
			while(rst.next()) {
				Huespedes huesped = new Huespedes(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4),rst.getString(5),rst.getString(6),rst.getInt(7));
				resultados.add(huesped);
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
