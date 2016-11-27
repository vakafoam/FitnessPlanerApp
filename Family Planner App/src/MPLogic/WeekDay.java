package MPLogic;

public class WeekDay {
    
    
    private Meal breakFast, lunch, dinner;
    private double dailyCalTotal;
    
    public WeekDay () {
        this.breakFast = null;
        this.lunch = null;
        this.dinner = null;
    }
    public void setBreakFast (Meal bFast) {
        
        this.breakFast = bFast;
        
    }
    
    public void setLunch (Meal lunch) {
        
        this.lunch = lunch;
        
    }
    
    public void setDinner (Meal dinner) {
        
        this.dinner = dinner;
        
    }
    
    public void setDailyCalTotal () {
        
        this.dailyCalTotal = dinner.getMealCalCount() + lunch.getMealCalCount()
                           + dinner.getMealCalCount();
        
    }
    
    public Meal getBreakFast () {
        
        return this.breakFast;
        
    }
    
    public Meal getLunch () {
        
        return this.lunch;
        
    }
    
    public Meal getDinner () {
        
        return this.dinner;
        
    }
    
    public double getDailyCalTotal () {
        
        return this.dailyCalTotal;
        
    }
    
    public String toString () {
        String bFast = breakFast.toString();
        String lunchStrng = lunch.toString();
        String dinnerStrng = dinner.toString();
        String  output = bFast + " " + lunchStrng + " " + dinnerStrng;
        return output;        
    }
    
}