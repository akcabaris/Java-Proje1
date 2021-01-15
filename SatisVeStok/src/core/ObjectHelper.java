package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import �nterfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces{
	
	
	/*mysql'e baglanmayi saglicak */
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Connection getConnection() {
		
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}