package dataStorage;

import MPLogic.*;
import FMlogic.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;


// **IN PROGRESS
public class MealPlannerData {

    private final static String mealOptions = "MealDatabase.txt";
    private final static String currentMenu = "-CurrentMenu.txt";
    private final static String menuArchive = "-MenuArchive.txt";

    public List<Meal> loadMealOptionsType(char mealType) throws IOException {

        if (mealType != 'B' || mealType != 'L' || mealType != 'D') {

            System.err.println("Invalid Meal Type");
            return null;

        }

        List<Meal> request = new ArrayList<>();

        try {

            FileReader mealsFile = new FileReader(mealOptions);
            Scanner loadMeals = new Scanner(mealsFile);
            String nextLine;
            String mealName;
            char mType;
            double calCount;

            while (loadMeals.hasNextLine()) {

                nextLine = loadMeals.next();

                if (nextLine.charAt(0) == mealType) {

                    String[] newMeal = nextLine.split("\\s+");
                    mType = newMeal[0].charAt(0);
                    mealName = newMeal[1];
                    calCount = Double.parseDouble(newMeal[2]);
                    Meal nextOption = new Meal();
                    nextOption.setMealType(mType);
                    nextOption.setMealName(mealName);
                    nextOption.setMealCalCount(calCount);
                    request.add(nextOption);
                }
            }

        } catch (IOException e) {
            System.err.println(e);
            return null;
        }

        return request;
    }

    public void saveCurrentMenu(User u, Week w) {

        File currMenu = null;
        FileWriter saveMenu = null;

        try {

            currMenu = new File(u.getName() + currentMenu);
            saveMenu = new FileWriter(currMenu);
            saveMenu.write(w.toString());
            saveMenu.flush();
            saveMenu.close();
        } catch (IOException e) {

            System.err.println(e);
        }
    }

    public Week getCurrentMenu(User u) {
       
        Week current;
        WeekDay day = new WeekDay();
        Meal b = new Meal();
        Meal l = new Meal();
        Meal d = new Meal();
       
        try {
            
            FileReader currMenu = new FileReader(u.getName() + currentMenu);
            Scanner loadCurr = new Scanner(currMenu);
            current = new Week(loadCurr.nextLine());
           
            while (loadCurr.hasNextLine()) {
                
                String next = loadCurr.nextLine();
                String[] nextDay = next.split("\\s+");
                int wkDay = Integer.parseInt(nextDay[0]);
                b.setMealType(nextDay[1].charAt(0));
                b.setMealName(nextDay[2]);
                b.setMealCalCount(Double.parseDouble(nextDay[3]));
                l.setMealType(nextDay[4].charAt(0));
                l.setMealName(nextDay[5]);
                l.setMealCalCount(Double.parseDouble(nextDay[6]));
                d.setMealType(nextDay[7].charAt(0));
                d.setMealName(nextDay[8]);
                d.setMealCalCount(Double.parseDouble(nextDay[9]));
                day.setBreakFast(b);
                day.setLunch(l);
                day.setDinner(d);
                current.setWeekDay(day, wkDay);                    
            }    
            
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }

        return current;
    }
    
    public void addToArchive(User u, Week w) {
        
        File addToArchive = null;
        FileWriter saveMenu = null;

        try {

           // addToArchive = new File(u.getName() + menuArchive);
           // saveMenu = new FileWriter(addToArchive, true);
            
            if (addToArchive.exists()) { 
                
                saveMenu.append(System.lineSeparator());
                saveMenu.append(w.toString());
                saveMenu.append(System.lineSeparator());
                saveMenu.flush(); 
                
            }
            else {
                
                addToArchive = new File(u.getName() + menuArchive);
                saveMenu = new FileWriter(addToArchive, true);
                saveMenu.write(w.toString());
                saveMenu.write(System.lineSeparator());
                saveMenu.flush();
                
            }
           

        } catch (IOException e) {

            System.err.println(e);
        }       
    }
    
    public List<Week> loadArchive (User u) {
        
        List<Week> archive = new ArrayList<>();
        Week nextArchive = new Week();
        WeekDay day = new WeekDay();
        Meal b = new Meal();
        Meal l = new Meal();
        Meal d = new Meal();
       
        try {
            
            FileReader currArchive = new FileReader(u.getName() + menuArchive);
            Scanner loadArchive = new Scanner(currArchive);
           
            while (loadArchive.hasNextLine()) {
                
                String next = loadArchive.nextLine();
                String[] nextDay = next.split("\\s+");
                
                if (nextDay.length == 1 && !nextDay[0].contentEquals("")) {
                    
                    nextArchive = new Week(loadArchive.nextLine());
                    
                }
                               
                if (nextDay.length == 10) {
                    
                    for (int i = 0; i < 8; i++) {
                        int wkDay = Integer.parseInt(nextDay[0]);
                        b.setMealType(nextDay[1].charAt(0));
                        b.setMealName(nextDay[2]);
                        b.setMealCalCount(Double.parseDouble(nextDay[3]));
                        l.setMealType(nextDay[4].charAt(0));
                        l.setMealName(nextDay[5]);
                        l.setMealCalCount(Double.parseDouble(nextDay[6]));
                        d.setMealType(nextDay[7].charAt(0));
                        d.setMealName(nextDay[8]);
                        d.setMealCalCount(Double.parseDouble(nextDay[9]));
                        day.setBreakFast(b);
                        day.setLunch(l);
                        day.setDinner(d);
                        nextArchive.setWeekDay(day, wkDay);                                      
                    }                    
                    archive.add(nextArchive);
                }
                                  
            }    
            
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return null;
        }

        return archive;
    }
}


