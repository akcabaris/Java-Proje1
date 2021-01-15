package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import complex.types.StokComplex;
import complex.types.StokTotalComplex;
import contracts.KategoriContract;
import contracts.StokContract;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokContract> {

	@Override
	public void Insert(StokContract entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Stok (PersonelId, UrunId, Tarih, Adet) VALUES(" + entity.getPersonalId()
					+ "," + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");

			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<StokComplex> GetAllStok() {

		List<StokComplex> dataContracts = new ArrayList<StokComplex>();

		Connection connection = getConnection();
		StokComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement
					.executeQuery("SELECT stok.Id, Personel.AdiSoyadi, urunler.Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler ON stok.UrunId = urunler.Id LEFT JOIN personel ON stok.PersonelId = personel.Id ORDER BY stok.Id DESC");
			while (resultset.next()) {
				contract = new StokComplex();
				contract.setId(resultset.getInt("Id"));
				contract.setPersonelAdi(resultset.getString("AdiSoyadi"));
				contract.setUrunAdi(resultset.getString("Adi"));
				contract.setAdet(resultset.getInt("Adet"));
				contract.setTarih(resultset.getString("stok.Tarih"));

				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;

	}

	public List<StokTotalComplex> GetTotalStok() {

		List<StokTotalComplex> dataContracts = new ArrayList<StokTotalComplex>();

		Connection connection = getConnection();
		StokTotalComplex contract;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultset = statement.executeQuery("SELECT SUM(Adet) as toplam, stok.Id, AdiSoyadi, Adi, Adet, stok.Tarih FROM stok LEFT JOIN urunler ON stok.UrunId = urunler.Id LEFT JOIN personel on stok.PersonelId = Personel.Id GROUP BY UrunId");
			while (resultset.next()) {
				contract = new StokTotalComplex();
				contract.setId(resultset.getInt("Id"));
				contract.setPersonelAdi(resultset.getString("AdiSoyadi"));
				contract.setUrunAdi(resultset.getString("Adi"));
				contract.setAdet(resultset.getInt("Adet"));
				contract.setTarih(resultset.getString("stok.Tarih"));
				contract.setToplam(resultset.getInt("toplam"));
				
				dataContracts.add(contract);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataContracts;

	}

	@Override
	public List<StokContract> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokContract Delete(StokContract entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokContract entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StokContract> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
