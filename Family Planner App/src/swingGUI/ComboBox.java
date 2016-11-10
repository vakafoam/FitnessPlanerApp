package swingGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ComboBox {
	private JFrame frame;
	private JPanel panel;
	private JComboBox<String> comboBox;
	JLabel label;
	String userType = null; // change the default, if user doesn't choose anything
	DefaultComboBoxModel<String> model; //to add/remove items from comboBox
	JTextField text;
	JButton addBtn;
	JButton removeBtn;	
	String selectedVal;
	
	public ComboBox() {
		frame = new JFrame();
		panel = new JPanel();
		comboBox = new JComboBox<>();
		label = new JLabel("I am a: ");
		
		// Not necessary if fields are unchangable
		model = new DefaultComboBoxModel<>();
		comboBox.setModel(model);
		
		comboBox.addItem("Simple User");
		comboBox.addItem("Meal Planner");
		comboBox.addItem("Personal Trainer");
		comboBox.addItemListener(new ItemListener() {

		public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					userType = (String) e.getItem();
					selectedVal = model.getSelectedItem().toString();
				}
			}
		});
		
		text = new JTextField(15);
		addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.addElement(text.getText());				
			}
		});
		removeBtn = new JButton("Remove");
		removeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.removeElement(selectedVal);
			}
		});
		
		
		panel.add(label);
		panel.add(comboBox);
		panel.add(text);
		panel.add(addBtn);
		panel.add(removeBtn);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ComboBox();
			}
		});
	}

}
