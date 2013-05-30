package gui;

import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public MainFrame() throws Exception {
		setTitle("RPG Database");
		setBounds(100, 100, 900, 600);
		addWindowListener(new DisconnectAndClose());
		setResizable(false);
		
		JTabbedPane tabPane = new JTabbedPane();
		getContentPane().add(tabPane);
		tabPane.addTab("Characters", new CharacterTab());
		tabPane.addTab("Items", new ItemTab());
		tabPane.addTab("Spells", new SpellTab());
		tabPane.addTab("Backpacks", new Backpack());
		tabPane.addTab("Spell Trees", new SpellTree());
		tabPane.addTab("Equipped Items", new EquippedItems());
		
		
	}
	
	public static void main(final String args[]) throws Exception {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		//frame.pack();
		frame.setLocationRelativeTo(null);
		//Database.connect();
		createGuiFrame();
	}
	
	private static void createGuiFrame() {

		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(2, 2));

		JLabel usernameLbl = new JLabel("Username:");
		JLabel passwordLbl = new JLabel("Password:");
		JTextField username = new JTextField();
		JPasswordField password = new JPasswordField();

		userPanel.add(usernameLbl);
		userPanel.add(username);
		userPanel.add(passwordLbl);
		userPanel.add(password);

		int input = JOptionPane.showConfirmDialog(null, userPanel,
				"Enter your login information:", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (input == JOptionPane.OK_OPTION) {
			
			String pass = new String(password.getPassword());
			try {
				Database.connect(username.getText(), pass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	class DisconnectAndClose extends WindowAdapter {
		public void windowClosing(WindowEvent e)
		  {
			try {
				Database.disconnect();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    System.exit(0);
		  }
	}
	
}
