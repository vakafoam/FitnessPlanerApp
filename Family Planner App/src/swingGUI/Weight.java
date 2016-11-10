package swingGUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Weight {
	private JFrame frame;
	private Container pane;
	private JPanel panel, panel2, panel3;
	private JButton updateBtn, backBtn;
	private JLabel label, label2, label3;
	private JTextField weightTxt;
	
	private void initElems() {
		frame = new JFrame("My weight");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		panel = new JPanel(); panel.setAlignmentX(Component.CENTER_ALIGNMENT);
		label = new JLabel("Your current weight is: "); 
		label2 = new JLabel("180 lbs");
		updateBtn = new JButton("Update"); 
				
		panel.add(Box.createRigidArea(new Dimension(0,10))); 
		panel.add(label); panel.add(label2); 
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		label3 = new JLabel("Update weigt: ");
		weightTxt = new JTextField(5);
		panel2 = new JPanel();
		panel2.add(Box.createRigidArea(new Dimension(0,50)));
		panel2.add(label3); panel2.add(weightTxt); panel2.add(updateBtn);
		
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

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Weight();
			}
		});

	}
}
