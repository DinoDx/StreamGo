package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class SerieTvModelDM implements Model<SerieTvBean>{
	
	@Override
	public SerieTvBean doRetrieveByKey(String code) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		SerieTvBean bean = null;
		
		String selectSQL = "select p.nome, p.durata, s.numepisodi, s.Link, s.Img FROM prodotti as p inner join serietv as s ON p.nome = s.nome WHERE p.nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, code);
			
			System.out.println("doRetrieveByKey: "+ preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = new SerieTvBean();
				bean.setNome(rs.getString("Nome"));
				bean.setDurata(rs.getInt("Durata"));
				bean.setNumEpisodi(rs.getInt("NumEpisodi"));
				bean.setLink(rs.getString("Link"));
				bean.setImg(rs.getString("Img"));
				
				
				RecensioneModelDM model = new RecensioneModelDM();
				bean.setRecensioni(model.doRetrieveByProduct(code));
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

	@Override
	public Collection<SerieTvBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<SerieTvBean> products = new LinkedList<SerieTvBean>();
		
		String selectSQL = "select p.nome, p.durata, s.numepisodi, s.Link, s.Img FROM prodotti as p inner join serietv as s ON p.nome = s.nome";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				SerieTvBean bean = new SerieTvBean();
				
				bean.setNome(rs.getString("Nome"));
				bean.setNumEpisodi(rs.getInt("NumEpisodi"));
				bean.setDurata(rs.getInt("Durata"));
				bean.setLink(rs.getString("Link"));
				bean.setImg(rs.getString("Img"));
				
				RecensioneModelDM model = new RecensioneModelDM();
				bean.setRecensioni(model.doRetrieveByProduct(rs.getString("Nome")));

				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
		
	}

	public Collection<SerieTvBean> doRetrieveAll(String order, String verso) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<SerieTvBean> products = new LinkedList<SerieTvBean>();
		
		String selectSQL = "select p.nome, p.durata, s.numepisodi, s.Link, s.Img FROM prodotti as p inner join serietv as s ON p.nome = s.nome";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		if(verso != null && !verso.equals("")) {
			selectSQL += " " + verso;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				SerieTvBean bean = new SerieTvBean();
				
				bean.setNome(rs.getString("Nome"));
				bean.setNumEpisodi(rs.getInt("NumEpisodi"));
				bean.setDurata(rs.getInt("Durata"));
				bean.setLink(rs.getString("Link"));
				bean.setImg(rs.getString("Img"));
				
				RecensioneModelDM model = new RecensioneModelDM();
				bean.setRecensioni(model.doRetrieveByProduct(rs.getString("Nome")));

				products.add(bean);
			}
		} finally {
			try {
				if(preparedStatement != null) 
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return products;
		
	}

	
	@Override
	public void doSave(SerieTvBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO prodotti" +
				" (Nome, Durata) VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, product.getNome());
			preparedStatement.setInt(2, product.getDurata());
			
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
		
		connection = null;
		preparedStatement = null;
		insertSQL = "INSERT INTO SerieTv" +
				" (NumEpisodi, Nome, Link, Img ) VALUES (?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, product.getNumEpisodi());
			preparedStatement.setString(2, product.getNome());
			preparedStatement.setString(3, product.getLink());
			preparedStatement.setString(4, product.getImg());
			
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
	public void doUpdate(SerieTvBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE SerieTV SET " +
				" Durata = ?, NumEpisodi = ?, WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);	
			
			preparedStatement.setInt(1, product.getDurata());
			preparedStatement.setInt(2, product.getNumEpisodi());
			
			preparedStatement.setString(3, product.getNome());
			
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
	public void doDelete(SerieTvBean product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM Prodotti WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, product.getNome());
			
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
		
		deleteSQL = "DELETE FROM SerieTv WHERE Nome = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, product.getNome());
			
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