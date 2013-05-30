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
public class Spells extends JPanel {
	private String[] attributes = {"spellName", "magicType", "magikaCost", "spellPotency",
			   "duration", "prerequisite"};
	
	public Spells() throws Exception {
		super();
		initialize();
	}

	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		Border etched = BorderFactory.createEtchedBorder();

		// create search panel
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(0,1));
		searchPanel.setBorder(BorderFactory.createTitledBorder(etched, "Search for:"));
		final JComboBox searchByDropdown = new JComboBox(attributes);
		searchPanel.add(searchByDropdown);
		final JTextField textField = new JTextField();
		textField.setColumns(10);
		searchPanel.add(textField);
		
		// create checkboxes
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(0,1));
		displayPanel.setBorder(BorderFactory.createTitledBorder(etched, "Display:"));
		final JCheckBox[] checkboxes = new JCheckBox[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			checkboxes[i] = new JCheckBox(attributes[i]);
			if (i < 4)
				checkboxes[i].setSelected(true);
			displayPanel.add(checkboxes[i]);
		}
		
		// create results area
		final JPanel resultsPanel = new JPanel();
		
		// create buttons
		final JButton searchButton = new JButton("Search");
		final JButton deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String searchBy = (String) searchByDropdown.getSelectedItem();
				String condition = textField.getText();
				searchButton.doClick();
				String msg = "Are you sure you wish to delete all of the searched results?";
				String title = "Confirm Delete";
				int confirm = JOptionPane.showConfirmDialog(null, msg,
						title, JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					StringBuilder query = new StringBuilder(
							"DELETE FROM `Spell` where ");
					query.append(searchBy + " = \"" + condition + "\";");

					try {
						Database.executeUpdate(query.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					searchButton.doClick();
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String searchBy = (String) searchByDropdown.getSelectedItem();
				String condition = textField.getText();
				StringBuilder query = new StringBuilder("SELECT ");
				for (JCheckBox box : checkboxes) {
					if (box.isSelected())
						query.append(box.getText() + ", ");
				}
				if (query.substring(query.length() - 2, query.length()).equals(
						", ")) {
					query.delete(query.length() - 2, query.length());
					query.append(" FROM `Spell` where " + searchBy
							+ " = \"" + condition + "\";");
					JTable table = null;
					try {
						table = Database.executeQuery(query.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					JScrollPane scrollArea = new JScrollPane(table);
					resultsPanel.removeAll();
					resultsPanel.add(scrollArea);
					revalidate();
					deleteButton.setEnabled(true);
				}
			}
		});
		
		// add everything to the panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		sidebar.add(searchPanel, new GBC(0,0,1,2));
		sidebar.add(displayPanel, new GBC(0,2,1,5).setFill(GBC.HORIZONTAL));
		sidebar.add(searchButton, new GBC(0,7,1,1).setFill(GBC.HORIZONTAL));
		sidebar.add(deleteButton, new GBC(0,8,1,1).setFill(GBC.HORIZONTAL));
		add(sidebar, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.EAST);
	}
}


/*import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		final JButton searchButton = new JButton("Search");
		add(searchButton, searchButtonConst);
		
		GridBagConstraints deleteButtonConst = new GridBagConstraints();
		deleteButtonConst.insets = new Insets(40, 0, 0, 0);
		deleteButtonConst.gridx = 3;
		deleteButtonConst.gridy = 1;
		JButton deleteButton = new JButton("Delete All");
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
            		query.append(" FROM Spell where " + searchBy + " = \"" + condition + "\";");
    				JTable table = null;
            		try {
    					table = Database.executeQuery(query.toString());
    				} catch (Exception e) {
    					e.printStackTrace();
    				}
    				JFrame frame = new JFrame();
            		frame.add(table);
            		frame.pack();
            		frame.setVisible(true);
            		resultTextArea.setText(result);
            		revalidate();
        		} else {
            		resultTextArea.setText("Please select at least one checkbox.");
        		}
            }
        });
		
		// action listener for delete button
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String searchBy = (String) searchByDropdown.getSelectedItem();
				String condition = textField.getText();

				searchButton.doClick();
				String msg = "Are you sure you wish to delete all of the searched results?";
				String title = "Confirm Delete";
				int confirm = JOptionPane.showConfirmDialog(null, msg,
						title, JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					StringBuilder query = new StringBuilder(
							"DELETE FROM Spell where ");
					query.append(searchBy + " = \"" + condition + "\";");

					try {
						Database.executeUpdate(query.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					searchButton.doClick();
				}
			}
		});
	}
}*/