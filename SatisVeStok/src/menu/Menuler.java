package menu;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

import contracts.Kullanicilar;
import contracts.PersonelContract;
import dal.KullanicilarDAL;
import fe.UrunEkleFE;
import fe.YetkiEkleFE;
import fe.KategoriEkleFE;
import fe.LoginFE;
import fe.PersonelEkleFE;
import fe.SifreIslemleriFE;

public class Menuler {

	public static JMenuBar initBar() {
		JMenuBar bar = new JMenuBar();

		// Ürün menüsü
		JMenu urunlerMenu = new JMenu("Ürün Ýþlemleri");
		bar.add(urunlerMenu);

		JMenuItem urunEkleItem = new JMenuItem("Ürün Ekle");
		urunlerMenu.add(urunEkleItem);

		JMenuItem kategoriEkleItem = new JMenuItem("Tür Ekle");
		urunlerMenu.add(kategoriEkleItem);

	

		// Personel menüsü
		JMenu personellerMenu = new JMenu("Personel Ýþlemleri");
		bar.add(personellerMenu);

		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);

		JMenuItem sifreBelirleItem = new JMenuItem("Þifre Oluþturma");
		personellerMenu.add(sifreBelirleItem);
		
		JMenuItem yetkiEkleItem = new JMenuItem("Yetki Ekle");
		personellerMenu.add(yetkiEkleItem);

		
		PersonelContract contract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
		
		if (new KullanicilarDAL().GetYetkiId(contract.getId()).getYetkiId()==2) {
			personellerMenu.hide();
		}
		
		
		urunEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new UrunEkleFE();
					}
				});
			}
		});

		kategoriEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new KategoriEkleFE();

			}
		});

		personelEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new PersonelEkleFE();

					}
				});

			}
		});

		yetkiEkleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						new YetkiEkleFE();

					}
				});

			}
		});
		
		sifreBelirleItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				new SifreIslemleriFE();
			}
		});
		
		

		return bar;
	}

}
