/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author trong
 */
public class CreatePanelMenu extends JPanel {
    private String panelName;
    
    public CreatePanelMenu(String panelName){
        this.panelName = panelName;
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createTitledBorder(this.panelName));
        this.setPreferredSize(new Dimension(500, 600));
    }
    
    public String getPanelName(){
        return this.panelName;
    }
    
    public void addButtonToMenu(JButton button){
        this.add(button);
    }
    
    public String toString() {
        return this.panelName;
    }
}
