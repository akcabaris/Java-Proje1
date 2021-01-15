package fe;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import contracts.KategoriContract;
import contracts.UrunlerContract;
import dal.KategoriDAL;
import dal.UrunlerDAL;
import �nterfaces.FeInterfaces;

// mirass�n� JDialog 'tan ald�m c�nk� JFrame 'den alsayd�m pencereyi kapat�nca program kapan�cakt�.
public class UrunEkleFE extends JDialog implements FeInterfaces {

	public UrunEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();
		
		panel.setBorder(BorderFactory.createTitledBorder("�r�n Kay�t Alan�"));
		add(panel);
		setTitle("�r�n Ekleyin");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(8, 2));

		JLabel adiLabel = new JLabel("�r�n Ad�:", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);
		
		JLabel kategoriLabel = new JLabel("Genre(T�r) Se�:", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
		panel.add(kategoriBox);
		
		
		JLabel tarihLabel = new JLabel("Tarih Se�:", JLabel.RIGHT);
		panel.add(tarihLabel);
		JDateChooser tarihDate = new JDateChooser();
		panel.add(tarihDate);

		JLabel fiyatLabel = new JLabel("Fiyat Gir", JLabel.RIGHT);
		panel.add(fiyatLabel);
		JTextField fiyatField = new JTextField(10);
		panel.add(fiyatField);
		
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton);

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UrunlerContract contract = new UrunlerContract();
				KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
				
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String date = format.format(tarihDate.getDate());
				
				contract.setAdi(adiField.getText());
				contract.setKategoriId(casContract.getId());
				contract.setTarih(date);
				contract.setFiyat(Float.parseFloat(fiyatField.getText()));
				
				
				
				new UrunlerDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdi()+" adl� �r�n eklendi");
			}
		});

		return panel;
	}

	@Override
	public JMenuBar initBar() {
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
