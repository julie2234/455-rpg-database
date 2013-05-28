package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ItemLevel extends JPanel implements ActionListener {

	public ItemLevel() {
		super();
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints fromLevelConst = new GridBagConstraints();
		fromLevelConst.anchor = GridBagConstraints.LINE_START;
		fromLevelConst.insets = new Insets(20, 10, 0, 0);
		fromLevelConst.gridx = 1;
		fromLevelConst.gridy = 0;
		JLabel fromLevel = new JLabel("Search for item from level");
		add(fromLevel, fromLevelConst);
		
		GridBagConstraints fromLevelFieldConst = new GridBagConstraints();
		fromLevelFieldConst.anchor = GridBagConstraints.LINE_START;
		fromLevelFieldConst.insets = new Insets(20, 10, 0, 0);
		fromLevelFieldConst.gridx = 2;
		fromLevelFieldConst.gridy = 0;
		JTextField fromLevelField = new JTextField(3);
		add(fromLevelField, fromLevelFieldConst);
		
		GridBagConstraints toLevelConst = new GridBagConstraints();
		toLevelConst.anchor = GridBagConstraints.LINE_START;
		toLevelConst.insets = new Insets(20, 10, 0, 0);
		toLevelConst.gridx = 3;
		toLevelConst.gridy = 0;
		JLabel toLevel = new JLabel("to level");
		add(toLevel, toLevelConst);
		
		GridBagConstraints toLevelFieldConst = new GridBagConstraints();
		toLevelFieldConst.anchor = GridBagConstraints.LINE_START;
		toLevelFieldConst.insets = new Insets(20, 10, 0, 10);
		toLevelFieldConst.gridx = 4;
		toLevelFieldConst.gridy = 0;
		JTextField toLevelField = new JTextField(3);
		add(toLevelField, toLevelFieldConst);
		
		GridBagConstraints searchButtonConst = new GridBagConstraints();
		searchButtonConst.anchor = GridBagConstraints.LINE_START;
		searchButtonConst.insets = new Insets(20, 20, 0, 100);
		searchButtonConst.gridx = 5;
		searchButtonConst.gridy = 0;
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(this);
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

	@Override
	public void actionPerformed(final ActionEvent the_arguments) {
		if (the_arguments.getActionCommand().equals("Search")) {
			// TODO Search for items that have a level within fromLevelField to toLevelField
		}
	}
}
