package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
//		charFieldConst.anchor = GridBagConstraints.LINE_START;
		charFieldConst.insets = new Insets(20, 10, 0, 0);
		charFieldConst.gridx = 2;
		charFieldConst.gridy = 0;
		JTextField charField = new JTextField(10);
		add(charField, charFieldConst);
		
		GridBagConstraints searchSpellConst = new GridBagConstraints();
		searchSpellConst.anchor = GridBagConstraints.LINE_END;
		searchSpellConst.insets = new Insets(20, 30, 0, 0);
		searchSpellConst.gridx = 3;
		searchSpellConst.gridy = 0;
		JLabel searchSpell = new JLabel("Cast");
		add(searchSpell, searchSpellConst);
		
		GridBagConstraints spellFieldConst = new GridBagConstraints();
//		spellFieldConst.anchor = GridBagConstraints.LINE_END;
		spellFieldConst.insets = new Insets(20, 10, 0, 80);
		spellFieldConst.gridx = 4;
		spellFieldConst.gridy = 0;
		JTextField spellField = new JTextField(10);
		add(spellField, spellFieldConst);
		
		GridBagConstraints charConst = new GridBagConstraints();
//		charConst.insets = new Insets(0, 80, 0, 0);
		charConst.gridx = 2;
		charConst.gridy = 1;
		JLabel character = new JLabel("(Character)");
		add(character, charConst);
		
		GridBagConstraints spellConst = new GridBagConstraints();
		spellConst.insets = new Insets(0, 0, 0, 80);
		spellConst.gridx = 4;
		spellConst.gridy = 1;
		JLabel spell = new JLabel("(Spell)");
		add(spell, spellConst);
		
		GridBagConstraints checkButtonConst = new GridBagConstraints();
		checkButtonConst.insets = new Insets(0, 20, 0, 40);
		checkButtonConst.gridx = 3;
		checkButtonConst.gridy = 2;
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
		resultsTextArea.setEditable(false);
	}
}
