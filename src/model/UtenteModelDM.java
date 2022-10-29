package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UtenteModelDM implements Model<UtenteBean> {

	@SuppressWarnings("resource")
	public UtenteBean doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs;

		UtenteBean bean = new UtenteBean();

		String selectSQL = "SELECT * FROM utenti WHERE username = ?";
		String telefoniSQL = "SELECT * FROM telefoni Where username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				if (rs.getString("ruolo").equalsIgnoreCase("Amministratore"))
					bean.setAdmin(true);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(telefoniSQL);
			preparedStatement.setString(1, username);
			System.out.println(preparedStatement);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.addTelefono(rs.getString("Numero"));
			}

			RecensioneModelDM model = new RecensioneModelDM();
			bean.setRecensioni(model.doRetrieveByUser(username));

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return bean;
	}

	@Override
	public ArrayList<UtenteBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<UtenteBean> utente = new ArrayList<UtenteBean>();

		String selectSQL = "SELECT * FROM utenti";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();

				bean.setUsername(rs.getString("username"));
				bean.setPassword(rs.getString("password"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				if (rs.getString("ruolo").equalsIgnoreCase("Amministratore"))
					bean.setAdmin(true);

				utente.add(bean);
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return utente;
	}

	public ArrayList<String> doRetrieveConsigliati(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<String> nomi = new ArrayList<String>();

		String selectSQL = "SELECT p.nome, AVG(voto) AS media, count(*)\r\n"
				+ "FROM prodotti as p inner join  recensioni as r on p.nome=r.nomeprodotto\r\n"
				+ "where p.nome NOT IN (select nomeprodotto from recensioni where username = ?)\r\n"
				+ "GROUP BY p.nome\r\n" + "ORDER BY media DESC;";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			System.out.println("doRetrieveConsigliati:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			for (int i = 0; i < 4 && rs.next(); i++) {
				nomi.add(rs.getString("nome"));
			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println("NOMI = " + nomi);
		return nomi;
	}

	@Override
	public void doSave(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO utenti" + " VALUES (?, ?, ?, ?, ?);";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			if (utente.isAdmin())
				preparedStatement.setString(3, "Amministratore");
			else
				preparedStatement.setString(3, "Utente");
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());

			System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUpdate(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE utenti SET " + "password = ?, ruolo = ?, nome = ?, cognome = ? WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, utente.getPassword());
			if (utente.isAdmin())
				preparedStatement.setString(2, "Amministratore");
			else
				preparedStatement.setString(2, "Utente");
			preparedStatement.setString(3, utente.getNome());
			preparedStatement.setString(4, utente.getCognome());

			preparedStatement.setString(5, utente.getUsername());

			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void doUpdate(String username, UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE utenti SET "
				+ "username = ?, password = ?, ruolo = ?, nome = ?, cognome = ? WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, utente.getUsername());
			preparedStatement.setString(2, utente.getPassword());
			if (utente.isAdmin())
				preparedStatement.setString(3, "Amministratore");
			else
				preparedStatement.setString(3, "Utente");
			preparedStatement.setString(4, utente.getNome());
			preparedStatement.setString(5, utente.getCognome());

			preparedStatement.setString(6, username);

			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doDelete(UtenteBean utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM utenti WHERE username = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, utente.getUsername());

			System.out.println("doDelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void addNumber(String username, String numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO telefoni " + "VALUES (?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, numero);
			preparedStatement.setString(2, username);

			System.out.println("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public void deleteNumber(String numero) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM telefoni WHERE numero = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, numero);

			System.out.println("doDelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	public String checkLogin(String username, String password) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String selectSQL = "SELECT * FROM utenti WHERE username = ? and password = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);

			System.out.println("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next())
				return rs.getString("ruolo");
			else
				return null;
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
}
