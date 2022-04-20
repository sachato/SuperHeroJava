package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Ville;

public class VilleDao extends Dao {

	public List<Ville> findAll() {
		List<Ville> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Ville");
			while (result.next()) {
				all.add(buildVilleFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	public List<Ville> findAllByPrefecture(int prefecture) {
		List<Ville> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Ville where IdPrefecture = "+prefecture+"");
			while (result.next()) {
				all.add(buildVilleFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	public Ville findById(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Ville where id = " + id);
			if (result.next()) {
				return buildVilleFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	public Ville findByName(String Nom) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Ville where Nom = '" + Nom +"'");
			if (result.next()) {
				return buildVilleFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	public void delete(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			statement.execute("delete from Ville where id = "+id);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public void add (Ville ville) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "insert into Ville (Nom, IdPrefecture) values (?,?)";
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setString(1, ville.getNom());
			statement.setDouble(2, ville.getPrefecture());
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public void update (Ville ville, int id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update Ville set Nom=?, IdPrefecture=? where id ="+id;
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setString(1, ville.getNom());
			statement.setDouble(2, ville.getPrefecture());
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	private Ville buildVilleFromRow(ResultSet result) throws SQLException {

		return new Ville(result.getInt(1), result.getString(2),result.getInt(3));
	}
}
