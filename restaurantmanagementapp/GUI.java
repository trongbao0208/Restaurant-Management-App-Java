/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package restaurantmanagementapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author trong
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI
     */
    
    //currentSelectedTable
    private String currentSelectedTable = "";
    
    //Tree Map to store orders<String, Order>
    SortedMap<String, Order> ordersSortedMap;
    
    //Hash Map to store orders panel<String, JPanel>
    HashMap<String, JPanel> orderPanelsHashMap;
    
    //ArrayList to store the tables
    private ArrayList<JButtonWithToString> tableButtonsArrayList;
    
    //ArrayList that stores the name of all the items in the menu to avoid repeated items
    private HashMap<String, Item> foodItemsHashMap;
    
    
    //List model to control the item 
    private DefaultListModel<Item> listModel;
    
    //HashMap that store order for each table
    private HashMap<String, Order> tableOrderHashMap;
    
    //Array stores all order
    private ArrayList<Order> orderArray;
    
    //HashMap store PIN:Employee 
    private HashMap<String, Employee> employeeList;
    
    //ArrayList Employee
    private ArrayList<Employee> employeeArrayList;
    
    //ArrayList JPanelMenu
    private ArrayList<CreatePanelMenu> panelMenuArrayList;
    
    //HashMap JPanel menu
    private HashMap<String, CreatePanelMenu> panelMenuHashMap;

    private int orderNumber;
    private Order currentOrder;
    
    private int checkBoxOpenControl;
    private int checkBoxClosedControl;
    
    //CardLayout control variable
    CardLayout cardLayout;
    CardLayout cardLayoutBig;
    
    //Admin Password
    private final String ADMINPASSWORD = "1234";
    
    
   
    public GUI()  {
        initComponents();
         
        
        //Retrieve employeeArrayList<Employee>
        try (FileInputStream fis = new FileInputStream("EmployeeArrayList.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
        // read object from file
        employeeArrayList = (ArrayList<Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            employeeArrayList = new ArrayList<>();
        }
        
        //Retrieve employeeList<String, Employee>
        try (FileInputStream fis = new FileInputStream("EmployeeHashMap.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
        // read object from file
        employeeList = (HashMap<String, Employee>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            employeeList = new HashMap<>();
        }
        
        
        //Retrieve the array that store all the table tableButtonArrayList<JButtonWithToString>
        try (FileInputStream fis = new FileInputStream("tableButtonsArrayList.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
        // read object from file
            tableButtonsArrayList = (ArrayList<JButtonWithToString>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            tableButtonsArrayList = new ArrayList<>();
        }
        
        //Retrieve the HashMap that store the menu item HashMap<String, Item>
        try (FileInputStream fis = new FileInputStream("FoodItemsHashMap.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
        // read object from file
            foodItemsHashMap = (HashMap<String, Item>) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            foodItemsHashMap = new HashMap<>();
        }
        
        
        //Add back all the tables
        for(int i = 0; i < tableButtonsArrayList.size(); i++){
            JButtonWithToString newJButton = tableButtonsArrayList.get(i);
            jPanelTables.add(newJButton);
            newJButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    GUI.this.tableSelected(newJButton.getText());
                    cardLayoutBig.show(jPanelMenuOrTableOrOrder, "food");
                    jButtonDone.setVisible(true);
                }
            });
        }
        jPanelInputOrder.setPreferredSize(new Dimension(188, 520));
        //Initialize ordersSortedMap
        ordersSortedMap = new TreeMap<>();
        
        
        orderPanelsHashMap = new HashMap<>();
        
        //Initialized checkbox control
        checkBoxOpenControl = 1;
        checkBoxClosedControl = 0;
        
        orderNumber = 0;
        
        //list model for order
        listModel = new DefaultListModel<>();
        
        //Hashmap that store table-order (Key-Value)
        tableOrderHashMap = new HashMap<>();
      
        //Array that stores the list of orders
        orderArray = new ArrayList<>();
        jList1.setModel(listModel);
        
        //Set the default card to login screen
        //Remeber to change the name of card in the property of the panel
        cardLayout = (CardLayout)(pnlCards.getLayout());
        cardLayout.show(pnlCards, "pnlCardOpen");
       // cardLayout.show(pnlCards, "pnlCardMain");
        
       
        
       
        
        
        this.setLocationRelativeTo(null);
        
        //create jPanelFoodMenu
        jPanelFoodMenu.setLayout(new BoxLayout(jPanelFoodMenu, BoxLayout.Y_AXIS));
        CreatePanelMenu beveragePanel = new CreatePanelMenu("Beverages");
        CreatePanelMenu appetizerPanel = new CreatePanelMenu("Appetizers");
        CreatePanelMenu entreePanel = new CreatePanelMenu("Entrees");
        
        //Add the panels to hashmap
        panelMenuHashMap = new HashMap<String, CreatePanelMenu>();
        panelMenuHashMap.put(beveragePanel.getPanelName(), beveragePanel);
        panelMenuHashMap.put(appetizerPanel.getPanelName(), appetizerPanel);
        panelMenuHashMap.put(entreePanel.getPanelName(), entreePanel);
    
        //Add the panels to arraylist 
        panelMenuArrayList = new ArrayList<CreatePanelMenu>();
        panelMenuArrayList.add(beveragePanel);
        panelMenuArrayList.add(appetizerPanel);
        panelMenuArrayList.add(entreePanel);
        
        
        JLabel labelMenu = new JLabel("Food menu");
        
        beveragePanel.setPreferredSize(new Dimension(jPanelFoodMenu.getWidth() - 5, jPanelFoodMenu.getHeight()/4));
        appetizerPanel.setPreferredSize(new Dimension(jPanelFoodMenu.getWidth() - 5, jPanelFoodMenu.getHeight()/4 ));
        entreePanel.setPreferredSize(new Dimension(jPanelFoodMenu.getWidth() - 5, jPanelFoodMenu.getHeight()/4));
        
        labelMenu.setAlignmentX(CENTER_ALIGNMENT);
        labelMenu.setFont(new Font("Courier", Font.BOLD,20));
        
        //Add panels and label to the big panel
        jPanelFoodMenu.add(labelMenu);
        jPanelFoodMenu.add(beveragePanel);
        jPanelFoodMenu.add(appetizerPanel);
        jPanelFoodMenu.add(entreePanel);
        
        //Add back all the menu items to the panel
        if(foodItemsHashMap.size() > 0){
            for (Item value : foodItemsHashMap.values()) {
                CreatePanelMenu currentCreatePanelMenu = panelMenuHashMap.get(value.getBelongToPanelName());
                currentCreatePanelMenu.add(value);
                value.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //Check if the table is selected first
                    if(currentOrder == null){
                        JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Please select a table first","Error",
                        JOptionPane.ERROR_MESSAGE);
                    }else{
                        currentOrder.getItems().add(value);
                        listModel.addElement(value);
                        currentOrder.addToTotalBill(value.getPrice());
                        double totalBill = currentOrder.getTotalBill();
                        jTotalBill.setText(Double.toString(totalBill));
                    }
                    }
                });
            }
        }
        
        
         //Set the default card to show the table
         //Remeber to change the name of card in the property of the panel
         // food or table card 
        cardLayoutBig = (CardLayout)jPanelMenuOrTableOrOrder.getLayout();
        cardLayoutBig.show(jPanelMenuOrTableOrOrder, "table");
        
        
        jButtonDone.setVisible(false);
        
        this.addWindowListener(new WindowHandler());
        
        //Set WrapLayout for orders panel to enable scrolling vertically
        jPanelListOfOrders.setLayout(new WrapLayout(FlowLayout.LEFT));
        
        //hide the pane when the app start
        this.hideOrderPane();
        
        jButtonRemoveItem.setEnabled(false);
        
        jList1.addMouseListener(new ListMouseListener());
        jButtonShowOrders.setText("<html>Show<br>Orders</html>");
        jButtonClockInOut.setText("<html>Clock In Clock Out</html>");
        jButtonTogoOrder.setText("<html>Togo<br>Order</html>");
        
        AddTableButton.setText("<html>Add table</html>");
        RemoveTableButton.setText("<html>Remove Table</html>");
        AddEmployeeButton.setText("Add Employee");
        RemoveEmployeeButton.setText("<html>Remove<br>Employee</html>");
        AddItemButton.setText("Add Item");
        RemoveItemButton.setText("Remove Item");
       
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelMain = new javax.swing.JPanel();
        jPanelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlCards = new javax.swing.JPanel();
        pnlCardOpen = new javax.swing.JPanel();
        jPanelPinPad = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonKeyClear = new javax.swing.JButton();
        jButtonKeySignIn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonKey1 = new javax.swing.JButton();
        jButtonKey2 = new javax.swing.JButton();
        jButtonKey3 = new javax.swing.JButton();
        jButtonKey4 = new javax.swing.JButton();
        jButtonKey5 = new javax.swing.JButton();
        jButtonKey6 = new javax.swing.JButton();
        jButtonKey7 = new javax.swing.JButton();
        jButtonKey8 = new javax.swing.JButton();
        jButtonKey9 = new javax.swing.JButton();
        jButtonKey0 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        pnlCardMain = new javax.swing.JPanel();
        jPanelInputOrder = new javax.swing.JPanel();
        jLabelOrderName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButtonSendOrder = new javax.swing.JButton();
        jLabelOrderNumber = new javax.swing.JLabel();
        jLabelBillText = new javax.swing.JLabel();
        jTotalBill = new javax.swing.JLabel();
        jLabelDollarSign = new javax.swing.JLabel();
        jButtonPay = new javax.swing.JButton();
        jButtonCancelOrder = new javax.swing.JButton();
        jButtonRemoveItem = new javax.swing.JButton();
        jPanelMenuOrTableOrOrder = new javax.swing.JPanel();
        jPanelTables = new javax.swing.JPanel();
        jPanelOrders = new javax.swing.JPanel();
        jScrollPaneOrders = new javax.swing.JScrollPane();
        jPanelListOfOrders = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanelFoodMenu = new javax.swing.JPanel();
        jPanelRegularFunctionalities = new javax.swing.JPanel();
        jButtonShowOrders = new javax.swing.JButton();
        jButtonClockInOut = new javax.swing.JButton();
        jButtonTogoOrder = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jButtonClose = new javax.swing.JButton();
        jButtonDone = new javax.swing.JButton();
        pnlCardAdminOptions = new javax.swing.JPanel();
        jPanelAddorRemove = new javax.swing.JPanel();
        RemoveEmployeeButton = new javax.swing.JButton();
        AddEmployeeButton = new javax.swing.JButton();
        RemoveItemButton = new javax.swing.JButton();
        AddItemButton = new javax.swing.JButton();
        RemoveTableButton = new javax.swing.JButton();
        AddTableButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        jPanelMain.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanelTitle.setBackground(new java.awt.Color(0, 0, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Restaurant Management App");

        javax.swing.GroupLayout jPanelTitleLayout = new javax.swing.GroupLayout(jPanelTitle);
        jPanelTitle.setLayout(jPanelTitleLayout);
        jPanelTitleLayout.setHorizontalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTitleLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTitleLayout.setVerticalGroup(
            jPanelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
        );

        pnlCards.setLayout(new java.awt.CardLayout());

        pnlCardOpen.setForeground(new java.awt.Color(255, 255, 204));

        jLabel5.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Admin Login");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Pin:");

        jButtonKeyClear.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonKeyClear.setText("Clear");
        jButtonKeyClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyClearActionPerformed(evt);
            }
        });

        jButtonKeySignIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonKeySignIn.setText("Sign in");
        jButtonKeySignIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeySignInActionPerformed(evt);
            }
        });

        jButtonKey1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey1.setText("1");
        jButtonKey1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey1ActionPerformed(evt);
            }
        });

        jButtonKey2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey2.setText("2");
        jButtonKey2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey2ActionPerformed(evt);
            }
        });

        jButtonKey3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey3.setText("3");
        jButtonKey3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey3ActionPerformed(evt);
            }
        });

        jButtonKey4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey4.setText("4");
        jButtonKey4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey4ActionPerformed(evt);
            }
        });

        jButtonKey5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey5.setText("5");
        jButtonKey5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey5ActionPerformed(evt);
            }
        });

        jButtonKey6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey6.setText("6");
        jButtonKey6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey6ActionPerformed(evt);
            }
        });

        jButtonKey7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey7.setText("7");
        jButtonKey7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey7ActionPerformed(evt);
            }
        });

        jButtonKey8.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey8.setText("8");
        jButtonKey8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey8ActionPerformed(evt);
            }
        });

        jButtonKey9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey9.setText("9");
        jButtonKey9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey9ActionPerformed(evt);
            }
        });

        jButtonKey0.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButtonKey0.setText("0");
        jButtonKey0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKey0ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonKey1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonKey5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKey8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKey2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonKey3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKey6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonKey9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonKey0, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKey1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKey4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKey8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKey9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonKey0, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jLabel7.setText("Pin = 1234");

        javax.swing.GroupLayout jPanelPinPadLayout = new javax.swing.GroupLayout(jPanelPinPad);
        jPanelPinPad.setLayout(jPanelPinPadLayout);
        jPanelPinPadLayout.setHorizontalGroup(
            jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelPinPadLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelPinPadLayout.createSequentialGroup()
                        .addComponent(jButtonKeyClear, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jButtonKeySignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPasswordField1))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanelPinPadLayout.setVerticalGroup(
            jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPinPadLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addGap(18, 18, 18)
                .addGroup(jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKeySignIn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKeyClear, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanelPinPadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout pnlCardOpenLayout = new javax.swing.GroupLayout(pnlCardOpen);
        pnlCardOpen.setLayout(pnlCardOpenLayout);
        pnlCardOpenLayout.setHorizontalGroup(
            pnlCardOpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCardOpenLayout.createSequentialGroup()
                .addContainerGap(109, Short.MAX_VALUE)
                .addComponent(jPanelPinPad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );
        pnlCardOpenLayout.setVerticalGroup(
            pnlCardOpenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardOpenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelPinPad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCardOpen, "pnlCardOpen");

        pnlCardMain.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanelInputOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabelOrderName.setText("Order Name: ");

        jScrollPane1.setViewportView(jList1);

        jButtonSendOrder.setText("Send");
        jButtonSendOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSendOrderActionPerformed(evt);
            }
        });

        jLabelOrderNumber.setText("Order number");

        jLabelBillText.setText("Total bill:");

        jTotalBill.setText("amount");

        jLabelDollarSign.setText("$");

        jButtonPay.setText("Pay");
        jButtonPay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayActionPerformed(evt);
            }
        });

        jButtonCancelOrder.setText("Cancel Order");
        jButtonCancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelOrderActionPerformed(evt);
            }
        });

        jButtonRemoveItem.setText("Remove Item");
        jButtonRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelInputOrderLayout = new javax.swing.GroupLayout(jPanelInputOrder);
        jPanelInputOrder.setLayout(jPanelInputOrderLayout);
        jPanelInputOrderLayout.setHorizontalGroup(
            jPanelInputOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputOrderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanelInputOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrderNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelOrderName, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelInputOrderLayout.createSequentialGroup()
                        .addComponent(jLabelBillText, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelDollarSign)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTotalBill))
                    .addComponent(jButtonRemoveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelInputOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonSendOrder, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jButtonCancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelInputOrderLayout.setVerticalGroup(
            jPanelInputOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelInputOrderLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabelOrderName)
                .addGap(18, 18, 18)
                .addComponent(jLabelOrderNumber)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonRemoveItem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(jPanelInputOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDollarSign)
                    .addComponent(jTotalBill)
                    .addComponent(jLabelBillText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSendOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelOrder)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPay, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelMenuOrTableOrOrder.setLayout(new java.awt.CardLayout());

        jPanelTables.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelTablesLayout = new javax.swing.GroupLayout(jPanelTables);
        jPanelTables.setLayout(jPanelTablesLayout);
        jPanelTablesLayout.setHorizontalGroup(
            jPanelTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        jPanelTablesLayout.setVerticalGroup(
            jPanelTablesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        jPanelMenuOrTableOrOrder.add(jPanelTables, "table");

        jPanelOrders.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelListOfOrdersLayout = new javax.swing.GroupLayout(jPanelListOfOrders);
        jPanelListOfOrders.setLayout(jPanelListOfOrdersLayout);
        jPanelListOfOrdersLayout.setHorizontalGroup(
            jPanelListOfOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        jPanelListOfOrdersLayout.setVerticalGroup(
            jPanelListOfOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 513, Short.MAX_VALUE)
        );

        jScrollPaneOrders.setViewportView(jPanelListOfOrders);

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Order");
        jLabel4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelOrdersLayout = new javax.swing.GroupLayout(jPanelOrders);
        jPanelOrders.setLayout(jPanelOrdersLayout);
        jPanelOrdersLayout.setHorizontalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPaneOrders)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelOrdersLayout.setVerticalGroup(
            jPanelOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelOrdersLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanelMenuOrTableOrOrder.add(jPanelOrders, "order");

        jPanelFoodMenu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanelFoodMenuLayout = new javax.swing.GroupLayout(jPanelFoodMenu);
        jPanelFoodMenu.setLayout(jPanelFoodMenuLayout);
        jPanelFoodMenuLayout.setHorizontalGroup(
            jPanelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 492, Short.MAX_VALUE)
        );
        jPanelFoodMenuLayout.setVerticalGroup(
            jPanelFoodMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
        );

        jPanelMenuOrTableOrOrder.add(jPanelFoodMenu, "food");

        jPanelRegularFunctionalities.setLayout(new java.awt.GridLayout(1, 0));

        jButtonShowOrders.setText("Show Orders");
        jButtonShowOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowOrdersActionPerformed(evt);
            }
        });
        jPanelRegularFunctionalities.add(jButtonShowOrders);

        jButtonClockInOut.setText("Employee");
        jButtonClockInOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClockInOutActionPerformed(evt);
            }
        });
        jPanelRegularFunctionalities.add(jButtonClockInOut);

        jButtonTogoOrder.setText("Togo Order");
        jButtonTogoOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTogoOrderActionPerformed(evt);
            }
        });
        jPanelRegularFunctionalities.add(jButtonTogoOrder);

        jButton2.setText("Admin");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonClose.setBackground(new java.awt.Color(255, 0, 51));
        jButtonClose.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClose.setText("Close");
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonDone.setText("Done");
        jButtonDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDoneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDone, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButtonDone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButtonClose, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout pnlCardMainLayout = new javax.swing.GroupLayout(pnlCardMain);
        pnlCardMain.setLayout(pnlCardMainLayout);
        pnlCardMainLayout.setHorizontalGroup(
            pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelInputOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCardMainLayout.createSequentialGroup()
                        .addComponent(jPanelRegularFunctionalities, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelMenuOrTableOrOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        pnlCardMainLayout.setVerticalGroup(
            pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardMainLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelMenuOrTableOrOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelInputOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(8, 8, 8)
                .addGroup(pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCardMainLayout.createSequentialGroup()
                        .addGroup(pnlCardMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelRegularFunctionalities, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(25, Short.MAX_VALUE))
                    .addGroup(pnlCardMainLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pnlCards.add(pnlCardMain, "pnlCardMain");

        jPanelAddorRemove.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelAddorRemove.setLayout(new java.awt.GridBagLayout());

        RemoveEmployeeButton.setBackground(new java.awt.Color(255, 102, 102));
        RemoveEmployeeButton.setForeground(new java.awt.Color(255, 255, 255));
        RemoveEmployeeButton.setText("jButton1");
        RemoveEmployeeButton.setPreferredSize(new java.awt.Dimension(120, 120));
        RemoveEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveEmployeeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(RemoveEmployeeButton, gridBagConstraints);

        AddEmployeeButton.setBackground(new java.awt.Color(153, 255, 153));
        AddEmployeeButton.setText("jButton2");
        AddEmployeeButton.setPreferredSize(new java.awt.Dimension(120, 120));
        AddEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddEmployeeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(AddEmployeeButton, gridBagConstraints);

        RemoveItemButton.setBackground(new java.awt.Color(255, 102, 102));
        RemoveItemButton.setForeground(new java.awt.Color(255, 255, 255));
        RemoveItemButton.setText("jButton2");
        RemoveItemButton.setPreferredSize(new java.awt.Dimension(120, 120));
        RemoveItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(RemoveItemButton, gridBagConstraints);

        AddItemButton.setBackground(new java.awt.Color(153, 255, 153));
        AddItemButton.setText("jButton2");
        AddItemButton.setPreferredSize(new java.awt.Dimension(120, 120));
        AddItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddItemButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(AddItemButton, gridBagConstraints);

        RemoveTableButton.setBackground(new java.awt.Color(255, 102, 102));
        RemoveTableButton.setForeground(new java.awt.Color(255, 255, 255));
        RemoveTableButton.setText("jButton2");
        RemoveTableButton.setPreferredSize(new java.awt.Dimension(120, 120));
        RemoveTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveTableButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(RemoveTableButton, gridBagConstraints);

        AddTableButton.setBackground(new java.awt.Color(153, 255, 153));
        AddTableButton.setText("jButton2");
        AddTableButton.setPreferredSize(new java.awt.Dimension(120, 120));
        AddTableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTableButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanelAddorRemove.add(AddTableButton, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Add/Remove");
        jLabel2.setPreferredSize(new java.awt.Dimension(160, 57));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        jPanelAddorRemove.add(jLabel2, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 204));
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCardAdminOptionsLayout = new javax.swing.GroupLayout(pnlCardAdminOptions);
        pnlCardAdminOptions.setLayout(pnlCardAdminOptionsLayout);
        pnlCardAdminOptionsLayout.setHorizontalGroup(
            pnlCardAdminOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardAdminOptionsLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jPanelAddorRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCardAdminOptionsLayout.createSequentialGroup()
                .addContainerGap(293, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302))
        );
        pnlCardAdminOptionsLayout.setVerticalGroup(
            pnlCardAdminOptionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCardAdminOptionsLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanelAddorRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pnlCards.add(pnlCardAdminOptions, "pnlCardAdmin");

        javax.swing.GroupLayout jPanelMainLayout = new javax.swing.GroupLayout(jPanelMain);
        jPanelMain.setLayout(jPanelMainLayout);
        jPanelMainLayout.setHorizontalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelMainLayout.setVerticalGroup(
            jPanelMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMainLayout.createSequentialGroup()
                .addComponent(jPanelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCards, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public ArrayList<JButtonWithToString> getTableButtonsArrayList(){
        return tableButtonsArrayList;
    }
    
    public JPanel getJPanelTables(){
        return jPanelTables;
    }
    
    public HashMap<String, Item> getFoodItemsHashMap(){
        return foodItemsHashMap;
    }
    
    public void addButtonToMenu (CreatePanelMenu panel, Item buttonItem){
        panel.add(buttonItem);
    }
    
    public ArrayList<CreatePanelMenu> getPanelMenuArrayList(){
        return panelMenuArrayList;
    }
    
    public Employee getEmployee(String pin){
        return employeeList.get(pin);
    }
    
    public String clockIn(String pin){
        Employee currentEmployee = employeeList.get(pin);
        return "";
    }
    
    public boolean checkValidEmployeePin(String pin){
        return employeeList.containsKey(pin);
    }
    
    public HashMap<String, Employee> getEmployeeHashMap(){
        return employeeList;
    }
    
    public ArrayList<Employee> getEmployeeArrayList(){
        return employeeArrayList;
    }
    
    public boolean checkCorrectAdminPassword(String password){
        return ADMINPASSWORD.equals(password);
    }
    
    public void registerNewEmployee (String firstName, String lastName, String pin){
        Employee newEmployee = new Employee(firstName, lastName, pin);
        employeeList.put(pin, newEmployee);
        employeeArrayList.add(newEmployee);
    }
    
    
    public boolean checkEmployeePINTaken(String pin){
        return employeeList.containsKey(pin);
    }
    
    public void removeEmployee(String id, int index){
        employeeList.remove(id);
        employeeArrayList.remove(index);
    }
       
    public Order getCurrentOrder(){
        return this.currentOrder;
    }
    
    public DefaultListModel<Item> getCurrentListModel() {
        return listModel;
    }
    
    public JLabel getTotalBillLabel(){
        return jTotalBill;
    }
    
    public void tableSelected (String tableName){
        showOrderPane();
        currentSelectedTable = tableName;
        jLabelOrderName.setText("Order name: " + tableName);
        //jPanelFoodMenu.setVisible(true);
        //Check if the tableOrder hashmap contain the table
        //If not
        
        if (!tableOrderHashMap.containsKey(tableName)){
            listModel.clear();
            Order newTableOrder = new Order(orderNumber, tableName, "Here");
            currentOrder = newTableOrder;
            tableOrderHashMap.put(tableName, newTableOrder);
            //Put the order in treemap
            ordersSortedMap.put(Integer.toString(orderNumber), newTableOrder);
            orderNumber += 1;
            jLabelOrderNumber.setText("Order number: " + Integer.toString(newTableOrder.getOrderNumber()));
            jTotalBill.setText(Double.toString(currentOrder.getTotalBill()));
        //If yes
        }else{
            this.currentOrder = tableOrderHashMap.get(tableName);
            listModel.clear();
            for (int i = 0; i < currentOrder.getItems().size(); i++){
                listModel.addElement(currentOrder.getItems().get(i));
            }
            jLabelOrderNumber.setText("Order number: " + Integer.toString(currentOrder.getOrderNumber()));
            jTotalBill.setText(Double.toString(currentOrder.getTotalBill()));
        }
        
    }
    
    private void jButtonKey8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey8ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "8");
    }//GEN-LAST:event_jButtonKey8ActionPerformed

    private void jButtonKey1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey1ActionPerformed
        // TODO add your handling code here:
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "1");
    }//GEN-LAST:event_jButtonKey1ActionPerformed

    private void jButtonKey2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey2ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "2");
    }//GEN-LAST:event_jButtonKey2ActionPerformed

    private void jButtonKey3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey3ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "3");
    }//GEN-LAST:event_jButtonKey3ActionPerformed

    private void jButtonKey4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey4ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "4");
    }//GEN-LAST:event_jButtonKey4ActionPerformed

    private void jButtonKey5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey5ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "5");
    }//GEN-LAST:event_jButtonKey5ActionPerformed

    private void jButtonKey6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey6ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "6");
    }//GEN-LAST:event_jButtonKey6ActionPerformed

    private void jButtonKey7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey7ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "7");
    }//GEN-LAST:event_jButtonKey7ActionPerformed

    private void jButtonKey9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey9ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "9");
    }//GEN-LAST:event_jButtonKey9ActionPerformed

    private void jButtonKey0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey0ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "0");
    }//GEN-LAST:event_jButtonKey0ActionPerformed

    private void jButtonKeyClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKeyClearActionPerformed
        jPasswordField1.setText("");
    }//GEN-LAST:event_jButtonKeyClearActionPerformed

    private void jButtonKeySignInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKeySignInActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        if(pwd.equals(ADMINPASSWORD)){
            cardLayout.show(pnlCards, "pnlCardMain");
        }else{
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Incorrect PIN. Please try again." ,"Incorrect PIN", JOptionPane.ERROR_MESSAGE);
            jPasswordField1.setText("");
        }
    }//GEN-LAST:event_jButtonKeySignInActionPerformed

    private void jButtonClockInOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClockInOutActionPerformed
        ClockInOutJDialog newDialog = new ClockInOutJDialog(this, true);
        newDialog.setVisible(true);
    }//GEN-LAST:event_jButtonClockInOutActionPerformed

    private void jButtonSendOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSendOrderActionPerformed
        // TODO add your handling code here:
        
        if(currentOrder != null){
            CreatePDF newPDF = new CreatePDF(this);
            try {
                String createPDF = newPDF.createPDFFile(currentOrder.getItems(), jLabelOrderName.getText());
                System.out.println(createPDF);
            }catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Please select a table before sending." ,"Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_jButtonSendOrderActionPerformed

    private void jButtonDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDoneActionPerformed
        // TODO add your handling code here:
        cardLayoutBig.show(jPanelMenuOrTableOrOrder, "table");
        jButtonDone.setVisible(false);
    }//GEN-LAST:event_jButtonDoneActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        //Write employeeArrayList<Employee> file
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EmployeeArrayList.dat"))){
            oos.writeObject(employeeArrayList);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Write employeeList<String, Employee> file
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EmployeeHashMap.dat"))){
            oos.writeObject(employeeList);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Write TableButtonsArrayList<JbuttonWithToString>
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TableButtonsArrayList.dat"))){
            oos.writeObject(tableButtonsArrayList);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Write HashMap<String, Item> foodItemsHashMap to file
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FoodItemsHashMap.dat"))){
            oos.writeObject(foodItemsHashMap);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
        System.exit(0);
        
    }//GEN-LAST:event_jButtonCloseActionPerformed
    
    public void displayOrders(){
        jPanelListOfOrders.removeAll();
            orderPanelsHashMap.clear();
            
            jButtonDone.setVisible(true);

            ordersSortedMap.forEach((key, value) -> {
                //Create JPanel
                
                if(value.getPaid() == false){
                    JPanel newJPanel = new JPanel();
                    newJPanel.setName(key);
                    newJPanel.setLayout(new BoxLayout(newJPanel, BoxLayout.Y_AXIS));
                    newJPanel.setPreferredSize(new Dimension(100, 100));
                    Border blackline = BorderFactory.createLineBorder(Color.black);
                    newJPanel.setBorder(blackline);

                    JLabel table = new JLabel();
                    table.setText("Name: " + value.getName());
                    JLabel order = new JLabel();
                    order.setText("Order #: " + value.getOrderNumber());
                    JLabel price = new JLabel();
                    price.setText("Total: " + value.getTotalBill());
                    JLabel orderType = new JLabel();
                    String orderTypeColor;
                    if(value.getOrderType().equals("Here")){
                        orderTypeColor = "<font color='orange'>Here</font>";
                    }else{
                        orderTypeColor = "<font color='blue'>Togo</font>";
                    }
                    orderType.setText("<html>Type: "+ orderTypeColor + "</html>");
                    newJPanel.add(table);
                    newJPanel.add(order);
                    newJPanel.add(price);
                    newJPanel.add(orderType);

                    orderPanelsHashMap.put(newJPanel.getName(), newJPanel);
                    //Mouse listener for hovering
                    newJPanel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseEntered(MouseEvent evt) {
                            JPanel parent = (JPanel) evt.getSource();
                            parent.setBackground(new Color(204, 255, 204));
                        }

                        @Override
                        public void mouseExited(MouseEvent evt) {
                            JPanel parent = (JPanel) evt.getSource();
                            parent.setBackground(null);
                        }

                        @Override
                        public void mousePressed(MouseEvent evt) {
                            showOrderPane();
                            JPanel parent = (JPanel) evt.getSource();
                            Order selectedOrder = ordersSortedMap.get(parent.getName());
                            GUI.this.currentOrder = selectedOrder;
                            GUI.this.listModel.clear();
                            for (int i = 0; i < GUI.this.currentOrder.getItems().size(); i++) {
                                GUI.this.listModel.addElement(currentOrder.getItems().get(i));
                            }
                            jLabelOrderNumber.setText("Order number: " + Integer.toString(currentOrder.getOrderNumber()));
                            jLabelOrderName.setText("Order name: " + currentOrder.getName());
                            jTotalBill.setText(Double.toString(currentOrder.getTotalBill()));
                        }
                    });
                    jPanelListOfOrders.add(newJPanel);
                }
                
            });

            jPanelListOfOrders.revalidate();
            jPanelListOfOrders.repaint();
            // If only the closed box is checked
    }
    
    private void jButtonShowOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowOrdersActionPerformed
        
        this.displayOrders();
        cardLayoutBig.show(jPanelMenuOrTableOrOrder, "order");
        
    }//GEN-LAST:event_jButtonShowOrdersActionPerformed

    private void jButtonPayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayActionPerformed
        // TODO add your handling code here:
        if(currentOrder == null){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Can't pay because no order is selected", "Error",
            JOptionPane.ERROR_MESSAGE);
        }else if(currentOrder.getTotalBill() == 0){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Can't pay because total bill is $0, please"
                    + " select cancel order instead.", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
        else{
            currentOrder.payOrder();
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "$" + currentOrder.getTotalBill() + 
                    " has been paid successfully.","Successful",
                        JOptionPane.INFORMATION_MESSAGE);
            currentOrder = null;
            this.hideOrderPane();
            if(tableOrderHashMap.containsKey(currentSelectedTable)){
                tableOrderHashMap.remove(currentSelectedTable);
            }
            cardLayoutBig.show(jPanelMenuOrTableOrOrder, "table");
            this.displayOrders();
            
        }
    }//GEN-LAST:event_jButtonPayActionPerformed

    private void jButtonCancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelOrderActionPerformed
        if(currentOrder == null){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Can't cancel because no order is selected", "Error",
            JOptionPane.ERROR_MESSAGE);
        }
        else{
            int result = JOptionPane.showConfirmDialog(new javax.swing.JFrame(),"Are you sure you want to"
                    + " cancel this order?", "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
                int orderNumber = currentOrder.getOrderNumber();
                JOptionPane.showMessageDialog(new javax.swing.JFrame(), "The order has been canceled", "Successful",
                        JOptionPane.INFORMATION_MESSAGE);
                ordersSortedMap.remove(Integer.toString(orderNumber));
                currentOrder = null;
                this.hideOrderPane();
                
                if (tableOrderHashMap.containsKey(currentSelectedTable)) {
                    tableOrderHashMap.remove(currentSelectedTable);
                }
                cardLayoutBig.show(jPanelMenuOrTableOrOrder, "table");
                this.displayOrders();   
            }
        }
        
    }//GEN-LAST:event_jButtonCancelOrderActionPerformed

    private void jButtonRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveItemActionPerformed
        // TODO add your handling code here:
       if(jList1.getSelectedIndex() == -1){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), "Please select an item before removing it","Error",
            JOptionPane.ERROR_MESSAGE);
        }else{
            Item selectedItem = listModel.get(jList1.getSelectedIndex());
            double itemPrice = selectedItem.getPrice();
            ArrayList<Item> currentArrayList = currentOrder.getItems();
            currentArrayList.remove(selectedItem);
            listModel.remove(jList1.getSelectedIndex());
            currentOrder.subtractFromTotalBill(itemPrice);
            jTotalBill.setText(Double.toString(currentOrder.getTotalBill()));
            this.displayOrders();

            if (GUI.this.listModel.isEmpty()) {
                GUI.this.jButtonRemoveItem.setEnabled(false);
            }
        }
       
        
    }//GEN-LAST:event_jButtonRemoveItemActionPerformed

    private void jButtonTogoOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTogoOrderActionPerformed
        // TODO add your handling code here:
        String orderName;
        orderName = JOptionPane.showInputDialog("Order name:");
        if(orderName != null){
            listModel.clear();
            Order newOrder = new Order(orderNumber, orderName, "Togo");
            currentOrder = newOrder;

            //Put the order in treemap
            ordersSortedMap.put(Integer.toString(orderNumber), currentOrder);
            orderNumber += 1;
            jLabelOrderNumber.setText("Order number: " + Integer.toString(newOrder.getOrderNumber()));
            jTotalBill.setText(Double.toString(currentOrder.getTotalBill()));
            this.showOrderPane();
            jLabelOrderName.setText("Order name: " + orderName);
            cardLayoutBig.show(jPanelMenuOrTableOrOrder, "food");
        }
        
    }//GEN-LAST:event_jButtonTogoOrderActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cardLayout.show(pnlCards, "pnlCardMain");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RemoveEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveEmployeeButtonActionPerformed

        RemoveEmployeeJDialog newJDialog = new RemoveEmployeeJDialog(this, true);
        newJDialog.setVisible(true);
        
    }//GEN-LAST:event_RemoveEmployeeButtonActionPerformed

    private void RemoveItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveItemButtonActionPerformed
       
        RemoveFoodItemJdialog newRemoveFoodItemJdialog = new RemoveFoodItemJdialog(this, true);
        newRemoveFoodItemJdialog.setVisible(true);
      
    }//GEN-LAST:event_RemoveItemButtonActionPerformed

    private void RemoveTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveTableButtonActionPerformed
      
        RemoveTableJdialog newJdialog = new RemoveTableJdialog(this, true);
        newJdialog.setVisible(true);
        
    }//GEN-LAST:event_RemoveTableButtonActionPerformed

    private void AddEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddEmployeeButtonActionPerformed
        NewEmployeeJDialog newJdialog = new NewEmployeeJDialog(this, true);
        newJdialog.setVisible(true);
    }//GEN-LAST:event_AddEmployeeButtonActionPerformed

    private void AddItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddItemButtonActionPerformed

        AddingItemJdialog newJdialog = new AddingItemJdialog(this, true);
        newJdialog.setVisible(true);
    }//GEN-LAST:event_AddItemButtonActionPerformed

    private void AddTableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTableButtonActionPerformed

             //Remove all the tables
            for(int i = 0; i < tableButtonsArrayList.size(); i++){
                jPanelTables.remove(tableButtonsArrayList.get(i));
            }
            
            AdjustTableJDialog newAdjustTableJDialog = new AdjustTableJDialog(this, true);
            newAdjustTableJDialog.setVisible(true);
        
            //Add back all the tables
            for(int i = 0; i < tableButtonsArrayList.size(); i++){
                JButtonWithToString newJButton = tableButtonsArrayList.get(i);
                jPanelTables.add(newJButton);
                newJButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        GUI.this.tableSelected(newJButton.getText());
                        jButtonDone.setVisible(true);
                        cardLayoutBig.show(jPanelMenuOrTableOrOrder, "food");
                }
                });
            }
       
        
        jPanelTables.revalidate();
        jPanelTables.repaint();
    }//GEN-LAST:event_AddTableButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        AdminPIN newAdminPIN = new AdminPIN(this, true);
        newAdminPIN.setVisible(true);
        boolean checkPassword = newAdminPIN.checkCorrectPassword();
        
        if(checkPassword == true){
            cardLayout.show(pnlCards, "pnlCardAdmin");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
   
    public void showOrderPane(){
        jButtonSendOrder.setVisible(true);
        jButtonCancelOrder.setVisible(true);
        jButtonPay.setVisible(true);
        jButtonRemoveItem.setVisible(true);
        jLabelDollarSign.setVisible(true);
        jLabelBillText.setVisible(true);
    }
    
    public void hideOrderPane(){
        listModel.clear();
        jLabelOrderName.setText("");
        jLabelOrderNumber.setText("");
        jTotalBill.setText("");
        jLabelBillText.setVisible(false);
        jLabelDollarSign.setVisible(false);
        jButtonSendOrder.setVisible(false);
        jButtonCancelOrder.setVisible(false);
        jButtonPay.setVisible(false);
        jButtonRemoveItem.setVisible(false);
        jButtonRemoveItem.setEnabled(false);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException  {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
        }});
    }
    
    
    //When the program is closed save all the necessary objects to file
    private class WindowHandler extends WindowAdapter{
        @Override
        public void windowClosing (java.awt.event.WindowEvent a){
            //Write employeeArrayList<Employee> file
            try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EmployeeArrayList.dat"))) {
                oos.writeObject(employeeArrayList);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Write hashmap employeeList<String, Employee> file
            try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("EmployeeHashMap.dat"))) {
                oos.writeObject(employeeList);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Write TableButtonsArrayList<JbuttonWithToString>
            try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("TableButtonsArrayList.dat"))) {
                oos.writeObject(tableButtonsArrayList);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }

            //Write HashMap<String, Item> foodItemsHashMap to file
            try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("FoodItemsHashMap.dat"))) {
                oos.writeObject(foodItemsHashMap);
            } catch (IOException ex) {
                Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    //mouse listener for listmodel
        private class ListMouseListener extends MouseAdapter{
        public void mouseClicked(MouseEvent e) {
            if (GUI.this.listModel.isEmpty()){
                GUI.this.jButtonRemoveItem.setEnabled(false);
                return;
            }
            if (e.getClickCount() == 1){
                GUI.this.jButtonRemoveItem.setEnabled(true);
            }
            }
        }
    

       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddEmployeeButton;
    private javax.swing.JButton AddItemButton;
    private javax.swing.JButton AddTableButton;
    private javax.swing.JButton RemoveEmployeeButton;
    private javax.swing.JButton RemoveItemButton;
    private javax.swing.JButton RemoveTableButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCancelOrder;
    private javax.swing.JButton jButtonClockInOut;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonDone;
    private javax.swing.JButton jButtonKey0;
    private javax.swing.JButton jButtonKey1;
    private javax.swing.JButton jButtonKey2;
    private javax.swing.JButton jButtonKey3;
    private javax.swing.JButton jButtonKey4;
    private javax.swing.JButton jButtonKey5;
    private javax.swing.JButton jButtonKey6;
    private javax.swing.JButton jButtonKey7;
    private javax.swing.JButton jButtonKey8;
    private javax.swing.JButton jButtonKey9;
    private javax.swing.JButton jButtonKeyClear;
    private javax.swing.JButton jButtonKeySignIn;
    private javax.swing.JButton jButtonPay;
    private javax.swing.JButton jButtonRemoveItem;
    private javax.swing.JButton jButtonSendOrder;
    private javax.swing.JButton jButtonShowOrders;
    private javax.swing.JButton jButtonTogoOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelBillText;
    private javax.swing.JLabel jLabelDollarSign;
    private javax.swing.JLabel jLabelOrderName;
    private javax.swing.JLabel jLabelOrderNumber;
    private javax.swing.JList<Item> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelAddorRemove;
    private javax.swing.JPanel jPanelFoodMenu;
    private javax.swing.JPanel jPanelInputOrder;
    private javax.swing.JPanel jPanelListOfOrders;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelMenuOrTableOrOrder;
    private javax.swing.JPanel jPanelOrders;
    private javax.swing.JPanel jPanelPinPad;
    private javax.swing.JPanel jPanelRegularFunctionalities;
    private javax.swing.JPanel jPanelTables;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPaneOrders;
    private javax.swing.JLabel jTotalBill;
    private javax.swing.JPanel pnlCardAdminOptions;
    private javax.swing.JPanel pnlCardMain;
    private javax.swing.JPanel pnlCardOpen;
    private javax.swing.JPanel pnlCards;
    // End of variables declaration//GEN-END:variables
}
