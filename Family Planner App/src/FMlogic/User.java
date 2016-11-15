package FMlogic;

import java.util.ArrayList;
import java.util.List;

public class User {

	private String name = null;
	private double weight;
	private List<String> foodPrefs = new ArrayList<>();
	private List<String> exercPrefs = new ArrayList<>();;
	
	// TODO: Preferences <- a hashmap of food/rating(1-5) ?? 
	
	public User (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFoodPrefs() {
		return foodPrefs;
	}

	public void setFoodPrefs(List<String> foodPrefs) {
		this.foodPrefs = foodPrefs;
	}

	public List<String> getExercPrefs() {
		return exercPrefs;
	}

	public void setExercPrefs(List<String> exercPrefs) {
		this.exercPrefs = exercPrefs;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "User [name=" + name + "]";
	}
}
