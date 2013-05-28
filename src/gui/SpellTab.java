package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class SpellTab extends JPanel {
	
	public SpellTab() throws Exception {
		super();
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		
		Spells search = new Spells();
		Castability castability = new Castability();
		
		JTabbedPane tabPane = new JTabbedPane();
		add(tabPane, BorderLayout.CENTER);
		tabPane.addTab("Search", search);
		tabPane.addTab("Castability", castability);
	}
}

