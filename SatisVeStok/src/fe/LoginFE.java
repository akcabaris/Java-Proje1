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

import contracts.PersonelContract;
import dal.KullanicilarDAL;
import dal.PersonelDAL;
import �nterfaces.FeInterfaces;

public class LoginFE extends JDialog implements FeInterfaces {
	
	public static JComboBox emailBox;

	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		add(panel);
		panel.setBorder(BorderFactory.createTitledBorder("Giri� Ekran�"));
		setTitle("Giri� Yap");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel emailLabel = new JLabel("Kullan�c�:", JLabel.RIGHT);
		panel.add(emailLabel);
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		JLabel passwordLabel = new JLabel("�ifre:", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(12);
		panel.add(passwordField);
		
		JButton iptalButton = new JButton("�ptal");
		panel.add(iptalButton); 
		JButton loginButton = new JButton("Giri� Yap");
		panel.add(loginButton);

		
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				PersonelContract contract = (PersonelContract) emailBox.getSelectedItem();
				
				if (new KullanicilarDAL().GetPersonelIdAndSifre(contract.getId(), passwordField.getText()).getId()!=0) {
					new AnaPencereFE();
					// Ana pencere a��ld�ktan sonra hide() yazd�m ��nk� exit yapmad�k�a giri� ekran� a��k kal�yor
					hide();
					
				}else {
					JOptionPane.showMessageDialog(null, "Giri� Yap�lamad�.");
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
