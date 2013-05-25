import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	public MainFrame() throws Exception {
		setTitle("RPG Database");
		setBounds(100, 100, 900, 600);
		addWindowListener(new DisconnectAndClose());
		
		JTabbedPane tabPane = new JTabbedPane();
		getContentPane().add(tabPane);
		tabPane.addTab("Character", new Character());
		tabPane.addTab("Items", new Items());
		tabPane.addTab("Spells", new Spells());
		tabPane.addTab("Operations", new Operations());
	}
	
	public static void main(final String args[]) throws Exception {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);
		Database.connect();
	}
	
	class DisconnectAndClose extends WindowAdapter {
		public void windowClosing(WindowEvent e)
		  {
			try {
				Database.disconnect();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    System.exit(0);
		  }
	}
	
}
