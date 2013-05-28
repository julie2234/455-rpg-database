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
public class Spells extends JPanel implements ActionListener {

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
		dropdownConst.ipadx = 16;
		dropdownConst.gridwidth = 2;
		dropdownConst.gridx = 1;
		dropdownConst.gridy = 0;
		String[] dropdown = {"spellName", "magicType", "magikaCost",
							 "spellPotency", "duration", "prerequisite"};
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
		searchButton.addActionListener(this);
		add(searchButton, searchButtonConst);
		
		GridBagConstraints deleteButtonConst = new GridBagConstraints();
//		deleteButtonConst.anchor = GridBagConstraints.LINE_START;
		deleteButtonConst.insets = new Insets(40, 0, 0, 0);
//		deleteButtonConst.weightx = 1.0;
		deleteButtonConst.gridx = 3;
		deleteButtonConst.gridy = 1;
		JButton deleteButton = new JButton("Delete All");
		deleteButton.addActionListener(this);
		add(deleteButton, deleteButtonConst);
		
		GridBagConstraints resultsConst = new GridBagConstraints();
		resultsConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsConst.gridx = 0;
		resultsConst.gridy = 2;
		resultsConst.weighty = 1.0;
		add(new JLabel("Results:"), resultsConst);
		
		GridBagConstraints textAreaConst = new GridBagConstraints();
		textAreaConst.anchor = GridBagConstraints.LAST_LINE_START;
		textAreaConst.gridx = 0;
		textAreaConst.gridy = 3;
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
/*		searchButton.addActionListener(new ActionListener() {
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
    					e.printStackTrace();
    				}
            		resultTextArea.setText(result);
            		revalidate();
        		} else {
            		resultTextArea.setText("Please select at least one checkbox.");
        		}
            }
        });*/
	}
	
	@Override
	public void actionPerformed(final ActionEvent the_arguments) {
		if (the_arguments.getActionCommand().equals("Search")) {
			// TODO Add database search based on searchByDropdown and checkboxes
			
		} else if (the_arguments.getActionCommand().equals("Delete All")) {
			// TODO Delete all of the results of the last search
			
		}
	}
}