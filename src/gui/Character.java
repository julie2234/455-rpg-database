import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Character extends JPanel {
	private MainFrame frame;

	public Character(MainFrame the_frame) throws Exception {
		frame = the_frame;
		initialize();
	}
	
	public void initialize() throws Exception {
		add(new JLabel("Search by:"));
		String[] dropdown = {"characterID", "characterName"};
		final JComboBox searchBy = new JComboBox(dropdown);
		add(searchBy);
		final JTextField txt1 = new JTextField();
		add(txt1);
		txt1.setColumns(10);
		JButton search = new JButton("Search");
		add(search);
		search.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent the_event) {
            	String condition = (String) searchBy.getSelectedItem();
        		String condition2 = txt1.getText();
        		String result = "";
				try {
					result = frame.getDb().executeQuery("SELECT characterID, characterName FROM `Character` where " + condition + " = \"" + condition2 + "\";");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		JTextArea results = new JTextArea();
        		results.append(result);
        		add(results);
        		revalidate();
            }
        });
		
	}
}
