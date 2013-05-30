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
public class Items extends JPanel {
	private String[] attributes = {"itemID", "itemName", "itemType", "itemLevel", "cost", "weight", "perk", "defense", "upgrade", "damage", "duration", "potency"};
	
	public Items() throws Exception {
		super();
		initialize();
	}

	private void initialize() throws Exception {
		setLayout(new BorderLayout());
		Border etched = BorderFactory.createEtchedBorder();

		// create search panel
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridLayout(0,1));
		searchPanel.setBorder(BorderFactory.createTitledBorder(etched, "Search for:"));
		final JComboBox searchByDropdown = new JComboBox(attributes);
		searchPanel.add(searchByDropdown);
		final JTextField textField = new JTextField();
		textField.setColumns(10);
		searchPanel.add(textField);
		
		// create checkboxes
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new GridLayout(0,1));
		displayPanel.setBorder(BorderFactory.createTitledBorder(etched, "Display:"));
		final JCheckBox[] checkboxes = new JCheckBox[attributes.length];
		for (int i = 0; i < attributes.length; i++) {
			checkboxes[i] = new JCheckBox(attributes[i]);
			checkboxes[i].setSelected(true);
			displayPanel.add(checkboxes[i]);
		}
		
		// create results area
		final JPanel resultsPanel = new JPanel();
		
		// create buttons
		final JButton searchButton = new JButton("Search");
		final JButton viewAllButton = new JButton("View All");
		final JButton deleteButton = new JButton("Delete");
		deleteButton.setEnabled(false);
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String searchBy = (String) searchByDropdown.getSelectedItem();
				String condition = textField.getText();
				searchButton.doClick();
				String msg = "Are you sure you wish to delete all of the searched results?";
				String title = "Confirm Delete";
				int confirm = JOptionPane.showConfirmDialog(null, msg,
						title, JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					StringBuilder query = new StringBuilder(
							"DELETE FROM `Item` where ");
					query.append(searchBy + " = \"" + condition + "\";");

					try {
						Database.executeUpdate(query.toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
					searchButton.doClick();
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				String searchBy = (String) searchByDropdown.getSelectedItem();
				String condition = textField.getText();
				StringBuilder query = new StringBuilder("SELECT ");
				for (JCheckBox box : checkboxes) {
					if (box.isSelected())
						query.append(box.getText() + ", ");
				}
				if (query.substring(query.length() - 2, query.length()).equals(
						", ")) {
					query.delete(query.length() - 2, query.length());
					query.append(" FROM `Item` where " + searchBy
							+ " = \"" + condition + "\";");
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
					deleteButton.setEnabled(true);
				}
			}
		});
		
		viewAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent the_event) {
				StringBuilder query = new StringBuilder("SELECT ");
				for (JCheckBox box : checkboxes) {
					if (box.isSelected())
						query.append(box.getText() + ", ");
				}
				if (query.substring(query.length() - 2, query.length()).equals(
						", ")) {
					query.delete(query.length() - 2, query.length());
					query.append(" FROM `Item`;");
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
					deleteButton.setEnabled(true);
				}
			}
		});
		
		// add everything to the panel
		JPanel sidebar = new JPanel();
		sidebar.setLayout(new GridBagLayout());
		sidebar.add(searchPanel, new GBC(0,0,1,2));
		sidebar.add(displayPanel, new GBC(0,2,1,5).setFill(GBC.HORIZONTAL));
		sidebar.add(searchButton, new GBC(0,7,1,1).setFill(GBC.HORIZONTAL));
		sidebar.add(viewAllButton, new GBC(0,8,1,1).setFill(GBC.HORIZONTAL));
		sidebar.add(deleteButton, new GBC(0,9,1,1).setFill(GBC.HORIZONTAL));
		add(sidebar, BorderLayout.WEST);
		add(resultsPanel, BorderLayout.EAST);
	}
}

