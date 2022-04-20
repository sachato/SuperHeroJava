package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Dao {

	Connection getConnection() {
//		String url = "jdbc:postgresql://172.17.0.2:5432/peopledb";
		String url = "jdbc:mysql://mysql-cesijava.alwaysdata.net/cesijava_cubeperso";
		Properties props = new Properties();
		props.setProperty("user", "cesijava");
		props.setProperty("password", "SALUTgoogle2.0*");
		props.setProperty("ssl", "false");
		try {
			return DriverManager.getConnection(url, props);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void closeConnection(Connection connection, Statement statement) {
		try {
			if(statement!=null) {
				statement.close();
			}
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
