package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Maitrise;
import model.SuperHero;
import model.Ville;

public class MaitriseDao extends Dao {
	
	public void add (Maitrise maitrise) {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		try {
			String sql = "insert into Maitrise (IdSuperhero, IdIncident) values (?,?)";
			statement = connection.prepareStatement(sql);
			//((PreparedStatement) statement).setInt(1, superhero.getId());
			statement.setInt(1, maitrise.getIdSuperHero());
			statement.setInt(2, maitrise.getIdIncident());
			statement.executeUpdate();

//			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
	}
	
	public List<Maitrise> findBySuperHero(int id) {
		Connection connection = getConnection();
		Statement statement=null;
		List<Maitrise> all = new ArrayList<>();
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from Maitrise where IdSuperHero = " + id);
			while (result.next()) {
				all.add(buildMaitriseFromRow(result));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			closeConnection(connection, statement);
		}
		return all;
	}

	private Maitrise buildMaitriseFromRow(ResultSet result) throws SQLException {

		return new Maitrise(result.getInt(1), result.getInt(2),result.getInt(3));
	}
}
