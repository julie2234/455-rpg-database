package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class CharacterTab extends JPanel {
	
	public CharacterTab() throws Exception {
		super();
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		
		Character search = new Character();
		GenerateMonster generateMonster = new GenerateMonster();
		CreateCharacter createCharacter = new CreateCharacter();
		
		JTabbedPane tabPane = new JTabbedPane();
		add(tabPane, BorderLayout.CENTER);
		tabPane.addTab("Search", search);
		tabPane.addTab("Generate Monster", generateMonster);
		tabPane.addTab("Create Character", createCharacter);
		
	}
}

