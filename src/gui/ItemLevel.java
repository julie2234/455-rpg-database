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
public class ItemLevel extends JPanel {
	
	public ItemLevel() throws Exception {
		super();
		initialize();
	}

	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		Border etched = BorderFactory.createEtchedBorder();

		// create search panel
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(0,1));
		searchPanel.setBorder(BorderFactory.createTitledBorder(etched, "Item level:"));
		final JTextField levelField = new JTextField();
		levelField.setColumns(10);
		searchPanel.add(levelField);
		
		// create results area
		final JPanel resultsPanel = new JPanel();
		
		// create buttons
		final JButton generateButton = new JButton("Generate");
		generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	String lvl = levelField.getText();
            	
            	String query = "SELECT * FROM `Item` where itemLevel = " 
            					+ lvl + " ORDER BY RAND() LIMIT 1;";
            	try {
    				JTable table = Database.executeQuery(query.toString());
    				JScrollPane scrollArea = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					resultsPanel.removeAll();
					resultsPanel.add(scrollArea);
					revalidate();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }});
		
		// add everything to the panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		sidebar.add(searchPanel, new GBC(0,0,1,2));
		sidebar.add(generateButton, new GBC(0,3,1,1).setFill(GBC.HORIZONTAL));
		add(sidebar, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.EAST);
	}
}

/*import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
		
		GridBagConstraints fromLevelConst = new GridBagConstraints();
		fromLevelConst.anchor = GridBagConstraints.LINE_START;
		fromLevelConst.insets = new Insets(20, 10, 0, 0);
		fromLevelConst.gridx = 1;
		fromLevelConst.gridy = 0;
		JLabel fromLevel = new JLabel("Generate item at level");
		add(fromLevel, fromLevelConst);
		
		GridBagConstraints fromLevelFieldConst = new GridBagConstraints();
		fromLevelFieldConst.anchor = GridBagConstraints.LINE_START;
		fromLevelFieldConst.insets = new Insets(20, 10, 0, 0);
		fromLevelFieldConst.gridx = 2;
		fromLevelFieldConst.gridy = 0;
		final JTextField levelField = new JTextField(3);
		add(levelField, fromLevelFieldConst);
		
		GridBagConstraints searchButtonConst = new GridBagConstraints();
		searchButtonConst.anchor = GridBagConstraints.LINE_START;
		searchButtonConst.insets = new Insets(20, 20, 0, 140);
		searchButtonConst.gridx = 5;
		searchButtonConst.gridy = 0;
		JButton genButton = new JButton("Generate");
		add(genButton, searchButtonConst);
		
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
		
		genButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	String lvl = levelField.getText();
            	
            	String query = "SELECT * FROM `Item` where itemLevel = " 
            					+ lvl + " ORDER BY RAND() LIMIT 1;";
            	
            	String result = "";
        		JTable table = null;
            	try {
    				table = Database.executeQuery(query.toString());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
        		resultsTextArea.setText(result);
        		JFrame frame = new JFrame();
        		frame.add(table);
        		frame.pack();
        		frame.setVisible(true);
        		revalidate();
            }
        });
	}
}*/
