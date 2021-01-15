package dal;

import java.sql.Connection
;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.KategoriContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriContract> {

	@Override
	public void Insert(KategoriContract entity) {
		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Kategori (Adi, ParentId) VALUES('" + entity.getAdi() + "',"
					+ entity.getParentId() + ")");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<KategoriContract> GetAll() {
		List<KategoriContract> dataContracts = new ArrayList<KategoriContract>();

		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM Kategori");
			while (resultset.next()) {
				contract = new KategoriContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdi(resultset.getString("Adi"));
				contract.setParentId(resultset.getInt("ParentId"));

				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;
	}

	public List<KategoriContract> GetAllParentId() {

		List<KategoriContract> dataContracts = new ArrayList<KategoriContract>();

		Connection connection = getConnection();
		KategoriContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM Kategori");
			while (resultset.next()) {
				contract = new KategoriContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdi(resultset.getString("Adi"));
				contract.setParentId(resultset.getInt("ParentId"));

				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;

	}

	@Override
	public KategoriContract Delete(KategoriContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(KategoriContract entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("UPDATE Kategori Set Adi = '" + entity.getAdi() + "', ParentId = " + entity.getParentId()
					+ " WHERE id = " + entity.getId() + " ");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
	
	public List<KategoriContract> GetSearchKategori(String kategoriAdi) {

		List<KategoriContract> dataContracts = new ArrayList<KategoriContract>();
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Kategori WHERE Adi LIKE '"+"%"+kategoriAdi+"%"+"'");
			
			while (rs.next()) {
				KategoriContract contract = new KategoriContract();
				
				contract.setAdi(rs.getString("Adi"));
				contract.setParentId(rs.getInt("ParentId"));
				
				dataContracts.add(contract);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		return dataContracts;
		
	}

	@Override
	public List<KategoriContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
