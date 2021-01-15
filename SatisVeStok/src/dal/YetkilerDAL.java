package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.YetkilerContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class YetkilerDAL extends ObjectHelper implements DALInterfaces<YetkilerContract> {

	@Override
	public void Insert(YetkilerContract entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Yetkiler (Adi) VALUES('" + entity.getAdi() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	
	}

	@Override
	public List<YetkilerContract> GetAll() {


		List<YetkilerContract> dataContracts = new ArrayList<YetkilerContract>();
		
		Connection connection = getConnection();
		YetkilerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM Yetkiler");
			while(resultset.next()) {
				contract = new YetkilerContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdi(resultset.getString("Adi"));
				
				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContracts;
	
	
	}

	@Override
	public YetkilerContract Delete(YetkilerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(YetkilerContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<YetkilerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
