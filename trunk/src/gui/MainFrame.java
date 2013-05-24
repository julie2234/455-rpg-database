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
	private static Database db;
	
	public MainFrame() throws Exception {
		super();
		
		setTitle("RPG Database");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPane = new JTabbedPane();
		getContentPane().add(tabPane);
		
		character = new Character(this);
		items = new Items();
		spells = new Spells();
		operations = new Operations();
		
		tabPane.addTab("Character", character);
		tabPane.addTab("Items", items);
		tabPane.addTab("Spells", spells);
		tabPane.addTab("Operations", operations);
	}
	
	public static void main(final String args[]) throws Exception {
		db = new Database();
		MainFrame frame = new MainFrame();
		frame.setBounds(100, 100, 855, 541);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.pack();
		frame.setVisible(true);
	}
	
	public Database getDb() {
		return db;
	}
}
