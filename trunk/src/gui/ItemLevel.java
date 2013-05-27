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
public class ItemLevel extends JPanel {

	public ItemLevel() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints searchCharConst = new GridBagConstraints();
		searchCharConst.anchor = GridBagConstraints.LINE_START;
		searchCharConst.insets = new Insets(20, 10, 0, 0);
		searchCharConst.gridx = 1;
		searchCharConst.gridy = 0;
		JLabel searchChar = new JLabel("Search for item from level");
		add(searchChar, searchCharConst);
		
		GridBagConstraints charFieldConst = new GridBagConstraints();
		charFieldConst.anchor = GridBagConstraints.LINE_START;
		charFieldConst.insets = new Insets(20, 10, 0, 0);
		charFieldConst.gridx = 2;
		charFieldConst.gridy = 0;
		JTextField charField = new JTextField(3);
		add(charField, charFieldConst);
		
		GridBagConstraints searchSpellConst = new GridBagConstraints();
		searchSpellConst.anchor = GridBagConstraints.LINE_START;
		searchSpellConst.insets = new Insets(20, 10, 0, 0);
		searchSpellConst.gridx = 3;
		searchSpellConst.gridy = 0;
		JLabel searchSpell = new JLabel("to level");
		add(searchSpell, searchSpellConst);
		
		GridBagConstraints spellFieldConst = new GridBagConstraints();
		spellFieldConst.anchor = GridBagConstraints.LINE_START;
		spellFieldConst.insets = new Insets(20, 10, 0, 10);
		spellFieldConst.gridx = 4;
		spellFieldConst.gridy = 0;
		JTextField spellField = new JTextField(3);
		add(spellField, spellFieldConst);
		
		GridBagConstraints searchButtonConst = new GridBagConstraints();
		searchButtonConst.anchor = GridBagConstraints.LINE_START;
		searchButtonConst.insets = new Insets(20, 20, 0, 100);
		searchButtonConst.gridx = 5;
		searchButtonConst.gridy = 0;
		JButton searchButton = new JButton("Search");
		add(searchButton, searchButtonConst);
		
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
