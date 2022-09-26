/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.io.Serializable;
import javax.swing.JButton;

/**
 *
 * @author trong
 */
public class JButtonWithToString extends JButton implements Serializable{
    String name = this.getText();
    public JButtonWithToString(String name) {
        super(name);
    }
    
    public String toString(){
        return name;
    }
}
