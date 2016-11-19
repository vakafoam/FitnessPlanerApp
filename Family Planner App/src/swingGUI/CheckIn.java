package swingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;


public class CheckIn {
	
	private JFrame frame;
	private JPanel mainPan, mainMealPan, mainExercPan, mealBoxPan, checkMealPan, 
	checkExercPan, exercBoxPan, rateMealPan, rateExercPan, donePan;
	private JTabbedPane pane;
	private JButton rateMealBtn, rateExercBtn, doneBtn, backBtn;
	private List<String> mealsConsumed = new ArrayList<>();
	private List<String> exercConsumed = new ArrayList<>();
	
	
	public CheckIn() {
		initElems();
	}
	
	private void initElems() {
		mainPan = new JPanel();
		pane = new JTabbedPane();
		donePan = new JPanel();
		
		// Structure: mainPan [pane [meals, exercises], donePan]
		
		addMealPrefs();
		addExercPrefs();
		
		pane.addTab("Meal CheckIn", mainMealPan);
		pane.addTab("Exercise CheckIn", mainExercPan);
		
		donePan.setBackground(Color.WHITE);
		doneBtn = new JButton("Done");
		doneBtn.addActionListener(new DoneEvent());
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		backBtn.addActionListener(new BackEvent());
		donePan.add(backBtn); donePan.add(doneBtn);
		
		mainPan.setLayout(new BorderLayout());
		mainPan.add(pane, BorderLayout.CENTER); mainPan.add(donePan, BorderLayout.PAGE_END);
		frame = new JFrame ("Check items");
		frame.add(mainPan);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addMealPrefs() {
		mainMealPan = new JPanel();
		mealBoxPan = new JPanel();
		checkMealPan = new JPanel();
		rateMealPan = new JPanel();
				
		// Structure: mainMealPan [mealBoxPan [checkMealPan, rateMealPan]]
				
		createMealCheckBoxes();
		
		checkMealPan.setLayout(new BoxLayout(checkMealPan, BoxLayout.Y_AXIS));
		checkMealPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		mealBoxPan.setLayout(new BorderLayout());
		checkMealPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in meals consumed"));
		mainMealPan.setLayout(new BorderLayout());
		
		rateMealPan.setLayout(new BorderLayout(5,20)); 
		rateMealBtn = new JButton("Rate selected meals"); rateMealPan.add(rateMealBtn, BorderLayout.EAST);
		rateMealBtn.addActionListener(new RateMealEvent());
		
		mealBoxPan.add(checkMealPan, BorderLayout.CENTER); mealBoxPan.add(rateMealPan, BorderLayout.SOUTH); 
		mainMealPan.add(mealBoxPan, BorderLayout.NORTH);
	}
	
	private void createMealCheckBoxes() {
		//TODO: Have to read these items from a prev week MealPlan
		
		for (int i=0;i<5;i++) {     // read the items from a plan
			JCheckBox check1 = new JCheckBox("Meal 1");
			check1.setSelected(true); 
			checkMealPan.add(check1);
			
		}
	}
	
	private void addExercPrefs() {
		mainExercPan = new JPanel();
		exercBoxPan = new JPanel();
		checkExercPan = new JPanel();
		rateExercPan = new JPanel();
						
		// Structure: mainExercPan [exercBoxPan [checkExercPan, rateExercPan]]
		
		createExercCheckBoxes();
		
		checkExercPan.setLayout(new BoxLayout(checkExercPan, BoxLayout.Y_AXIS));
		checkExercPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exercBoxPan.setLayout(new BorderLayout());
		checkExercPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in exercises done"));
		mainExercPan.setLayout(new BorderLayout());

		rateExercPan.setLayout(new BorderLayout(5,20));
		rateExercBtn = new JButton("Rate selected exercises"); rateExercPan.add(rateExercBtn, BorderLayout.EAST);
		rateExercBtn.addActionListener(new RateExercEvent());
		
		exercBoxPan.add(checkExercPan, BorderLayout.CENTER); exercBoxPan.add(rateExercPan, BorderLayout.SOUTH); 
		mainExercPan.add(exercBoxPan, BorderLayout.NORTH); 
	}
	
	private void createExercCheckBoxes() {
		// TODO: Have to read these items from a prev week ExercPlan
		for (int i=0;i<5;i++) {     // read the items from a plan
			JCheckBox check1 = new JCheckBox("Exerc 1");
			check1.setSelected(true); 
			checkExercPan.add(check1);
			
		}
		
	}
	
	private class RateMealEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			
		}
	}
	
	private class RateExercEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			
		}
	}
	
	private class DoneEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Store in a file ?
			saveCheckedItems(checkMealPan, mealsConsumed);   // store checked items in lists
			saveCheckedItems(checkExercPan, exercConsumed);
			
			/*  Testing the Checked storages
			for (String name : mealsConsumed ) {
				System.out.println(name);
			} 
			for (String name : exercConsumed ) {
				System.out.println(name);
			} 
			*/
		}
	}
	
	private void saveCheckedItems(JPanel p, List<String> storage) {
		for (Component c : p.getComponents()) {
			JCheckBox box = (JCheckBox) c;
			if (box.isSelected()) {
				storage.add(box.getText());
			}
		}
	}
	
	private class BackEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Store in a file ?  if necessary
			
		}
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new CheckIn();
			}
		});
	}
	
}
