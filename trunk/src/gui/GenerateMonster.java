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
public class GenerateMonster extends JPanel {

	public GenerateMonster() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints genMonsterConst = new GridBagConstraints();
		genMonsterConst.anchor = GridBagConstraints.LINE_START;
		genMonsterConst.insets = new Insets(20, 10, 0, 0);
		genMonsterConst.gridx = 1;
		genMonsterConst.gridy = 0;
		JLabel genMonster = new JLabel("Generate monster at level");
		add(genMonster, genMonsterConst);
		
		GridBagConstraints monsterFieldConst = new GridBagConstraints();
		monsterFieldConst.anchor = GridBagConstraints.LINE_START;
		monsterFieldConst.insets = new Insets(20, 10, 0, 0);
		monsterFieldConst.gridx = 2;
		monsterFieldConst.gridy = 0;
		JTextField monsterField = new JTextField(3);
		add(monsterField, monsterFieldConst);
		
		GridBagConstraints genButtonConst = new GridBagConstraints();
		genButtonConst.anchor = GridBagConstraints.LINE_START;
		genButtonConst.insets = new Insets(20, 20, 0, 140);
		genButtonConst.gridx = 5;
		genButtonConst.gridy = 0;
		JButton genButton = new JButton("Generate");
		add(genButton, genButtonConst);
		
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
