package MPLogic;

public class Week {
    
    //Array representing each weekday (Sunday = 0, Monday = 1, etc.)
    private WeekDay[] weekDays = new WeekDay[7];
    //The date of Sunday or the beginning of the week, in the form 11/17/2016
    private String  weekOf;
    
    public Week() {
        
        this.weekOf = "";
        for (int i = 0; i < weekDays.length; i++) {
            
           this.weekDays[i] = new WeekDay();
           
        }
        
    }
    
    //Constructor taking Sunday's date to denote which week, initializes each WeekDay.
    public Week(String date) {
        
        this.weekOf = date;
        for (int i = 0; i < weekDays.length; i++) {
            
           this.weekDays[i] = new WeekDay();
           
        }
        
    }
    
    public void setWeekDay (WeekDay wd, int day) {
        
        this.weekDays[day] = wd;
        
    }
    
    public WeekDay getWeekDay (int day) {
        
        return this.weekDays[day];
        
    }
    
    public String getWeekOf () {
        
        return this.weekOf;
        
    }
    
    public String toString () {
        
        String output = "";
        
        output += this.weekOf + System.lineSeparator();
        WeekDay currDay;
        
        for (int i = 0; i < this.weekDays.length; i ++) {
            
            currDay = this.weekDays[i];
            output += i + " " + currDay.toString() + System.lineSeparator();
            
        }
        
        return output;
    }
    
}