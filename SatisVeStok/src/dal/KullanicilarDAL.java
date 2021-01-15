package dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import contracts.Kullanicilar;
import core.ObjectHelper;
import ýnterfaces.DALInterfaces;

public class KullanicilarDAL extends ObjectHelper implements DALInterfaces<Kullanicilar> {

	@Override
	public void Insert(Kullanicilar entity) {

		Connection connection = getConnection();

		try {
			Statement statement = connection.createStatement();
			statement.execute("INSERT INTO Kullanicilar (PersonelId, YetkiId, Sifre) VALUES(" + entity.getPersonelId()
					+ "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
			statement.close();
			connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public Kullanicilar GetPersonelIdAndSifre(int personelId, String sifre) {
		Kullanicilar contract = new Kullanicilar();
		List<Kullanicilar> listele = new ArrayList<Kullanicilar>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Kullanicilar WHERE PersonelId ="+personelId+" AND Sifre='"+sifre.trim()+"'");

			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setSifre(rs.getString("Sifre"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);

		}

		return contract;

	}
	
	
	public Kullanicilar GetYetkiId(int personelId) {
		Kullanicilar contract = new Kullanicilar();
		List<Kullanicilar> listele = new ArrayList<Kullanicilar>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Kullanicilar WHERE PersonelId ="+personelId+"");

			while (rs.next()) {
				contract.setId(rs.getInt("Id"));
				contract.setPersonelId(rs.getInt("PersonelId"));
				contract.setYetkiId(rs.getInt("YetkiId"));
				
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println(e);

		}

		return contract;

	}

	@Override
	public List<Kullanicilar> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Kullanicilar Delete(Kullanicilar entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Kullanicilar entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Kullanicilar> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
