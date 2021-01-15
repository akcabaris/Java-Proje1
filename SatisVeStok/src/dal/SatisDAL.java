package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex.types.SatisComplex;
import complex.types.StokComplex;
import contracts.SatisContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisContract> {

	@Override
	public void Insert(SatisContract entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Satis (UrunId, Tarih, Adet, PersonelId) VALUES("+entity.getUrunId()+",'"+ entity.getTarih()+"',"+entity.getAdet()+","+entity.getPersonalId()+")");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<SatisComplex> GetAllSatis() {

		List<SatisComplex> dataContracts = new ArrayList<SatisComplex>();

		Connection connection = getConnection();
		SatisComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT satis.Id, personel.AdiSoyadi, Adi, Adet, satis.Tarih FROM satis LEFT JOIN urunler ON satis.UrunId = urunler.Id LEFT JOIN personel ON satis.PersonelId = personel.Id ORDER BY satis.Id DESC");
			while (resultset.next()) {
				contract = new SatisComplex();
				contract.setId(resultset.getInt("satis.Id"));
				contract.setPersonelAdi(resultset.getString("personel.AdiSoyadi"));
				contract.setTarih(resultset.getString("satis.Tarih"));
				contract.setUrunAdi(resultset.getString("Adi"));
				contract.setAdet(resultset.getInt("Adet"));

				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataContracts;

	}

	@Override
	public List<SatisContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisContract Delete(SatisContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SatisContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
