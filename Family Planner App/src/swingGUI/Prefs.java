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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dataStorage.Data;

public class Prefs {
	
	private JFrame frame;
	private JPanel mainPan, mainMealPan, mainExercPan, mealBoxPan, checkMealPan, 
	checkExercPan, exercBoxPan, addMealPan, addExercPan, donePan;
	private JTabbedPane pane;
	private JTextField newMeal, newExerc;
	private JLabel enterMeal, enterExerc;
	private JButton addMealBtn, addExercBtn, doneBtn, backBtn;
	private List<String> mealPrefs = new ArrayList<>();
	private List<String> exercPrefs = new ArrayList<>();
	
	
	public Prefs() {
		initElems();
	}
	
	private void initElems() {
		mainPan = new JPanel();
		pane = new JTabbedPane();
		donePan = new JPanel();
		
		// Structure: mainPan [pane [meals, exercises], donePan]
		
		addMealPrefs();
		addExercPrefs();
		
		pane.addTab("Meal Preferences", mainMealPan);
		pane.addTab("Exercise preferences", mainExercPan);
		
		donePan.setBackground(Color.WHITE);
		doneBtn = new JButton("Done");
		doneBtn.addActionListener(new DoneEvent());
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		backBtn.addActionListener(new BackEvent());
		donePan.add(backBtn); donePan.add(doneBtn);
				
		mainPan.setLayout(new BorderLayout());
		mainPan.add(pane, BorderLayout.CENTER); mainPan.add(donePan, BorderLayout.PAGE_END);
		frame = new JFrame ("My preferences");
		frame.add(mainPan);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addMealPrefs() {
		mainMealPan = new JPanel();
		mealBoxPan = new JPanel();
		checkMealPan = new JPanel();
		addMealPan = new JPanel();
				
		// Structure: mainMealPan [mealBoxPan [checkMealPan, addMealPan]]
				
		createMealCheckBoxes();
		
		checkMealPan.setLayout(new BoxLayout(checkMealPan, BoxLayout.Y_AXIS));
		checkMealPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		mealBoxPan.setLayout(new BorderLayout());
		checkMealPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in/out meals"));
		mainMealPan.setLayout(new BorderLayout());
		
		addMealPan.setLayout(new BorderLayout(5,20)); 
		enterMeal = new JLabel ("Add a meal: "); addMealPan.add(enterMeal, BorderLayout.WEST);
		newMeal = new JTextField(10); addMealPan.add(newMeal, BorderLayout.CENTER);
		addMealBtn = new JButton("Add"); addMealPan.add(addMealBtn, BorderLayout.EAST);
		addMealBtn.addActionListener(new AddMealEvent());
		
		

		mealBoxPan.add(checkMealPan, BorderLayout.CENTER); mealBoxPan.add(addMealPan, BorderLayout.SOUTH); 
		mainMealPan.add(mealBoxPan, BorderLayout.NORTH);
	}
	
	private void createMealCheckBoxes() {
		//TODO: Have to read these items from a file
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
						
		// Structure: mainExercPan [exercBoxPan [checkExercPan, addExercPan]]
		
		createExercCheckBoxes();
		
		checkExercPan.setLayout(new BoxLayout(checkExercPan, BoxLayout.Y_AXIS));
		checkExercPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exercBoxPan.setLayout(new BorderLayout());
		checkExercPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Check in/out exercises"));
		mainExercPan.setLayout(new BorderLayout());
		
		addExercPan.setLayout(new BorderLayout(5,20));
		enterExerc = new JLabel ("Add an exercise: "); addExercPan.add(enterExerc, BorderLayout.WEST);
		newExerc = new JTextField(10); addExercPan.add(newExerc, BorderLayout.CENTER);
		addExercBtn = new JButton("Add"); addExercPan.add(addExercBtn, BorderLayout.EAST);
		addExercBtn.addActionListener(new AddExercEvent());
		
		exercBoxPan.add(checkExercPan, BorderLayout.CENTER); exercBoxPan.add(addExercPan, BorderLayout.SOUTH); 
		mainExercPan.add(exercBoxPan, BorderLayout.NORTH); 
	}
	
	private void createExercCheckBoxes() {
		JCheckBox check1 = new JCheckBox("Jogging"); check1.setSelected(true); checkExercPan.add(check1);
		JCheckBox check2 = new JCheckBox("Power Lifting"); check2.setSelected(true); checkExercPan.add(check2);
		JCheckBox check3 = new JCheckBox("Yoga"); check3.setSelected(true); checkExercPan.add(check3);
		JCheckBox check4 = new JCheckBox("Shaping"); check4.setSelected(true); checkExercPan.add(check4);
		JCheckBox check5 = new JCheckBox("Swimming"); check5.setSelected(true); checkExercPan.add(check5);
		
	}
	
	private class AddMealEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String meal = newMeal.getText();
			JCheckBox check = new JCheckBox(meal); check.setSelected(true); checkMealPan.add(check);
			newMeal.setText("");
			frame.revalidate();    				// redraw the frame after adding a component
			// TODO: Add a new item to a file
		}
	}
	
	private class AddExercEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String meal = newExerc.getText();
			JCheckBox check = new JCheckBox(meal); check.setSelected(true); checkExercPan.add(check);
			newExerc.setText("");
			frame.revalidate();    				// redraw the frame after adding a component
			// TODO: Add a new item to a file
		}
	}
	
	private class DoneEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
						
			saveCheckedItems(checkMealPan, mealPrefs);
			saveCheckedItems(checkExercPan, exercPrefs);
			
			/*  Testing the Prefs storages
			for (String name : mealPrefs ) {
				System.out.println(name);
			} 
			for (String name : exercPrefs ) {
				System.out.println(name);
			} 
			*/
			
			Data.storePrefs(mealPrefs, exercPrefs);  // Store Prefs into files

			new Options();
			frame.dispose();
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
			new Options();
			frame.dispose();
		}
		
	}
	
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Prefs();
			}
		});

	}
}
