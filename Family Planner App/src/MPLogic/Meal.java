package MPLogic;

public class Meal {
    
    private char mealType;
    private String mealName; 
    private double mealCalCount;
    
    public Meal() {
        
        this.mealName = null;
        
    }
    
    public Meal(char mType, String mName, double mCCount) {
        
        this.mealType = mType;
        this.mealName = mName;
        this.mealCalCount = mCCount;
        
    }
    
    public void setMealType(char mType) {
        
        this.mealType = mType;
        
    }
    
    public void setMealName(String mName) {
        
        this.mealName = mName;
        
    }
    
    public void setMealCalCount(double mCalCount) {
        
        this.mealCalCount = mCalCount;
        
    }
    
    public char getMealType() {
        
        return this.mealType;
        
    }
    
    public String getMealName(){
        
        return this.mealName;
        
    }
    
    public double getMealCalCount() {
        
        return this.mealCalCount;
        
    }
    
    public String toString() {
        
        String output = mealType + " " + mealName + " " + mealCalCount;
        return output;
        
    }
    
}