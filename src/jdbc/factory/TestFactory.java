package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;
public class TestFactory {
	public static void main(String[] args) throws SQLException {
		Connection con = new ConnectionFactory().recuperaConexion();
    	System.out.println("Cerrando conexcion");
    	con.close();
	}
}
