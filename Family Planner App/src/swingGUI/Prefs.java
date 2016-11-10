package swingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Prefs {
	
	private JFrame frame;
	private JPanel mainMealPan, mainExercPan, mealBoxPan, checkMealPan, 
	checkExercPan, exercBoxPan, addMealPan, addExercPan, doneMealPan, doneExercPan;
	private JTabbedPane pane;
	private JTextField newMeal, newExerc;
	private JLabel enterMeal, enterExerc;
	private JButton addMealBtn, addExercBtn, doneMealBtn, doneExercBtn, backBtn1, backBtn2;
	
	
	public Prefs() {
		initElems();
	}
	
	private void initElems() {
		pane = new JTabbedPane();
		
		addMealPrefs();
		addExercPrefs();
		
		pane.addTab("Meal Preferences", mainMealPan);
		pane.addTab("Exercise preferences", mainExercPan);
		
		frame = new JFrame ("My preferences");
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addMealPrefs() {
		mainMealPan = new JPanel();
		mealBoxPan = new JPanel();
		checkMealPan = new JPanel();
		addMealPan = new JPanel();
		doneMealPan = new JPanel();
		
		// Structure: mainMealPan [mealBoxPan [checkMealPan, addMealPan], doneMealPan]
				
		createMealCheckBoxes();
		
		//JScrollPane listScroller = new JScrollPane();
		//mealBoxPan.setPreferredSize(new Dimension(500, 200));
		checkMealPan.setLayout(new BoxLayout(checkMealPan, BoxLayout.Y_AXIS));
		checkMealPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		mealBoxPan.setLayout(new BorderLayout());
		checkMealPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in/out meals"));
		mainMealPan.setLayout(new BorderLayout());
		
		addMealPan.setLayout(new BorderLayout(5,20)); 
		enterMeal = new JLabel ("Add a meal: "); addMealPan.add(enterMeal, BorderLayout.WEST);
		newMeal = new JTextField(10); addMealPan.add(newMeal, BorderLayout.CENTER);
		addMealBtn = new JButton("Add"); addMealPan.add(addMealBtn, BorderLayout.EAST);
		
		doneMealPan.setBackground(Color.WHITE);
		doneMealBtn = new JButton("Done");
		backBtn1 = new JButton("<< Back"); backBtn1.setBackground(Color.WHITE);
		doneMealPan.add(backBtn1); doneMealPan.add(doneMealBtn);

		mealBoxPan.add(checkMealPan, BorderLayout.CENTER); mealBoxPan.add(addMealPan, BorderLayout.SOUTH); 
		mainMealPan.add(mealBoxPan, BorderLayout.NORTH); mainMealPan.add(doneMealPan, BorderLayout.SOUTH);
	}
	
	private void createMealCheckBoxes() {
		JCheckBox check1 = new JCheckBox("Baked Potatoes"); check1.setSelected(true); checkMealPan.add(check1);
		JCheckBox check2 = new JCheckBox("Roasted Beef"); check2.setSelected(true); checkMealPan.add(check2);
		JCheckBox check3 = new JCheckBox("Fish"); check3.setSelected(true); checkMealPan.add(check3);
		JCheckBox check4 = new JCheckBox("Desert"); check4.setSelected(true); checkMealPan.add(check4);
		JCheckBox check5 = new JCheckBox("Rice"); check5.setSelected(true); checkMealPan.add(check5);
		
	}
	
	private void addExercPrefs() {
		mainExercPan = new JPanel();
		exercBoxPan = new JPanel();
		checkExercPan = new JPanel();
		addExercPan = new JPanel();
		doneExercPan = new JPanel();
				
		// Structure: mainExercPan [exercBoxPan [checkExercPan, addExercPan], doneExercPan]
		
		createExercCheckBoxes();
		
		//JScrollPane listScroller = new JScrollPane();
		//mealBoxPan.setPreferredSize(new Dimension(500, 200));
		checkExercPan.setLayout(new BoxLayout(checkExercPan, BoxLayout.Y_AXIS));
		checkExercPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exercBoxPan.setLayout(new BorderLayout());
		checkExercPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in/out exercises"));
		mainExercPan.setLayout(new BorderLayout());
		
		addExercPan.setLayout(new BorderLayout(5,20));
		enterExerc = new JLabel ("Add a meal: "); addExercPan.add(enterExerc, BorderLayout.WEST);
		newExerc = new JTextField(10); addExercPan.add(newExerc, BorderLayout.CENTER);
		addExercBtn = new JButton("Add"); addExercPan.add(addExercBtn, BorderLayout.EAST);
		
		doneExercPan.setBackground(Color.WHITE);
		doneExercBtn = new JButton("Done");
		backBtn2 = new JButton("<< Back"); backBtn2.setBackground(Color.WHITE);
		doneExercPan.add(backBtn2); doneExercPan.add(doneExercBtn);

		exercBoxPan.add(checkExercPan, BorderLayout.CENTER); exercBoxPan.add(addExercPan, BorderLayout.SOUTH); 
		mainExercPan.add(exercBoxPan, BorderLayout.NORTH); mainExercPan.add(doneExercPan, BorderLayout.SOUTH);
	}
	
	private void createExercCheckBoxes() {
		JCheckBox check1 = new JCheckBox("Jogging"); check1.setSelected(true); checkExercPan.add(check1);
		JCheckBox check2 = new JCheckBox("Power Lifting"); check2.setSelected(true); checkExercPan.add(check2);
		JCheckBox check3 = new JCheckBox("Yoga"); check3.setSelected(true); checkExercPan.add(check3);
		JCheckBox check4 = new JCheckBox("Shaping"); check4.setSelected(true); checkExercPan.add(check4);
		JCheckBox check5 = new JCheckBox("Swimming"); check5.setSelected(true); checkExercPan.add(check5);
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Prefs();
			}
		});

	}
}
