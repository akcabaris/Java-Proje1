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
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;

import contracts.Kullanicilar;
import contracts.PersonelContract;
import contracts.YetkilerContract;
import dal.KullanicilarDAL;
import dal.PersonelDAL;
import dal.YetkilerDAL;
import ýnterfaces.FeInterfaces;

public class SifreIslemleriFE extends JDialog implements FeInterfaces {

	public SifreIslemleriFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		panel.setBorder(BorderFactory.createTitledBorder("Þifre Belirleme"));
		add(panel);

		setTitle("Þifre Belirleme");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(5, 2));

		JLabel personelLabel = new JLabel("Personel Seç", JLabel.RIGHT);
		panel.add(personelLabel);

		JComboBox personelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(personelBox);

		JLabel yetkiLabel = new JLabel("Yetki Seç:", JLabel.RIGHT);
		panel.add(yetkiLabel);

		JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
		panel.add(yetkiBox);

		JLabel passwordLabel = new JLabel("Þifre Belirle:", JLabel.RIGHT);
		panel.add(passwordLabel);

		JPasswordField passField = new JPasswordField(10);
		panel.add(passField);

		JLabel passTekrarLabel = new JLabel("Þifre Tekrar:", JLabel.RIGHT);
		panel.add(passTekrarLabel);

		JPasswordField passTekrarField = new JPasswordField(10);
		panel.add(passTekrarField);


		JButton iptalButton = new JButton("Ýptal");
		panel.add(iptalButton);
		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);


		kaydetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Kullanicilar contract = new Kullanicilar();
				PersonelContract contractPersonel = (PersonelContract) personelBox.getSelectedItem();
				YetkilerContract contractYetkiler = (YetkilerContract) yetkiBox.getSelectedItem();

				if (passField.getText().equals(passTekrarField.getText())) {
					contract.setPersonelId(contractPersonel.getId());
					contract.setYetkiId(contractYetkiler.getId());
					contract.setSifre(passField.getText());

					JOptionPane.showMessageDialog(null, contractPersonel.getAdiSoyadi() + " isimli personele, "
							+ contractYetkiler.getAdi() + " olarak" + " þifre oluþturulmuþtur.");
					new KullanicilarDAL().Insert(contract);

				} else {
					JOptionPane.showMessageDialog(null, "Þifreler uyuþmuyor! Tekrar Deneyin");
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
