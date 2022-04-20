package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import model.Incident;
import model.SuperHero;

public class SuperHeroDao extends Dao{

	//trouve tout les super hero referancer
	public List<SuperHero> findAll() {
		List<SuperHero> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from SuperHero");
			while (result.next()) {
				all.add(buildSuperHeroFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	//trouve un superhero par son id
	public SuperHero findById(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from SuperHero where id = " + id);
			if (result.next()) {
				return buildSuperHeroFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	public List<SuperHero> findByIdPrefecture(int id) {
		List<SuperHero> all = new ArrayList<>();
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from SuperHero where IdPrefecture = " + id);
			while (result.next()) {
				all.add(buildSuperHeroFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}
	
	//trouve un superhero par son nom
	public SuperHero findByName(String nom) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from SuperHero where Nom = '" + nom +"'");
			if (result.next()) {
				return buildSuperHeroFromRow(result);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return null;
	}
	
	
	//supprime un superhero
	public void delete(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			statement.execute("delete from SuperHero where id = "+id);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	//ajoute un super hero
	public void add (SuperHero superhero) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "insert into SuperHero (Nom, Telephone, Password, IdPrefecture) values (?,?,?,?)";
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setString(1, superhero.getNom());
			statement.setString(2, superhero.getTelephone());
			statement.setString(3, superhero.getPassword());
			statement.setInt(4, superhero.getPrefecture());
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	//modifie un superhero
	public void update (SuperHero superhero, int id) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "update SuperHero set Nom=?, Telephone=?, Password=?, IdPrefecture=? where id ="+id;
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setString(1, superhero.getNom());
			statement.setString(2, superhero.getTelephone());
			statement.setString(3, superhero.getPassword());
			statement.setInt(4, superhero.getPrefecture());
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
	
	//fonction utilitaire pour la recuperation de super hero depuis la bdd
	private SuperHero buildSuperHeroFromRow(ResultSet result) throws SQLException {

		return new SuperHero(result.getInt(1), result.getString(2), result.getString(3), result.getString(4),  result.getInt(5));
	}
	
}
