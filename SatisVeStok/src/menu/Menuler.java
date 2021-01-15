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

		// �r�n men�s�
		JMenu urunlerMenu = new JMenu("�r�n ��lemleri");
		bar.add(urunlerMenu);

		JMenuItem urunEkleItem = new JMenuItem("�r�n Ekle");
		urunlerMenu.add(urunEkleItem);

		JMenuItem kategoriEkleItem = new JMenuItem("T�r Ekle");
		urunlerMenu.add(kategoriEkleItem);

	

		// Personel men�s�
		JMenu personellerMenu = new JMenu("Personel ��lemleri");
		bar.add(personellerMenu);

		JMenuItem personelEkleItem = new JMenuItem("Personel Ekle");
		personellerMenu.add(personelEkleItem);

		JMenuItem sifreBelirleItem = new JMenuItem("�ifre Olu�turma");
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
