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
import javax.swing.SwingUtilities;

public class Options {
	private JFrame frame;
	private Container pane;
	private JPanel panel, panel2;
	private JButton plansBtn, prefsBtn, weightBtn, MealPlannerBtn, ExercisePlannerBtn,backBtn;
	private JLabel label;
	
	private void initElems() {
		frame = new JFrame("Choose an option");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pane = frame.getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		panel = new JPanel();
		label = new JLabel("Please select an option to proceed"); label.setAlignmentX(Component.CENTER_ALIGNMENT);
		plansBtn = new JButton("View My Plans"); plansBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
				
		prefsBtn = new JButton("My Preferences"); prefsBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		weightBtn = new JButton("My Weight"); weightBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		MealPlannerBtn = new JButton("Plan Meals");
		ExercisePlannerBtn = new JButton("Plan Exercises");
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		
		panel.add(Box.createRigidArea(new Dimension(0,10))); 
		panel.add(label); panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.add(plansBtn); panel.add(prefsBtn); panel.add(weightBtn); 
		panel.add(MealPlannerBtn); panel.add(ExercisePlannerBtn);
		panel.add(Box.createRigidArea(new Dimension(0,10)));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel2 = new JPanel();
		panel2.add(Box.createRigidArea(new Dimension(0,50)));
		panel2.add(backBtn); panel2.add(MealPlannerBtn); panel2.add(ExercisePlannerBtn); 
		panel2.setBackground(Color.white);

		pane.add(panel);
		pane.add(panel2);
		frame.pack();
		frame.setVisible(true);
	}

	public Options() {
		initElems();
	}

	
}
