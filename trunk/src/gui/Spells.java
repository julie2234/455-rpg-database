package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Spells extends JPanel {

	public Spells() throws Exception {
		super();
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new GridBagLayout());
		
		GridBagConstraints searchByConst = new GridBagConstraints();
		searchByConst.insets = new Insets(20, 0, 0, 0);
		searchByConst.gridx = 0;
		searchByConst.gridy = 0;
		add(new JLabel("Search by:"), searchByConst);
		
		GridBagConstraints dropdownConst = new GridBagConstraints();
		dropdownConst.insets = new Insets(20, 5, 0, 0);
		dropdownConst.ipadx = 21;
		dropdownConst.gridwidth = 2;
		dropdownConst.gridx = 1;
		dropdownConst.gridy = 0;
		String[] dropdown = {"spellName", "magicType", "magikaCost"};
		final JComboBox searchByDropdown = new JComboBox(dropdown);
		add(searchByDropdown, dropdownConst);
		
		GridBagConstraints searchFieldConst = new GridBagConstraints();
		searchFieldConst.anchor = GridBagConstraints.LINE_START;
		searchFieldConst.insets = new Insets(20, 15, 0, 0);
		searchFieldConst.ipadx = 40;
		searchFieldConst.gridx = 3;
		searchFieldConst.gridy = 0;
		final JTextField textField = new JTextField();
		add(textField, searchFieldConst);
		textField.setColumns(10);
		
		GridBagConstraints searchButtonConst = new GridBagConstraints();
		searchButtonConst.anchor = GridBagConstraints.LINE_START;
		searchButtonConst.insets = new Insets(20, 10, 0, 0);
		searchButtonConst.weightx = 1.0;
		searchButtonConst.gridx = 6;
		searchButtonConst.gridy = 0;
		JButton searchButton = new JButton("Search");
		add(searchButton, searchButtonConst);
		
		GridBagConstraints resultsConst = new GridBagConstraints();
		resultsConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsConst.gridx = 0;
		resultsConst.gridy = 1;
		resultsConst.weighty = 1.0;
		add(new JLabel("Results:"), resultsConst);
		
		GridBagConstraints textAreaConst = new GridBagConstraints();
		textAreaConst.anchor = GridBagConstraints.LAST_LINE_START;
		textAreaConst.gridx = 0;
		textAreaConst.gridy = 2;
		textAreaConst.gridwidth = 7;
		final JTextArea resultTextArea = new JTextArea(12, 40);
		JScrollPane scrollArea = new JScrollPane(resultTextArea);
		add(scrollArea, textAreaConst);
		resultTextArea.setEditable(false);
		
		GridBagConstraints displayResultsConst = new GridBagConstraints();
		displayResultsConst.anchor = GridBagConstraints.LINE_START;
		displayResultsConst.insets = new Insets(0, 0, 0, 16);
		displayResultsConst.gridx = 7;
		displayResultsConst.gridy = 0;
		add(new JLabel("Display Results:"), displayResultsConst);
		
		GridBagConstraints checkBoxConst = new GridBagConstraints();
		checkBoxConst.anchor = GridBagConstraints.LINE_START;
		checkBoxConst.insets = new Insets(0, 0, 145, 16);
		checkBoxConst.gridx = 7;
		checkBoxConst.gridy = 1;
		checkBoxConst.gridheight = 3;
		checkBoxConst.ipady = 20;
		JPanel checkBoxPanel = new JPanel();
		String[] checkNames = {"spellName", "magicType", "magikaCost",
							   "spellPotency", "duration", "prerequisite"};
		final JCheckBox[] checkboxes = new JCheckBox[checkNames.length];
		checkBoxPanel.setLayout(new BoxLayout(checkBoxPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < checkNames.length; i++) {
			checkboxes[i] = new JCheckBox(checkNames[i], true);
			checkBoxPanel.add(checkboxes[i]);
		}
		add(checkBoxPanel, checkBoxConst);
		
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
            		query.append(" FROM Spells where " + searchBy + " = \"" + condition + "\";");
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