package gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private JTabbedPane tabPane;
	private Character character;
	private Items items;
	private Spells spells;
	private Operations operations;
	
	public MainFrame() {
		super();
		
		setTitle("RPG Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().add(tabPane);
		
		tabPane = new JTabbedPane();
		character = new Character();
		items = new Items();
		spells = new Spells();
		operations = new Operations();
		
		tabPane.addTab("Character", character);
		tabPane.addTab("Items", items);
		tabPane.addTab("Spells", spells);
		tabPane.addTab("Operations", operations);
	}
	
	public static void Main(final String args[]) {
		MainFrame rpgDatabase = new MainFrame();
		rpgDatabase.pack();
		rpgDatabase.setVisible(true);
	}
}
