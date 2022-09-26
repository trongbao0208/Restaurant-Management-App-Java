/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author trong
 */
public class Order{
    private String name;
    private int orderNumber;
    private boolean paid;
    private ArrayList<Item> items;
    private double totalBill;
    private String orderType;
    
   
    
    public Order(int orderNumber, String name, String orderType){
        this.name = name;
        this.orderNumber = orderNumber;
        this.totalBill = 0;
        this.paid = false;
        this.items = new ArrayList<>();
        this.orderType = orderType;
    }
    
    public String getOrderType(){
        return this.orderType;
    }
    
    public boolean getPaid(){
        return this.paid;
    }
    
    public String getName(){
        return this.name;
    }
    
    public ArrayList<Item> getItems(){
        return items;
    }
    
    public void subtractFromTotalBill (double amount){
        setTotalBill(getTotalBill() - amount);
    }
     
    public void addItemToArray(Item item){
        getItems().add(item);
    }
    
    public double getTotalBill(){
        return totalBill;
    }
    
    public void addToTotalBill(double amount){
        setTotalBill(getTotalBill() + amount);
    }
    
    public int getOrderNumber(){
        return orderNumber;
    }
    
    public void payOrder(){
        this.paid = true;
    }





    /**
     * @param totalBill the totalBill to set
     */
    public void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }
}
