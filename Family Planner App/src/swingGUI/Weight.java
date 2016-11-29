package swingGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import FMlogic.App;
import dataStorage.Data;

public class Weight {
	private JFrame frame;
	private Container pane;
	private JPanel panel, panel2, panel3;
	private JButton updateBtn, backBtn;
	private JLabel label, label2, label3, label4, label5;
	private JTextField weightTxt, goalWeightTxt;
	
	private void initElems() {
		frame = new JFrame("My weight");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		panel = new JPanel(); panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		label = new JLabel("Your current weight is: "); 
		double weight = App.getCurrentUser().getWeight();
		label2 = new JLabel(weight + " lbs");
		label4 = new JLabel("Your goal weight is: " + App.getCurrentUser().getGoalWeight() + " lbs");
						
		panel.add(Box.createRigidArea(new Dimension(0,10))); 
		panel.add(label); panel.add(label2); panel.add(label4); 
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		label3 = new JLabel("Update weight: ");
		weightTxt = new JTextField(5);
		panel2 = new JPanel();
		panel2.add(Box.createRigidArea(new Dimension(0,50)));
		updateBtn = new JButton("Update"); 
		updateBtn.addActionListener(new UpdateEvent());
		panel2.add(label3); panel2.add(weightTxt);
		
		label5 = new JLabel("Update your goal weight: ");
		goalWeightTxt = new JTextField(5);
		panel2.add(label5); panel2.add(goalWeightTxt); panel2.add(updateBtn);
		
		panel3 = new JPanel();
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		panel3.add(backBtn); 
		panel3.setBackground(Color.white);

		pane.add(panel); pane.add(panel2); pane.add(panel3);
		frame.pack();
		frame.setVisible(true);
	}

	public Weight() {
		initElems();
	}
	
	private class UpdateEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			double oldWeight = App.getCurrentUser().getWeight();
			try {
				double weight = Double.parseDouble(weightTxt.getText());
				
				App.getCurrentUser().setWeight(weight);      // update the weight of the current user
				if (!goalWeightTxt.getText().equals("")) {
					double goal = Double.parseDouble(goalWeightTxt.getText());
					App.getCurrentUser().setGoalWeight(goal);
				}
				Data.updateWeightFile(oldWeight, weight);    // write the update to a file
				frame.dispose();
				
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Bad input! Please try again", 
						"Enter a valid weight", JOptionPane.ERROR_MESSAGE);
				weightTxt.setText("");
			}		
		}	
	}
}
