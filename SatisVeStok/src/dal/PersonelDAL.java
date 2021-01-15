package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.PersonelContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelContract>{

	@Override
	public void Insert(PersonelContract entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Personel (AdiSoyadi, Email) VALUES('" + entity.getAdiSoyadi() + "','"
					+ entity.getEmail() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	
		
	}

	@Override
	public List<PersonelContract> GetAll() {

		List<PersonelContract> dataContracts = new ArrayList<PersonelContract>();
		
		Connection connection = getConnection();
		PersonelContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM Personel");
			while(resultset.next()) {
				contract = new PersonelContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdiSoyadi(resultset.getString("AdiSoyadi"));
				contract.setEmail(resultset.getString("Email"));
				
				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;
	
	}

	@Override
	public PersonelContract Delete(PersonelContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(PersonelContract entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PersonelContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}}
