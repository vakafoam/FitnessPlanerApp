package swingGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class Rate {

	private JFrame frame;
	private JPanel mainPan, mainMealPan, mealHeaderPan, exercHeaderPan, radioMealPan, radioExercPan, mainExercPan, checkMealPan, 
	               checkExercPan, donePan;
	private JTabbedPane pane;
	private JTextField newMeal, newExerc;
	private JLabel mealHeader, exercHeader;
	private JButton doneBtn, backBtn;
	private Map<String,ButtonGroup> mealBtnGroupMap = new HashMap<>();
	private Map<String,Integer> mealRates = new HashMap<>();         // store meal rates from user input
	private Map<String,ButtonGroup> exercBtnGroupMap = new HashMap<>();
	private Map<String,Integer> exercRates = new HashMap<>();         // store meal rates from user input
	
	public Rate() {
		initElems();
	}
	
	private void initElems() {
		mainPan = new JPanel();
		pane = new JTabbedPane();
		donePan = new JPanel();
		
		// Structure: mainPan [pane [meals, exercises], donePan]
		
		addMealRate();
		addExercRate();
		
		pane.addTab("Rate Meals", mainMealPan);
		pane.addTab("Rate Exercises", mainExercPan);
		
		donePan.setBackground(Color.WHITE);
		doneBtn = new JButton("Done");
		doneBtn.addActionListener(new DoneEvent());
		backBtn = new JButton("<< Back"); backBtn.setBackground(Color.WHITE);
		backBtn.addActionListener(new BackEvent());
		donePan.add(backBtn); donePan.add(doneBtn);
		
		mainPan.setLayout(new BorderLayout());
		mainPan.add(pane, BorderLayout.CENTER); mainPan.add(donePan, BorderLayout.PAGE_END);
		frame = new JFrame ("Rate items");
		frame.add(mainPan);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void addMealRate() {
		
		// Structure: mainMealPan [mealHeaderPan, radioMealPan [panel x N meals]]

		mainMealPan = new JPanel();
		mainMealPan.setLayout(new BoxLayout(mainMealPan, BoxLayout.PAGE_AXIS));
		mealHeaderPan = new JPanel();
		radioMealPan = new JPanel();
		radioMealPan.setLayout(new BoxLayout(radioMealPan, BoxLayout.PAGE_AXIS));
		
		mealHeader = new JLabel("Please Rate your meals from 1 to 5");
		mealHeaderPan.add(mealHeader);
								
		createMealRates();
		
		mainMealPan.add(mealHeaderPan, BorderLayout.NORTH);
		mainMealPan.add(radioMealPan, BorderLayout.CENTER);
	}
	
	private void createMealRates() {
		//TODO: Have to read these items from a checkedIn meals
		
		for (int i=0;i<5;i++) {           // TODO: for all checked meals
			String mealName = "Meal 1 toString";   // get the name of meal
			JPanel p = new JPanel();
			JLabel mealLbl = new JLabel(mealName);
			p.add(mealLbl);		
	
			ButtonGroup bg = createRateBtns(p);
			p.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			
			mealBtnGroupMap.put(mealName, bg);    // store meal name + radiobutton group in a map
			radioMealPan.add(p);
		}
	}
	
	private ButtonGroup createRateBtns(JPanel p) {
		ButtonGroup bg = new ButtonGroup();
		JRadioButton b1 = new JRadioButton("1");
		JRadioButton b2 = new JRadioButton("2");
		JRadioButton b3 = new JRadioButton("3");
		JRadioButton b4 = new JRadioButton("4");
		JRadioButton b5 = new JRadioButton("5");
		
		b5.setSelected(true); // we first assume user likes all items

		bg.add(b1); bg.add(b2); bg.add(b3); bg.add(b4); bg.add(b5);
		p.add(b1); p.add(b2); p.add(b3); p.add(b4); p.add(b5);
		
		return bg;
	}
	
	private void addExercRate() {
		
		// Structure: mainExercPan [exercHeaderPan, radioExercPan [panel x N exerc] ]
		
		mainExercPan = new JPanel();
		mainExercPan.setLayout(new BoxLayout(mainExercPan, BoxLayout.PAGE_AXIS));
		exercHeaderPan = new JPanel();
		radioExercPan = new JPanel();
		radioExercPan.setLayout(new BoxLayout(radioExercPan, BoxLayout.PAGE_AXIS));
						
		exercHeader = new JLabel("Please Rate your exercises from 1 to 5");
		exercHeaderPan.add(exercHeader);
		
		
		createExercRates();
		
		mainExercPan.add(exercHeaderPan, BorderLayout.NORTH);
		mainExercPan.add(radioExercPan, BorderLayout.CENTER);
		 
	}
	
	private void createExercRates() {
		//TODO: Have to read these items from a checkedIn exerc

		for (int i=0;i<5;i++) {           // TODO: for all checked exerc
			String exercName = "Exerc 1 toString";   // get the name of exerc
			JPanel p = new JPanel();
			JLabel exercLbl = new JLabel(exercName);
			p.add(exercLbl);		

			ButtonGroup bg = createRateBtns(p);
			p.setBorder(javax.swing.BorderFactory.createLineBorder(Color.LIGHT_GRAY));

			exercBtnGroupMap.put(exercName, bg);    // store meal name + radiobutton group in a map
			radioExercPan.add(p);

		}
	}
	
	private class DoneEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			saveRates (mealBtnGroupMap, mealRates); 
			saveRates (exercBtnGroupMap, exercRates); 
			
			//TODO: Store ratings in files 
			
			/*  Testing the Rates storages
			for (String name : mealRates.keySet() ) {
				System.out.println(name + " " +mealRates.get(name));
			} 
			for (String name : exercRates.keySet() ) {
				System.out.println(name + " " + exercRates.get(name));
			} 
			*/
		}
		
	}
	
	private void saveRates (Map<String,ButtonGroup> inMap, Map<String,Integer> outMap) {
		for (Entry<String, ButtonGroup> entry : inMap.entrySet()) {
			String itemName = entry.getKey();
			ButtonGroup bg = entry.getValue();
			Enumeration <AbstractButton> buts = bg.getElements();
			while (buts.hasMoreElements()) {
				JRadioButton but = (JRadioButton) buts.nextElement();
				if (but.isSelected()) {
					int rate = Integer.parseInt(but.getText());
					outMap.put(itemName, rate);              // store user ratings in a map
				}
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
				new Rate();
			}
		});
	}
}
