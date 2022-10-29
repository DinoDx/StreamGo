package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class RecensioneModelDM implements Model<RecensioneBean> {

	@Override
	public RecensioneBean doRetrieveByKey(String numProgressivo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean bean = new RecensioneBean();
		
		String selectSQL = "SELECT * FROM recensioni WHERE numProgressivo = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(numProgressivo));
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setVoto(rs.getInt("Voto"));
				bean.setNomeUtente(rs.getString("Username"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setNumProgressivo(rs.getInt("NumProgressivo"));
				bean.setNomeProdotto(rs.getString("NomeProdotto"));				
			}			
			
			System.out.println(bean);
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}
	
	public ArrayList<RecensioneBean> doRetrieveByUser(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
		
		String selectSQL = "SELECT * FROM recensioni WHERE username = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				
				bean.setVoto(rs.getInt("Voto"));
				bean.setNomeUtente(rs.getString("username"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setNumProgressivo(rs.getInt("NumProgressivo"));
				bean.setNomeProdotto(rs.getString("NomeProdotto"));	
				
				recensioni.add(bean);			
			}			
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return recensioni;
	}

	public ArrayList<RecensioneBean> doRetrieveByProduct(String nomeProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ArrayList<RecensioneBean> recensioni = new ArrayList<RecensioneBean>();
		
		String selectSQL = "SELECT * FROM recensioni WHERE nomeProdotto = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, nomeProdotto);
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				
				bean.setVoto(rs.getInt("Voto"));
				bean.setNomeUtente(rs.getString("username"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setNumProgressivo(rs.getInt("NumProgressivo"));
				bean.setNomeProdotto(rs.getString("NomeProdotto"));	
				
				recensioni.add(bean);			
			}			
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return recensioni;
	}
	
	public RecensioneBean doRetrieveByUserAndProduct(String username, String nomeProdotto) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		RecensioneBean recensione = null;
		
		String selectSQL = "SELECT * FROM recensioni WHERE username = ? AND nomeProdotto = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, nomeProdotto);
			
			System.out.println("doRetrieveByUserandProduct: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			int i = 1;
			while(rs.next()) {
				recensione = new RecensioneBean();
				
				recensione.setVoto(rs.getInt("Voto"));
				recensione.setNomeUtente(rs.getString("username"));
				recensione.setDescrizione(rs.getString("Descrizione"));
				recensione.setNumProgressivo(rs.getInt("NumProgressivo"));
				recensione.setNomeProdotto(rs.getString("NomeProdotto"));
				System.out.println(i + " " + recensione);
				i++;
			}			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return recensione;
	}
	
	@Override
	public Collection<RecensioneBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<RecensioneBean> recensioni = new LinkedList<RecensioneBean>();
		
		String selectSQL = "SELECT * FROM recensioni";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				RecensioneBean bean = new RecensioneBean();
				
				bean.setVoto(rs.getInt("Voto"));
				bean.setNomeUtente(rs.getString("Username"));
				bean.setDescrizione(rs.getString("Descrizione"));
				bean.setNumProgressivo(rs.getInt("NumProgressivo"));
				bean.setNomeProdotto(rs.getString("NomeProdotto"));	
				
				recensioni.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return recensioni;
	}

	@Override
	public void doSave(RecensioneBean recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO recensioni" +
				" (Username, Descrizione, Voto, NomeProdotto) VALUES (?, ?, ?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, recensione.getNomeUtente());
			preparedStatement.setString(2, recensione.getDescrizione());
			preparedStatement.setInt(3, recensione.getVoto());
			preparedStatement.setString(4, recensione.getNomeProdotto());
			
			System.out.println("doSave: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
		
			connection.commit();

		} finally {
				try {
					if(preparedStatement != null) 
						preparedStatement.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}		
	}

	@Override
	public void doUpdate(RecensioneBean recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE recensioni SET " +
				" Username = ?, Descrizione = ?, Voto = ?, NomeProdotto = ? WHERE numProgressivo = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setString(1, recensione.getNomeUtente());
			preparedStatement.setString(2, recensione.getDescrizione());
			preparedStatement.setInt(3, recensione.getVoto());
			preparedStatement.setString(4, recensione.getNomeProdotto());
			preparedStatement.setInt(5, recensione.getNumProgressivo());
			
			System.out.println("doUpdate: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}

	@Override
	public void doDelete(RecensioneBean recensione) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM Recensioni WHERE NumProgressivo = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, recensione.getNumProgressivo());
			
			System.out.println("doDelete: "+ preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			connection.commit();
			
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}	
	}

}
