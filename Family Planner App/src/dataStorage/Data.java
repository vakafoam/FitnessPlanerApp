package dataStorage;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import FMlogic.App;
import FMlogic.User;

public class Data {

	public final static String USERS = "Users.txt";
	
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
}
