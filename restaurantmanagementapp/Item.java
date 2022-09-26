/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author trong
 */
public class Item  extends JButton{
    private String name;
    private double price;
    private boolean availability;
    private String belongToPanelName;
    private GUI parent;
    public Item(String name, double price, boolean availability, String belongToPanelName){
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.belongToPanelName = belongToPanelName;
    }
    
   

    
    
    public String toString() {
        return this.name + " $" + Double.toString(price);
    }
    
    public String getBelongToPanelName(){
        return this.belongToPanelName;
    }
    
    public String getName(){
        return this.name ;
    }
    
    public double getPrice(){
        return this.price;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return the availability
     */
    public boolean isAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
