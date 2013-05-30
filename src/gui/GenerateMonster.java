package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class GenerateMonster extends JPanel {
	
	public GenerateMonster() throws Exception {
		super();
		initialize();
	}

	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		Border etched = BorderFactory.createEtchedBorder();

		// create search panel
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(0,1));
		searchPanel.setBorder(BorderFactory.createTitledBorder(etched, "Monster level"));
		final JTextField levelField = new JTextField();
		levelField.setColumns(10);
		searchPanel.add(levelField);
		
		// create results area
		final JPanel resultsPanel = new JPanel();
		resultsPanel.setLayout(new BorderLayout());
		
		// create buttons
		final JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	String lvl = levelField.getText();
            	
            	String query = "SELECT * FROM `Character` where characterLevel = " 
    					+ lvl + " AND playerBoolean = \"F\" ORDER BY RAND() LIMIT 1;";
            	try {
    				JTable table = Database.executeQuery(query.toString());
    				JScrollPane scrollArea = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					resultsPanel.removeAll();
					resultsPanel.add(scrollArea, BorderLayout.CENTER);
					revalidate();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }});
		
		// add everything to the panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		sidebar.add(searchPanel, new GBC(0,0,1,2));
		sidebar.add(generateButton, new GBC(0,3,1,1).setFill(GBC.HORIZONTAL));
		add(sidebar, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.CENTER);
	}
}
