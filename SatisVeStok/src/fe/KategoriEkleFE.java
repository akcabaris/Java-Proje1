package fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import contracts.KategoriContract;
import dal.KategoriDAL;
import ýnterfaces.FeInterfaces;

public class KategoriEkleFE extends JDialog implements FeInterfaces {

	public KategoriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));

		add(panel);
		setTitle("Tür(Genre) Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		
		JPanel panel = new JPanel(new GridLayout(3, 2));
		JLabel adiLabel = new JLabel("Tür Adý:", JLabel.RIGHT);
		panel.add(adiLabel);
		JTextField adiField = new JTextField(10);
		panel.add(adiField);

		JLabel kategoriLabel = new JLabel("Tür Seç", JLabel.RIGHT);
		panel.add(kategoriLabel);
		JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
		panel.add(kategoriBox);
		kategoriBox.insertItemAt("--Tür Seçiniz--", 0);
		kategoriBox.setSelectedIndex(0);
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton); 

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KategoriContract contract = new KategoriContract();
				
				
				if (kategoriBox.getSelectedIndex() != 0) {
					KategoriContract cascontract = (KategoriContract) kategoriBox.getSelectedItem();
					contract.setAdi(adiField.getText());
					contract.setParentId(cascontract.getId());
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, contract.getAdi() + " adlý tür eklendi.");
				}else {
					contract.setAdi(adiField.getText());
					contract.setParentId(kategoriBox.getSelectedIndex());
					
					
					new KategoriDAL().Insert(contract);
					JOptionPane.showMessageDialog(null, contract.getAdi() + "adlý tür eklendi.");
					kategoriBox.setSelectedItem(new KategoriDAL().GetAllParentId().toArray());
				}
				
				
				
			}
		});
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
