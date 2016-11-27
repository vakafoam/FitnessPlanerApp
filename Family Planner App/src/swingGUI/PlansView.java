package swingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FMlogic.User;
import MPLogic.Meal;
import MPLogic.Week;
import MPLogic.WeekDay;
import dataStorage.MealPlannerData;

public class PlansView {

	private JFrame frame;
	private JPanel mainPan, mainMealPan, mainExercPan, mealPlanPan, 
			exercPlanPan, RateMealPan, RateExercPan, donePan;
	private JTabbedPane pane;
	private JLabel chooseWeek;
	private JComboBox<String> mealWeek, exercWeek;
	private JTable mealTable, exercTable;
	private JButton rateMealBtn, rateExercBtn, doneBtn, backBtn;
	private final String[] columnNames = {"MEAL","Sunday", "Monday", "Tuesday", 
			"Wednesday", "Thursday", "Friday", "Saturday"};
	
	public PlansView() {
		initElems();
	}
	
	private void initElems() {
		
		//TODO: Istead of Done->Rate + CheckIn frame
		
		mainPan = new JPanel();
		pane = new JTabbedPane();
		donePan = new JPanel();
		
		addMealPlans();
		addExercPlans();
		
		pane.addTab("Meal Plan", mainMealPan);
		pane.addTab("Exercise Plan", mainExercPan);
		
		donePan = new JPanel(); donePan.setBackground(Color.WHITE);
		doneBtn = new JButton("Done");
		doneBtn.addActionListener(new DoneEvent());
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		backBtn.addActionListener(new BackEvent());
		donePan.add(backBtn); donePan.add(doneBtn);
		
		mainPan.setLayout(new BorderLayout());
		mainPan.add(pane, BorderLayout.CENTER); mainPan.add(donePan, BorderLayout.PAGE_END);
				
		frame = new JFrame ("My Plans");
		frame.add(mainPan);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);  // places the frame in center
		frame.setVisible(true);
	}
	
	private void addMealPlans() {
		mainMealPan = new JPanel();
		mealPlanPan = new JPanel();
				
		mealPlanPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Week Meal Plan"));
		mainMealPan.setLayout(new BoxLayout(mainMealPan, BoxLayout.Y_AXIS));
		chooseWeek = new JLabel("Choose a week"); mealPlanPan.add(chooseWeek);
		mealWeek = new JComboBox<>(); 
		createMealWeeks();
		mealPlanPan.add(mealWeek);
		
		mealTable = new JTable(getMealData(), columnNames);
		mealTable.setFillsViewportHeight(true);
		mealTable.setCellSelectionEnabled(true);
		ListSelectionModel tModel = mealTable.getSelectionModel();
		tModel.addListSelectionListener(new MealSelectionHandler());
		mealPlanPan.add(mealTable);
		JScrollPane scrollPane = new JScrollPane(mealTable); mealPlanPan.add(scrollPane);
		mealPlanPan.setLayout(new BoxLayout(mealPlanPan, BoxLayout.Y_AXIS));
		
		RateMealPan = new JPanel();
		rateMealBtn = createBtn("Check in Meals");
		RateMealPan.add(rateMealBtn); mainMealPan.add(mealPlanPan); mainMealPan.add(RateMealPan);
	}
	
	private JButton createBtn (String name) {
		JButton btn = new JButton(name); 
		btn.setPreferredSize(new Dimension(620,40));
		return btn;
	}
	
	private Object[][] getMealData() {
		// TODO: Have to change to current user
		
		List<String> br = new ArrayList<>(8); br.add("Breakfast");
		List<String> lu = new ArrayList<>(8); lu.add("Lunch");
		List<String> di = new ArrayList<>(8); di.add("Dinner");
		User u = new User ("Mary");
		Week week = new MealPlannerData().getCurrentMenu(u);
		for (int i=0;i<7;i++) {
			WeekDay day = week.getWeekDay(i);
			String b = day.getBreakFast().getMealName();
			String l = day.getLunch().getMealName();
			String d = day.getDinner().getMealName();
			br.add(b); lu.add(l); di.add(d);
		}
		Object[] breakfast = br.toArray();
		Object[] lunch = lu.toArray();
		Object[] dinner = di.toArray();
				
		Object[][] data = {
			        breakfast, lunch, dinner
			        };
		return data;
	}

	private void createMealWeeks() {
		mealWeek.addItem("Upcoming");
		mealWeek.addItem("This");
		mealWeek.addItem("Previous");
	}
	
	private class MealSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				int row = mealTable.getSelectedRow();
				int col = mealTable.getSelectedColumn();
				System.out.println(mealTable.getValueAt(row, col));
            }
		}
	}
	
	private void addExercPlans() {
		mainExercPan = new JPanel();
		exercPlanPan = new JPanel();
				
		exercPlanPan.setBorder(javax.swing.BorderFactory.createTitledBorder("Week Exercise Plan"));
		mainExercPan.setLayout(new BoxLayout(mainExercPan, BoxLayout.Y_AXIS));
		chooseWeek = new JLabel("Choose a week"); exercPlanPan.add(chooseWeek);
		exercWeek = new JComboBox<>(); 
		createExercWeeks();
		exercPlanPan.add(exercWeek);
				
		exercTable = new JTable(getExercData(), columnNames);
		exercTable.setFillsViewportHeight(true);
		exercTable.setCellSelectionEnabled(true);
		ListSelectionModel tModel = mealTable.getSelectionModel();
		tModel.addListSelectionListener(new ExercSelectionHandler());
		exercPlanPan.add(exercTable);
		JScrollPane scrollPane = new JScrollPane(exercTable); exercPlanPan.add(scrollPane);
		exercPlanPan.setLayout(new BoxLayout(exercPlanPan, BoxLayout.Y_AXIS));
		
		RateExercPan = new JPanel();		
		rateExercBtn = createBtn("Check in Exercises"); 
		RateExercPan.add(rateExercBtn);
		mainExercPan.add(exercPlanPan); mainExercPan.add(RateExercPan);
	}
	
	private class ExercSelectionHandler implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				int row = mealTable.getSelectedRow();
				int col = mealTable.getSelectedColumn();
				System.out.println(mealTable.getValueAt(row, col));
            }
		}
	}
	
	private class DoneEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//TODO: Store in a file ?  if necessary
			new Options();
			frame.dispose();
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
