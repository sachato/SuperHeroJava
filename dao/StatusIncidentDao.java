package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Incident;
import model.Prefecture;
import model.StatusIncident;
import model.SuperHero;
import model.Ville;

public class StatusIncidentDao extends Dao {
	
	public void add (StatusIncident incident) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "insert into StatusIncident (IdSuperhero, IdVille, IdIncident, Status, DateTimeOuverture, DateTimeResolution) values (?,?,?,?,?,?)";
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			if(incident.getIdSuperHero() == 0) {
				statement.setString(1, null);
			}
			else {
				statement.setInt(1, incident.getIdSuperHero());
			}
			statement.setInt(2, incident.getIdVille());
			statement.setInt(3, incident.getIdIncident());
			statement.setString(4, incident.getStatus());
			statement.setString(5, incident.getDateTimeOuverture());
			statement.setString(6, incident.getDateTimeResolution());
			System.out.println(sql);
			System.out.println(sql);
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public void update (StatusIncident incident, SuperHero superhero, String status) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update StatusIncident set IdSuperHero=?, Status='"+status+"' where id ="+incident.getId();
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setInt(1, superhero.getId());
			System.out.println(sql);
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public void update (StatusIncident incident, String status, String date) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update StatusIncident set DateTimeResolution=?, Status='"+status+"' where id ="+incident.getId();
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setString(1, date);
			System.out.println(sql);
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public List<StatusIncident> findByIdVilleEtStatus(String status , int idVille) {
		List<StatusIncident> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from StatusIncident where Status = '" + status +"' AND idVille = "+ idVille +";");
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
	
	public List<StatusIncident> findByIdSuperHeroEtStatus(String status , int idSuperHero) {
		List<StatusIncident> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from StatusIncident where Status = '" + status +"' AND IdSuperHero = "+ idSuperHero +";");
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
	
	public List<StatusIncident> findByIdsuperheroEtStatus(String status , int idSuperHero) {
		List<StatusIncident> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from StatusIncident where Status = '" + status +"' AND idSuperhero = "+ idSuperHero +";");
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
	
	
	private StatusIncident buildPrefectureFromRow(ResultSet result) throws SQLException {

		return new StatusIncident(result.getInt(1), result.getInt(2), result.getInt(3), result.getInt(4), result.getString(5), result.getString(6), result.getString(7));
	}
}
