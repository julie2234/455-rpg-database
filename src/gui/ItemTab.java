package gui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class ItemTab extends JPanel {
	
	public ItemTab() throws Exception {
		super();
		initialize();
	}
	
	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		ItemLevel itemLevel = new ItemLevel();
		Items search = new Items();
		JTabbedPane tabPane = new JTabbedPane();
		add(tabPane, BorderLayout.CENTER);
		tabPane.addTab("Search", search);
		tabPane.addTab("Search Range", itemLevel);
	}
}

