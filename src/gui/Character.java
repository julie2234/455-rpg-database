import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Character extends JPanel {
	private String[] attributes = {"characterID", "characterName", "race", "playerBoolean", "backpackID", "HP", "MP", "stamina", "experience"};

	public Character() throws Exception {
		setLayout(new BorderLayout());
		initialize();
	}
	
	public void initialize() throws Exception {
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridLayout(0, 1));
		sidebar.add(new JLabel("Search by:"));
		final JComboBox searchByDropdown = new JComboBox(attributes);
		sidebar.add(searchByDropdown);
		final JTextField textField = new JTextField();
		textField.setColumns(10);
		sidebar.add(textField);
		sidebar.add(new JLabel("Display:"));
		final JCheckBox[] checkboxes = new JCheckBox[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			checkboxes[i] = new JCheckBox(attributes[i]);
			checkboxes[i].setSelected(true);
			sidebar.add(checkboxes[i]);
		}
		JButton searchButton = new JButton("Search");
		sidebar.add(searchButton);
		add(sidebar, BorderLayout.WEST);
		
		final JTextArea resultTextArea = new JTextArea();
		add(resultTextArea, BorderLayout.EAST);
		
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
            		query.append(" FROM `Character` where " + searchBy + " = \"" + condition + "\";");
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
	}
}
