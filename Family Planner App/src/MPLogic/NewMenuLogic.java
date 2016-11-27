package MPLogic;

import MPData.*;
import java.util.List;

public class NewMenuLogic {
 
    private List<Meal> breakFastOptions, lunchOptions, dinnerOptions;

    public List<Meal> loadBFastOptions() {
        
        return this.breakFastOptions;
    }
    
    public List<Meal> loadLunchOptions() {
        
        return this.lunchOptions;
    }
    
    public List<Meal> loadDinnerOptions() {
        
        return this.dinnerOptions;
    }
    
} 