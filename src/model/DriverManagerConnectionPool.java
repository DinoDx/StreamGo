package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

	private static List<Connection> freeDbConnections;
	private static String url;
	private static String database;
	private static String username;
	private static String password;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:" + e.getMessage());
		}
	}

	public static synchronized void initializeConnection(String newUrl, String db, String u_name, String pass)
			throws SQLException {
		url = newUrl;
		database = db;
		username = u_name;
		password = pass;

		Connection newConnection = DriverManager.getConnection(url + database, username, password);
		newConnection.setAutoCommit(false);
		System.out.println("Create a new DB connection");

		if (newConnection != null)
			freeDbConnections.add(newConnection);
//	return newConnection;
	}

	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
		/*
		 * String ip = "localhost"; String port = "3306"; String db = "esameTsw"; String
		 * username = "esameTsw"; String password = "esameTsw";
		 */
		newConnection = DriverManager.getConnection(url + database, username, password);

		System.out.println("Create a new DB connection");
		newConnection.setAutoCommit(false);
		return newConnection;
	}

	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if (connection != null)
			freeDbConnections.add(connection);
	}

}
