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
public class Fight extends JPanel {

	public Fight() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints attackerFieldConst = new GridBagConstraints();
		attackerFieldConst.anchor = GridBagConstraints.LINE_START;
		attackerFieldConst.insets = new Insets(20, 10, 0, 0);
		attackerFieldConst.gridx = 2;
		attackerFieldConst.gridy = 0;
		JTextField attackerField = new JTextField(10);
		add(attackerField, attackerFieldConst);
		
		GridBagConstraints vsConst = new GridBagConstraints();
		vsConst.anchor = GridBagConstraints.LINE_START;
		vsConst.insets = new Insets(20, 10, 0, 0);
		vsConst.gridx = 3;
		vsConst.gridy = 0;
		JLabel vs = new JLabel("vs.");
		add(vs, vsConst);
		
		GridBagConstraints defenderFieldConst = new GridBagConstraints();
		defenderFieldConst.anchor = GridBagConstraints.LINE_START;
		defenderFieldConst.insets = new Insets(20, 10, 0, 10);
		defenderFieldConst.gridx = 4;
		defenderFieldConst.gridy = 0;
		JTextField defenderField = new JTextField(10);
		add(defenderField, defenderFieldConst);
		
		GridBagConstraints fightButtonConst = new GridBagConstraints();
		fightButtonConst.anchor = GridBagConstraints.LINE_START;
		fightButtonConst.insets = new Insets(20, 20, 0, 100);
		fightButtonConst.gridx = 5;
		fightButtonConst.gridy = 0;
		JButton fightButton = new JButton("Fight!");
		add(fightButton, fightButtonConst);
		
		GridBagConstraints char1Const = new GridBagConstraints();
		char1Const.gridx = 2;
		char1Const.gridy = 1;
		JLabel char1 = new JLabel("(Attacking Character)");
		add(char1, char1Const);
		
		GridBagConstraints char2Const = new GridBagConstraints();
		char2Const.gridx = 4;
		char2Const.gridy = 1;
		JLabel char2 = new JLabel("(Defending Character)");
		add(char2, char2Const);
		
		GridBagConstraints resultsConst = new GridBagConstraints();
		resultsConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsConst.gridx = 0;
		resultsConst.gridy = 2;
		resultsConst.weighty = 1.0;
		resultsConst.weightx = 1.0;
		JLabel results = new JLabel("Results:");
		add(results, resultsConst);
		
		GridBagConstraints resultsTextAreaConst = new GridBagConstraints();
		resultsTextAreaConst.anchor = GridBagConstraints.LAST_LINE_START;
		resultsTextAreaConst.gridx = 0;
		resultsTextAreaConst.gridy = 3;
		resultsTextAreaConst.gridwidth = 7;
		resultsTextAreaConst.weightx = 1.0;
		final JTextArea resultsTextArea = new JTextArea(12, 40);
		JScrollPane scrollArea = new JScrollPane(resultsTextArea);
		add(scrollArea, resultsTextAreaConst);
		resultsTextArea.setEditable(false);
	}
	// TODO Compare attackerField with defenderField to see how much damage defenderField received from attackerField
}
