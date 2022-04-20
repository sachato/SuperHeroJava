package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Incident;
import model.SuperHero;
import model.Ville;

public class IncidentDao extends Dao{

	public List<Incident> findAll() {
		List<Incident> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Incident");
			while (result.next()) {
				all.add(buildIncidentFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	public Incident findByName(String Nom) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Incident where Incident = '" + Nom +"'");
			if (result.next()) {
				return buildIncidentFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	public Incident findById(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Incident where Id = " + id +"");
			if (result.next()) {
				return buildIncidentFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	private Incident buildIncidentFromRow(ResultSet result) throws SQLException {

		return new Incident(result.getInt(1), result.getString(2));
	}
}
