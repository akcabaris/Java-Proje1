package fe;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import complex.types.SatisComplex;
import complex.types.StokComplex;
import complex.types.StokTotalComplex;
import contracts.PersonelContract;
import contracts.SatisContract;
import contracts.StokContract;
import contracts.UrunlerContract;
import dal.SatisDAL;
import dal.StokDAL;
import dal.UrunlerDAL;
import menu.Menuler;
import ýnterfaces.FeInterfaces;

public class AnaPencereFE extends JFrame implements FeInterfaces {

	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();

		add(panel);
		setJMenuBar(bar);
		setTitle("Rock & Metal Plak");
		setSize(700, 500);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE); // bunu yazmadýðým taktirde uygulamayý kapatsam da arka planda çalýþýr.
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = Menuler.initBar();
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();

		ImageIcon icon = new ImageIcon("icons/stok.png");
		ImageIcon icon2 = new ImageIcon("icons/satis.png");

		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		// Stok
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(7,2));
		JPanel stokSolAltPanel = new JPanel();
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok Ýþlemleri"));

		Object[] stokKolonlar = { "Id", "Ürün Adý", "Personel Adý", "Adet/Toplam", "Tarihi" };
		DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);

		for (StokComplex contract : new StokDAL().GetAllStok()) {
			model.addRow(contract.getVeriler());
		}

		JLabel stokUrunAdiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		JLabel stokAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:", JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();
		stokSolUstPanel.add(stokTarihi);

		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		stokYenileButton.setBackground(Color.gray);
		stokYenileButton.setForeground(Color.BLACK);
		
		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		stokEkleButton.setBackground(Color.GRAY);
		stokEkleButton.setForeground(Color.BLACK);

		JButton stokTotalButton = new JButton("Stok toplam");
		stokSolUstPanel.add(stokTotalButton);
		stokTotalButton.setBackground(Color.gray);
		stokTotalButton.setForeground(Color.black);
		
		stokTotalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				
				for (StokTotalComplex total : new StokDAL().GetTotalStok()){
					model.addRow(total.getVeriler());
				}
			}
		});
		
		stokYenileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int satir = model.getRowCount();

				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokComplex complexContract : new StokDAL().GetAllStok()) {
					model.addRow(complexContract.getVeriler());
				}

			}
		});
		
		
		stokEkleButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StokContract contract = new StokContract();

				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();

				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(stokTarihi.getDate());

				contract.setPersonalId(pContract.getId());
				contract.setUrunId(uContract.getId());
				contract.setTarih(date.toString());
				contract.setAdet(Integer.parseInt(stokAdetField.getText()));
				new StokDAL().Insert(contract);

				JOptionPane.showMessageDialog(null, uContract.getAdi() + " adlý ürün eklendi.");

				int satir = model.getRowCount();

				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokComplex complexContract : new StokDAL().GetAllStok()) {
					model.addRow(complexContract.getVeriler());
				}
			}
		});

		// Satis

		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(7, 2));
		JPanel satisSagAltPanel = new JPanel();
		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satýþ Ýþlemleri"));

		Object[] satisKolonlar = { "Id", "Personel Adý", "Ürün Adý", "Adeti", "Tarihi" };
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);

		JLabel satisUrunAdiLabel = new JLabel("Ürün Adý:", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		JLabel satisAdetLabel = new JLabel("Adet:", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("satis Tarihi:", JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();
		satisSagUstPanel.add(satisTarihi);

		JButton satisEkleButton = new JButton("Satýþ Yap");
		satisSagUstPanel.add(satisEkleButton);
		
		for (SatisComplex nContrant: new SatisDAL().GetAllSatis()){
			satisModel.addRow(nContrant.getVeriler());
		}
		
		satisEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Satýþ apan personel, giriþ yapan personel
				PersonelContract pContract = (PersonelContract) LoginFE.emailBox.getSelectedItem();
				UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
				
				SatisContract contract = new SatisContract();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(satisTarihi.getDate());
				
				contract.setPersonalId(pContract.getId());
				contract.setAdet(Integer.parseInt(satisAdetField.getText()));
				contract.setUrunId(uContract.getId());
				contract.setTarih(date);
				
				new SatisDAL().Insert(contract);
				
				StokContract stokContract = new StokContract();
				stokContract.setPersonalId(pContract.getId());
				stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
				stokContract.setUrunId(uContract.getId());
				stokContract.setTarih(date);
				
				new StokDAL().Insert(stokContract);
				
				JOptionPane.showMessageDialog(null, "Satýþ Yapýldý.");
				int satir = satisModel.getRowCount();

				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				
				for (SatisComplex nContrant: new SatisDAL().GetAllSatis()){
					satisModel.addRow(nContrant.getVeriler());
				}
				
			}
		}); 

		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int satir = satisModel.getRowCount();

				for (int i = 0; i < satir; i++) {
					satisModel.removeRow(0);
				}
				
				for (SatisComplex contract : new SatisDAL().GetAllSatis()){
					satisModel.addRow(contract.getVeriler());
				}
				
			}
		});

		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);

		satisPanel.add(satisSagPanel, BorderLayout.WEST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);

		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);

		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);

		pane.addTab("Stoklar", icon, stokPanel, "");

		pane.addTab("Satýþlar ", icon2, satisPanel, "");
		return pane;
	}
	

}
