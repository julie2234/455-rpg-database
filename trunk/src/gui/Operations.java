package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Operations extends JPanel {
	JPanel castability;
	JPanel itemLevel;
	JPanel generateMonster;
	JPanel createCharacter;
	
	public Operations() throws Exception {
		castability = new JPanel();
		itemLevel = new JPanel();
		generateMonster = new JPanel();
		createCharacter = new JPanel();
		
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		
		JTabbedPane tabPane = new JTabbedPane();
		add(tabPane, BorderLayout.CENTER);
		tabPane.addTab("Castability", castability);
		tabPane.addTab("Item Level", itemLevel);
		tabPane.addTab("Generate Monster", generateMonster);
		tabPane.addTab("Create Character", createCharacter);

		castability.setLayout(new GridBagLayout());
		itemLevel.setLayout(new GridBagLayout());
		generateMonster.setLayout(new GridBagLayout());
		createCharacter.setLayout(new GridBagLayout());
		
		initializeCastability();
		initializeItemLevel();
		initializeGenerateMonster();
		initializeCreateCharacter();
		
/*
		// action listener for search button
		searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	String searchBy = (String) searchByDropdown.getSelectedItem();
        		String condition = textField.getText();
        		String result = "";
        		StringBuilder query = new StringBuilder("SELECT ");
        		for (JCheckBox box : checkboxes) {
        			if (box.isSelected())
        				query.append(box.getText() + ", "); 
        		}
        		if (query.substring(query.length() - 2, query.length()).equals(", ")) {
        			query.delete(query.length() - 2, query.length());
            		query.append(" FROM Item where " + searchBy + " = \"" + condition + "\";");
    				try {
    					result = Database.executeQuery(query.toString());
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
            		resultTextArea.setText(result);
            		revalidate();
        		} else {
            		resultTextArea.setText("Please select at least one checkbox.");
        		}

            }
        });
*/
	}
	
	private void initializeCastability() {
		castability.setLayout(new GridBagLayout());
	}
	
	private void initializeItemLevel() {
		itemLevel.setLayout(new GridBagLayout());
	}
	
	private void initializeGenerateMonster() {
		generateMonster.setLayout(new GridBagLayout());
	}
	
	private void initializeCreateCharacter() {
		createCharacter.setLayout(new GridBagLayout());
	}
}

