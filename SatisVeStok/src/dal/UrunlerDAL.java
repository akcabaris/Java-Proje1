package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.UrunlerContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerContract> {

	@Override
	public void Insert(UrunlerContract entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Kategori (Adi, KategoriId, Tarih, Fiyat) VALUES('" + entity.getAdi() + "',"+ entity.getKategoriId() +", '"+entity.getTarih()+"',"+entity.getFiyat()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<UrunlerContract> GetAll() {

		List<UrunlerContract> dataContracts = new ArrayList<UrunlerContract>();
		
		Connection connection = getConnection();
		UrunlerContract contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT * FROM Urunler");
			while(resultset.next()) {
				contract = new UrunlerContract();
				contract.setId(resultset.getInt("Id"));
				contract.setAdi(resultset.getString("Adi"));
				contract.setKategoriId(resultset.getInt("KategoriId"));
				contract.setTarih(resultset.getString("Tarih"));
				
				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;
	

	}

	@Override
	public UrunlerContract Delete(UrunlerContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UrunlerContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
