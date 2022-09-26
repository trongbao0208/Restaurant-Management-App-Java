/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author trong
 */
public class Employee implements Serializable{
    private static final DecimalFormat df = new DecimalFormat("#.##");
    private String firstName;
    private String lastName;
    private String fullName;
    private String pin;
    private boolean alreadyClockedIn;
    private Date ClockInTime;
    private Date ClockOutTime;
    
    private double totalHoursOfWork;
    
    public Employee(String firstName, String lastName, String pin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.pin = pin;
        alreadyClockedIn = false;
        totalHoursOfWork = 0;
    }
    
    public String getNameAndEmployeePIN(){
        return fullName + ": " + pin;
    }
    
    public String getPin(){
        return pin;
    }
    
    public String clockIn (){
        Date date = new Date();
        ClockInTime = date;
        SimpleDateFormat ft = 
        new SimpleDateFormat ("MM.dd.yyyy 'at' hh:mm:ss a zzz");
        alreadyClockedIn = true;
        return fullName + " clocked in on " + ft.format(date);
    }
    
    
    public String clockOut(){
        
        Date currentDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM.dd.yyyy 'at' hh:mm:ss a zzz");
        //Difference in milliseconds
        long timeDiff = currentDate.getTime() - this.ClockInTime.getTime();
        double totalHours = (double)timeDiff / 3600000;
        this.totalHoursOfWork += totalHours;
        this.ClockInTime = null;
        alreadyClockedIn = false;
        return this.getFullName() + " has successfully clocked out at " + ft.format(currentDate)
                + " with a total of " + df.format(totalHours) + " hour.";
    }
    
    public boolean checkAlreadyClockedIn(){
        return alreadyClockedIn;
    } 
    
    public String getFullName(){
        return this.fullName;
    }
}
