package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class Operations extends JPanel {
	
	public Operations() throws Exception {
		super();
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		
		Castability castability = new Castability();
		ItemLevel itemLevel = new ItemLevel();
		GenerateMonster generateMonster = new GenerateMonster();
		CreateCharacter createCharacter = new CreateCharacter();
		
		JTabbedPane tabPane = new JTabbedPane();
		add(tabPane, BorderLayout.CENTER);
		tabPane.addTab("Castability", castability);
		tabPane.addTab("Item Level", itemLevel);
		tabPane.addTab("Generate Monster", generateMonster);
		tabPane.addTab("Create Character", createCharacter);
		
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
}

