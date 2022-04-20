package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Incident;
import model.Prefecture;
import model.Ville;


public class PrefectureDao extends Dao {
	
	public List<Prefecture> findAll() {
		List<Prefecture> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Prefecture order by Nom asc");
			while (result.next()) {
				all.add(buildPrefectureFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	public Prefecture findByName(String Nom) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Prefecture where Nom = '" + Nom +"'");
			if (result.next()) {
				return buildPrefectureFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	private Prefecture buildPrefectureFromRow(ResultSet result) throws SQLException {

		return new Prefecture(result.getInt(1), result.getString(2));
	}
}
