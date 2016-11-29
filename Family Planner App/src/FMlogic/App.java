package FMlogic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dataStorage.Data;
import swingGUI.LogIn;

/*
 * Application class with the main method to start the application
 */
public class App {

	private static List<User> users = new ArrayList<>();
	private static User currentUser = null; 
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static boolean setCurrentUser(String userName) {
		if (userExists(userName)) {
			for (User u: users){
				if (u.getName().equals(userName)) {
					currentUser = u;
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<User> getUsers() {
		return users;
	}

	/*
	 * Add user to the system and save to Users.txt
	 * @parameter String userName
	 */
	public static void addUser(String userName) {
		if (!userExists(userName)) {
			User newUser = new User (userName);
			users.add(newUser);
			Data.saveUser(newUser);      		// Write a new user to a file
			Data.createUserPrefsFile(newUser);  // Create default preference files
		} else {
			JOptionPane.showMessageDialog(null, "Username is taken! Use another name or login", 
					"User already registered", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void removeUser(User user){
		users.remove(user);
		//TODO: Remove user form a file
	}
	
	/*
	 * Return an existing user object by name
	 * @parameter String userName
	 * @return User user or null if does not exist
	 */
	public static User getUserByName (String name) {
		for (User u: users) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	
	/*
	 * Return boolean of if the user exists
	 */
	public static boolean userExists (String user) {
		// Check if the user is registered 
		if (users.isEmpty()) {
			System.out.println("Not found!");
			return false;
		} 
		if (getUserByName(user)!=null) {
				return true;
		}
		return false;
	}
	
	/*
	 * Load saved users from the data file into the App array
	 */
	private static void loadUsers() {
		Data.readUsers();
	}
	
	public static void main(String[] args) {
		
		loadUsers(); // read registered users from the file
		
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LogIn();
			}
		});
		
	}
}
