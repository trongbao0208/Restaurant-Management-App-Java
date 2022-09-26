/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package restaurantmanagementapp;

/**
 *
 * @author trong
 */
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ResizableTableButton extends JButton {

    private Point grabbedPoint;
    private Dimension grabbedDimension;

    private boolean isHeld = false;
    
    private int index;
    private JPanel jpanel;
    private ArrayList<ResizableTableButton> arrayList;
    
 

    public ResizableTableButton(String name) {
        super(name);
        addMouseListener(clickListener);
        addMouseMotionListener(moveListener);
    }
    

    private MouseMotionListener moveListener = new MouseMotionAdapter() {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (isHeld) {
                Point newP = e.getPoint();
                setPreferredSize(new Dimension(grabbedDimension.width
                        - (grabbedPoint.x - newP.x), grabbedDimension.height
                        - (grabbedPoint.y - newP.y)));
                setBounds(new Rectangle(getLocation(), ResizableTableButton.this
                        .getPreferredSize()));
            }
        }
    };

    private MouseListener clickListener = new MouseAdapter() {

        @Override
        public void mouseReleased(MouseEvent e) {
            isHeld = false;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            isHeld = true;
            grabbedPoint = e.getPoint();
            grabbedDimension = ((JButton) e.getSource()).getSize();
        }
        
      
    };
}
