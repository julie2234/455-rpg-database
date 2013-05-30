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
public class CreateCharacter extends JPanel {
	
	public CreateCharacter() throws Exception {
		super();
		initialize();
	}

	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		Border etched = BorderFactory.createEtchedBorder();

		// create name panel
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new GridLayout(0,1));
		namePanel.setBorder(BorderFactory.createTitledBorder(etched, "Character name:"));
		final JTextField nameField = new JTextField();
		nameField.setColumns(10);
		namePanel.add(nameField);
		
		// create race panel
		JPanel racePanel = new JPanel();
		racePanel.setLayout(new GridLayout(0,1));
		racePanel.setBorder(BorderFactory.createTitledBorder(etched, "Character race:"));
		String[] dropdown = {"High elf", "Argonian", "Wood elf", "Breton", "Dunmer", "Imperial", "Khajiit", "Nord", "Orc", "Redguard"};
		final JComboBox raceByDropdown = new JComboBox(dropdown);
		racePanel.add(raceByDropdown);
		
		// create results area
		final JPanel resultsPanel = new JPanel();
		
		// create buttons
		final JButton createButton = new JButton("Create");
		createButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	//INSERT INTO `Character` (characterName, characterLevel, race, playerBoolean, backpackID, HP, MP, stamina, experience) 
            	//VALUES('Ainsley', 3, 'Giant', 'T', 'B1009', 100, 100, 100, 100);
            	StringBuilder query = new StringBuilder("INSERT INTO `Character` ");
            	query.append("(characterName, characterLevel, race, playerBoolean, HP, MP, stamina, experience)");
            	query.append(" VALUES(");
            	query.append("'" + nameField.getText() + "', 1, ");
            	query.append("'" + (String) raceByDropdown.getSelectedItem() + "', 'T', 100, 100, 100, 0");
				try {
					JTable table = Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q2 = new StringBuilder("SELECT characterID FROM `Character` WHERE characterName = \"");
				q2.append(nameField.getText() + "\"");
				
				String charID = "";
				try {
					charID = Database.executeQueryString(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q3 = new StringBuilder("INSERT INTO `Backpack` ");
				q3.append("(characterID) VALUES(");
				q3.append(charID + ");");
				
				try {
					JTable table = Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q4 = new StringBuilder("INSERT INTO `Spell Tree` ");
				q4.append("(CharacterID, magicType, novice, apprentice, adept, expert, master) VALUES(");
				q4.append(charID + "'Destruction', 'F', 'F', 'F', 'F', 'F');");
				
				try {
					JTable table = Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q5 = new StringBuilder("INSERT INTO `Spell Tree` ");
				q4.append("(CharacterID, magicType, novice, apprentice, adept, expert, master) VALUES(");
				q4.append(charID + "'Restoration', 'F', 'F', 'F', 'F', 'F');");
				
				try {
					Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q6 = new StringBuilder("INSERT INTO `Spell Tree` ");
				q4.append("(CharacterID, magicType, novice, apprentice, adept, expert, master) VALUES(");
				q4.append(charID + "'Alteration', 'F', 'F', 'F', 'F', 'F');");
				
				try {
					Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				StringBuilder q7 = new StringBuilder("SELECT * FROM `Character`");
				JTable table = null;
				try {
					table = Database.executeQuery(query.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				JScrollPane scrollArea = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				resultsPanel.removeAll();
				resultsPanel.add(scrollArea);
				revalidate();
				
            	revalidate();
            }
        });
		
		// add everything to the panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		sidebar.add(namePanel, new GBC(0,0,1,2));
		sidebar.add(racePanel, new GBC(0,2,1,5).setFill(GBC.HORIZONTAL));
		sidebar.add(createButton, new GBC(0,7,1,1).setFill(GBC.HORIZONTAL));
		add(sidebar, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.EAST);
	}
}
