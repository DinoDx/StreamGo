package model;

import java.sql.SQLException;
import java.util.Collection;

public interface Model<T> {
	
	public T doRetrieveByKey(String code) throws SQLException;
	
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	
	public void doSave(T product) throws SQLException;
	
	public void doUpdate(T product) throws SQLException;
	
	public void doDelete(T product) throws SQLException;
}
