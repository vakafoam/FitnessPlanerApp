package FMlogic;

import java.util.List;

public class System {

	private List<User> users;
	
	private System () {
		users = null;
	}

	public List<User> getUsers() {
		return users;
	}

	public void addUser(User user) {
		users.add(user);
	}
	
	public void removeUser(User user){
		users.remove(user);
	}
	
	
	
}
