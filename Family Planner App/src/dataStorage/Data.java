package dataStorage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import FMlogic.App;
import FMlogic.User;

public class Data {

	private final static String USERS = "Users.txt";
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//private final static String USER_MealPREFS = App.getCurrentUser().getName() + "_MealPrefs.txt";
	private final static String USER_MealPREFS = "vaka" + "_MealPrefs.txt";
	private final static String USER_ExercPREFS = "vaka" + "_ExercPrefs.txt";

	//private final static String USER_ExercPREFS = App.getCurrentUser().getName() + "_ExercPrefs.txt";
	private final static String DEFAULT_MealPREFS = "MealPrefs.txt";
	private final static String DEFAULT_ExercPREFS = "ExercPrefs.txt";
	private static enum PrefType { MEAL, EXERC };

	
	public static List<User> readUsers() {
		List<User> users = App.getUsers(); 
		new ArrayList<>();
		try {
			
			FileReader f = new FileReader(USERS);
			Scanner file = new Scanner(f);
			String line;
			
			while (file.hasNextLine()) {
				line = file.nextLine();
				String[] words = line.split("\\s+");
				//if (words.length!=2) { continue;}   // this check is handled in the catch block
				try {
	                //Read the words, create user for each line
					String name = words[0];
					double weight = Double.parseDouble(words[1]);
					User user = new User(name);
					user.setWeight(weight);
					users.add(user);         // users is a pointer to the users list in App class
					
	            } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
	                System.err.println("Skipping ill-formatted line " + line);
	            } 
			}
			file.close();
			f.close();

		} catch (IOException e) {
            System.err.println(e);
            return null;
        }
		
		return users;
	}
	
	public static void saveUser (User u) {
		File f = null;
		FileWriter fw = null;
		try {
			f = new File(USERS);
			fw = new FileWriter(f, true);
			fw.append("\n" + u.getName()+" "+u.getWeight()+" ");
			fw.flush();
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
		        if(fw != null)
		            fw.close();
		    } catch (IOException e) {
		        System.out.println(e);
		    }
		}
	}
	
	public static boolean updateWeightFile (double oldWeight, double newWeight) {
		
		User user = App.getCurrentUser();
		
		try {
			BufferedReader bufRead = new BufferedReader(new FileReader(USERS));
			String line;
			String input = "";       // stores all data in the file
			
			while ((line = bufRead.readLine()) != null) {
				input += line + "\n";
			}
			bufRead.close();
			input = input.replace(user.getName() + " " + oldWeight, 
					user.getName() + " " + newWeight);                      // replaces the user data
			
			File f = new File (USERS);
			FileWriter fw = new FileWriter(f);
			BufferedWriter bufWrite = new BufferedWriter(fw);
			bufWrite.write(input);
			bufWrite.close();
			
			
		} catch (Exception e) {
			System.out.println("Reading file error!");
			return false;
		}	
		
		return true;
	}

	/*
	 * Create a file with users prefs + rates (meal or exerc), populate with default items
	 */
	private static void createUserPrefsFile (PrefType type, User u) {
		
		// Deciding which file to create (meal/exerc)
		String name = u.getName();
		String path = "";
		String defPath = "";
		if (type == PrefType.MEAL) {
			path = name + "_MealPrefs.txt";
			defPath = DEFAULT_MealPREFS;
		} else if (type == PrefType.EXERC) {
			path = name + "_ExercPrefs.txt";
			defPath = DEFAULT_ExercPREFS;
		}
		// Creating the file
		File f = new File (path);
			try {
				f.createNewFile();
				// writing default prefs to a file
				BufferedReader bufRead = new BufferedReader(new FileReader(defPath));
				String line;
				String input = "";       // stores all data in the file
				
				while ((line = bufRead.readLine()) != null) {
					input += line + "\n";
				}
				bufRead.close();
				
				FileWriter fw = new FileWriter(f);
				BufferedWriter bufWrite = new BufferedWriter(fw);
				bufWrite.write(input);
				bufWrite.close();
				
			} catch (IOException e) {
				System.out.println("Error creating Prefs file");
			}		
	}
	
	public static void createUserPrefsFile (User u) {
		String name = u.getName();
		File meals = new File (name + "_MealPrefs.txt");
		File exerc = new File (name + "_ExercPrefs.txt");
		if (!meals.exists()) createUserPrefsFile (PrefType.MEAL, u);
		if (!exerc.exists()) createUserPrefsFile (PrefType.EXERC, u);
	}
	
	/*
	 * Store preference items to corresponding user files
	 */
	public static void storePrefs(List<String> mealPrefs, List<String> exercPrefs) {
		
		File mealFile = new File (USER_MealPREFS);
		File exercFile = new File (USER_ExercPREFS);
		if (!mealFile.exists()) createUserPrefsFile(PrefType.MEAL, App.getCurrentUser());
		if (!exercFile.exists()) createUserPrefsFile(PrefType.EXERC, App.getCurrentUser());
		
		// Storing meal preferences
		try {
			
			BufferedReader bufRead = new BufferedReader(new FileReader(USER_MealPREFS));
			String line;
			String input = "";       // stores all data from the file
			
			while ((line = bufRead.readLine()) != null) {
				input += line + "\n";
			}
			bufRead.close();
			
			for (String m : mealPrefs) {
				input += m + " 5" + "\n";          // give a rate of all to all preferred items
			}
			
			FileWriter fw = new FileWriter(mealFile);
			BufferedWriter bufWrite = new BufferedWriter(fw);
			bufWrite.write(input);
			bufWrite.close();
			
		} catch (IOException e) {
			System.out.println("Error writting to Meal Prefs file");
		}		
		
		// Storing exercise preferences
		try {

			BufferedReader bufRead = new BufferedReader(new FileReader(USER_ExercPREFS));
			String line;
			String input = "";       // stores all data from the file

			while ((line = bufRead.readLine()) != null) {
				input += line + "\n";
			}
			bufRead.close();

			for (String m : exercPrefs) {
				input += m + " 5" + "\n";          // give a rate of all to all preferred items
			}

			FileWriter fw = new FileWriter(exercFile);
			BufferedWriter bufWrite = new BufferedWriter(fw);
			bufWrite.write(input);
			bufWrite.close();

		} catch (IOException e) {
			System.out.println("Error writting to Meal Prefs file");
		}				
	}
	
	public static HashMap<String, Integer> getUserMealPrefsRates (User u) {
		HashMap<String, Integer> mealRates = new HashMap<>();
		String userName = u.getName();
		String filePath = userName + "_MealPrefs.txt";
		try {
			
			FileReader f = new FileReader(filePath);
			Scanner file = new Scanner(f);
			String line;
			
			while (file.hasNextLine()) {
				line = file.nextLine();
				String[] words = line.split("\\s+");
				
				try {
	                //Read the words, create user for each line
					String pref = words[0];
					int rate = Integer.parseInt(words[1]);
					mealRates.put(pref, rate);					
					
	            } catch (ArrayIndexOutOfBoundsException|NumberFormatException e) {
	                System.err.println("Skipping ill-formatted line " + line);
	            } 
			}
			file.close();
			f.close();

		} catch (IOException e) {
            System.err.println(e);
            return null;
        }
		return mealRates;
	}
	
	public static void main(String[] args) {
		List<String> meal = new ArrayList<>();
		List<String> exerc = new ArrayList<>();
		meal.add("fish"); meal.add("creampie");
		exerc.add("swim"); exerc.add("fly"); 
		
		storePrefs(meal, exerc);
		User vaka = new User ("vaka");
		
		HashMap<String, Integer> meals = getUserMealPrefsRates(vaka);
		
		for (Entry<String, Integer> e : meals.entrySet()) {
			String mealPref = e.getKey();
			int mealRating = e.getValue();
			System.out.println(mealPref + " " + mealRating);
		}


	}
}
