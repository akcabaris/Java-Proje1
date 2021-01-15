package fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import contracts.PersonelContract;
import dal.PersonelDAL;
import ýnterfaces.FeInterfaces;

public class PersonelEkleFE extends JDialog implements FeInterfaces{
	
	public PersonelEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
		add(panel);
		
		setTitle("Personel Ekle");
		pack();
		setLocationRelativeTo(null);
		setModalityType(DEFAULT_MODALITY_TYPE);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3, 2));
		
		JLabel adiSoyadiLabel = new JLabel("Adý Soyadý:",JLabel.RIGHT);
		panel.add(adiSoyadiLabel);
		
		JTextField adiSoyadiField = new JTextField(10);
		panel.add(adiSoyadiField);
		
		
		JLabel eMailLabel = new JLabel("Email:",JLabel.RIGHT);
		panel.add(eMailLabel);
		
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);
		
		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		
		JButton kaydetButton =new JButton("Kaydet");
		panel.add(kaydetButton);

		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelContract contract = new PersonelContract();
				contract.setAdiSoyadi(adiSoyadiField.getText());
				contract.setEmail(eMailField.getText());
				
				new PersonelDAL().Insert(contract);
				JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+ " adlý personel eklendi");
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
