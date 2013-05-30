package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Castability extends JPanel {

	public Castability() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints searchCharConst = new GridBagConstraints();
		searchCharConst.insets = new Insets(20, 0, 0, 0);
		searchCharConst.gridx = 1;
		searchCharConst.gridy = 0;
		JLabel searchChar = new JLabel("Can");
		add(searchChar, searchCharConst);
		
		GridBagConstraints charFieldConst = new GridBagConstraints();
		charFieldConst.insets = new Insets(20, 10, 0, 0);
		charFieldConst.gridx = 2;
		charFieldConst.gridy = 0;
		final JTextField charField = new JTextField(10);
		add(charField, charFieldConst);
		
		GridBagConstraints searchSpellConst = new GridBagConstraints();
		searchSpellConst.anchor = GridBagConstraints.LINE_START;
		searchSpellConst.insets = new Insets(20, 10, 0, 0);
		searchSpellConst.gridx = 3;
		searchSpellConst.gridy = 0;
		JLabel searchSpell = new JLabel("cast");
		add(searchSpell, searchSpellConst);
		
		GridBagConstraints spellFieldConst = new GridBagConstraints();
		spellFieldConst.insets = new Insets(20, 10, 0, 0);
		spellFieldConst.gridx = 4;
		spellFieldConst.gridy = 0;
		final JTextField spellField = new JTextField(10);
		add(spellField, spellFieldConst);
		
		GridBagConstraints questionConst = new GridBagConstraints();
		questionConst.anchor = GridBagConstraints.LINE_START;
		questionConst.insets = new Insets(20, 0, 0, 0);
		questionConst.gridx = 5;
		questionConst.gridy = 0;
		JLabel question = new JLabel("?");
		add(question, questionConst);
		
		GridBagConstraints charConst = new GridBagConstraints();
		charConst.gridx = 2;
		charConst.gridy = 1;
		JLabel character = new JLabel("(Character)");
		add(character, charConst);
		
		GridBagConstraints spellConst = new GridBagConstraints();
		spellConst.gridx = 4;
		spellConst.gridy = 1;
		JLabel spell = new JLabel("(Spell)");
		add(spell, spellConst);
		
		GridBagConstraints checkButtonConst = new GridBagConstraints();
		checkButtonConst.insets = new Insets(20, 20, 0, 80);
		checkButtonConst.gridx = 6;
		checkButtonConst.gridy = 0;
		JButton checkButton = new JButton("Check");
		add(checkButton, checkButtonConst);
		
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
		//resultsTextArea.setEditable(false);
		
		checkButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String charID = charField.getText();
				String spell = spellField.getText();
				StringBuilder query1 = new StringBuilder("SELECT prerequisite FROM `Spell` WHERE spellName = \"");
				query1.append(spell + "\";");

				String prereq = "";
				try {
					prereq = Database.executeQueryString(query1.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q2 = new StringBuilder("SELECT magicType FROM `Spell` WHERE spellName = \"");
				q2.append(spell + "\";");
				
				String type = "";
				try {
					type = Database.executeQueryString(q2.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q3 = new StringBuilder("SELECT ");
				q3.append(prereq);
				q3.append(" FROM `SpellTree` WHERE magicType = \"");
				q3.append(type + "\" AND charID = ");
				q3.append(charID + ";");
				
				String canDo = "";
				try {
					canDo = Database.executeQueryString(q3.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder result = new StringBuilder("No\n\n");
				result.append(query1.toString() + "\n" + prereq + "\n\n");
				result.append(q2.toString() + "\n" + type + "\n\n");
				result.append(q3.toString() + "\n\n");
				
				if (canDo.equals("T")) {
					result.append("\n\nYes");
				}
				
				resultsTextArea.setText(result.toString());
				revalidate();
				
			}
		});
	}
	// TODO Check if spellField is a part of charField
}
