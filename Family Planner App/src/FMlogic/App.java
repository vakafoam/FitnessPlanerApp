package FMlogic;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import swingGUI.LogIn;

public class App {

	private static List<User> users = new ArrayList();
	private static User currentUser = null; 
	
	public static User getCurrentUser() {
		return currentUser;
	}
	
	public static boolean setCurrentUser(String userName) {
		for (User u: users) {
			if (u.getName().equals(userName)){
				currentUser = u;
				return true;
			}
		}
		return false;
	}
	
	public static List<User> getUsers() {
		return users;
	}

	public static void addUser(String userName) {
		User newUser = new User (userName);
		users.add(newUser);
	}
	
	public static void removeUser(User user){
		users.remove(user);
	}
	
	public static boolean userExists(String user) {
		// Check if the user is registered 
		if (users.isEmpty()) {
			return false;
		} 
		for (User u: users) {
			if (u.getName().equals(user)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable () {
			public void run() {
				new LogIn();
			}
		});
		
	}
}
