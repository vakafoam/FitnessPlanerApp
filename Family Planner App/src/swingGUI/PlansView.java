package swingGUI;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PlansView {

	private JFrame frame;
	private JPanel mainMealPan, mainExercPan, mealPlanPan, exercPlanPan, mealPan2, exercPan2, mealPan3, exercPan3;
	private JTabbedPane pane;
	private JTextField newMeal, newExerc;
	private JLabel enterMeal, enterExerc, chooseWeek;
	private JComboBox<String> mealWeek, exercWeek;
	private JTable mealTable, exercTable;
	private JButton rateMealBtn, rateExercBtn, doneBtn, backBtn, doneBtn2, backBtn2;
	private final String[] columnNames = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	public PlansView() {
		initElems();
	}
	
	private void initElems() {
		pane = new JTabbedPane();
		
		addMealPlans();
		addExercPlans();
		
		pane.addTab("Meal Plan", mainMealPan);
		pane.addTab("Exercise Plan", mainExercPan);
		
		frame = new JFrame ("My Plans");
		frame.add(pane);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addMealPlans() {
		mainMealPan = new JPanel();
		mealPlanPan = new JPanel();
				
		mealPlanPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		mealPlanPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Week Meal Plan"));
		mainMealPan.setLayout(new BoxLayout(mainMealPan, BoxLayout.Y_AXIS));
		chooseWeek = new JLabel("Choose a week"); mealPlanPan.add(chooseWeek);
		mealWeek = new JComboBox<>(); 
		createMealWeeks();
		mealPlanPan.add(mealWeek);
		
		mealTable = new JTable(getMealData(), columnNames);
		mealTable.setFillsViewportHeight(true);
		mealPlanPan.add(mealTable);
		JScrollPane scrollPane = new JScrollPane(mealTable); mealPlanPan.add(scrollPane);
		mealPlanPan.setLayout(new BoxLayout(mealPlanPan, BoxLayout.Y_AXIS));
		
		mealPan2 = new JPanel();
		
		rateMealBtn = new JButton("Rate Meals"); mealPan2.add(rateMealBtn);
		
		mealPan3 = new JPanel(); mealPan3.setBackground(Color.WHITE);
		doneBtn = new JButton("Done");
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		mealPan3.add(backBtn); mealPan3.add(doneBtn);

		mainMealPan.add(mealPlanPan); mainMealPan.add(mealPan2); mainMealPan.add(mealPan3);
	}
	
	private Object[][] getMealData() {
		 Object[][] data = {
			        {"Porridge", "Fruits",
			         "Oatmeals", "Yogurt", "Fruits", "Oatmeals", "Yogurt"},
			        {"Potatoes", "Pasta",
				         "Rice", "Pizza", "Buchwheat", "Vegetables", "Pizza"},
			        {"Fruits", "Vegetables",
					         "Salad", "Yogurt", "Rice", "Salad", "Yogurt"},
			        };
		return data;
	}

	private void createMealWeeks() {
		mealWeek.addItem("Upcoming");
		mealWeek.addItem("This");
		mealWeek.addItem("Previous");
		
	}
	
	private void addExercPlans() {
		mainExercPan = new JPanel();
		exercPlanPan = new JPanel();
				
		exercPlanPan.setAlignmentX(Component.RIGHT_ALIGNMENT);
		exercPlanPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Week Exercise Plan"));
		mainExercPan.setLayout(new BoxLayout(mainExercPan, BoxLayout.Y_AXIS));
		chooseWeek = new JLabel("Choose a week"); exercPlanPan.add(chooseWeek);
		exercWeek = new JComboBox<>(); 
		createExercWeeks();
		exercPlanPan.add(exercWeek);
		
		
		exercTable = new JTable(getExercData(), columnNames);
		exercTable.setFillsViewportHeight(true);
		exercPlanPan.add(exercTable);
		JScrollPane scrollPane = new JScrollPane(exercTable); exercPlanPan.add(scrollPane);
		exercPlanPan.setLayout(new BoxLayout(exercPlanPan, BoxLayout.Y_AXIS));
		
		exercPan2 = new JPanel();
		
		rateExercBtn = new JButton("Rate Exercises"); exercPan2.add(rateExercBtn);
		
		exercPan3 = new JPanel(); exercPan3.setBackground(Color.WHITE);
		doneBtn2 = new JButton("Done");
		backBtn2 = new JButton("<< Back"); backBtn2.setBackground(Color.WHITE);
		exercPan3.add(backBtn2); exercPan3.add(doneBtn2);

		mainExercPan.add(exercPlanPan); mainExercPan.add(exercPan2); mainExercPan.add(exercPan3);
	}
	
	private void createExercWeeks() {
		exercWeek.addItem("Upcoming");
		exercWeek.addItem("This");
		exercWeek.addItem("Previous");
		
	}
	
	private Object[][] getExercData() {
		 Object[][] data = {
			        {"Swimming", "Jogging",
			         "Power Lifting", "---", "Swimming", "---", "Jogging"}
			        };
		return data;
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new PlansView();
			}
		});

	}
}
