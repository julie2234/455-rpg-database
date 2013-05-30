package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CreateCharacter extends JPanel {

	public CreateCharacter() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints searchCharConst = new GridBagConstraints();
		searchCharConst.anchor = GridBagConstraints.LINE_START;
		searchCharConst.insets = new Insets(20, 0, 0, 0);
		searchCharConst.gridx = 1;
		searchCharConst.gridy = 0;
		JLabel searchChar = new JLabel("Character name:");
		add(searchChar, searchCharConst);
		
		GridBagConstraints charFieldConst = new GridBagConstraints();
		charFieldConst.anchor = GridBagConstraints.LINE_START;
		charFieldConst.insets = new Insets(20, 10, 0, 0);
		charFieldConst.gridx = 2;
		charFieldConst.gridy = 0;
		final JTextField charField = new JTextField(10);
		add(charField, charFieldConst);
		
		GridBagConstraints searchSpellConst = new GridBagConstraints();
		searchSpellConst.anchor = GridBagConstraints.LINE_START;
		searchSpellConst.insets = new Insets(20, 0, 0, 0);
		searchSpellConst.gridx = 4;
		searchSpellConst.gridy = 0;
		JLabel searchSpell = new JLabel("Character race:");
		add(searchSpell, searchSpellConst);
		
		GridBagConstraints dropdownConst = new GridBagConstraints();
		dropdownConst.insets = new Insets(20, 10, 0, 40);
		dropdownConst.gridwidth = 2;
		dropdownConst.gridx = 5;
		dropdownConst.gridy = 0;
		String[] dropdown = {"High elf", "Argonian", "Wood elf", "Breton", "Dunmer", "Imperial", "Khajiit", "Nord", "Orc", "Redguard"};
		final JComboBox raceByDropdown = new JComboBox(dropdown);
		add(raceByDropdown, dropdownConst);
		
		GridBagConstraints createButtonConst = new GridBagConstraints();
		createButtonConst.anchor = GridBagConstraints.LINE_START;
		createButtonConst.insets = new Insets(20, 0, 0, 0);
		createButtonConst.gridx = 3;
		createButtonConst.gridy = 1;
		JButton createButton = new JButton("Create");
		add(createButton, createButtonConst);
		
		GridBagConstraints resultsConst = new GridBagConstraints();
		resultsConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsConst.gridx = 0;
		resultsConst.gridy = 3;
		resultsConst.weighty = 1.0;
		resultsConst.weightx = 1.0;
		JLabel results = new JLabel("Results:");
		add(results, resultsConst);
		
		GridBagConstraints resultsTextAreaConst = new GridBagConstraints();
		resultsTextAreaConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsTextAreaConst.gridx = 0;
		resultsTextAreaConst.gridy = 4;
		resultsTextAreaConst.gridwidth = 7;
		resultsTextAreaConst.weightx = 1.0;
		final JTextArea resultsTextArea = new JTextArea(12, 40);
		JScrollPane scrollArea = new JScrollPane(resultsTextArea);
		add(scrollArea, resultsTextAreaConst);
		resultsTextArea.setEditable(false);
		
		createButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	//INSERT INTO `Character` (characterName, characterLevel, race, playerBoolean, backpackID, HP, MP, stamina, experience) 
            	//VALUES('Ainsley', 3, 'Giant', 'T', 'B1009', 100, 100, 100, 100);
            	StringBuilder query = new StringBuilder("INSERT INTO `Character` VALUES(");
            	query.append("'" + charField.getText() + "', 'T', ");
            	query.append("'" + (String) raceByDropdown.getSelectedItem() + "', 1, 0, 100, 100, 100");
            	
            	String result = "";
        		
				try {
					JTable table = Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
        		resultsTextArea.setText(result);
            	revalidate();
            }
        });
		
	}
	// TODO Add a tuple to Character with characterName of charField and race of raceByDropdown
}
