/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package restaurantmanagementapp;

import javax.swing.JOptionPane;

/**
 *
 * @author trong
 */
public class ClockInOutJDialog extends javax.swing.JDialog {

    /**
     * Creates new form ClockInOutJDialog
     */
    private GUI parent;
    public ClockInOutJDialog(GUI parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        this.setLocationRelativeTo(null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButtonKeyIn = new javax.swing.JButton();
        jButtonKeyOut = new javax.swing.JButton();
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
        jButtonClear = new javax.swing.JButton();
        jButtonViewTime = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic Medium", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Employee");

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

        jButtonKeyIn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonKeyIn.setText("Clock In");
        jButtonKeyIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyInActionPerformed(evt);
            }
        });

        jButtonKeyOut.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonKeyOut.setText("Clock Out");
        jButtonKeyOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKeyOutActionPerformed(evt);
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

        jButtonClear.setText("Clear");
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jButtonViewTime.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButtonViewTime.setText("View Time");
        jButtonViewTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonViewTimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordField1))
                        .addGap(33, 33, 33)
                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jButtonKeyIn, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonKeyOut, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonViewTime, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonKeyOut, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonKeyIn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonViewTime, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        getContentPane().add(jPanel7, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonKeyInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKeyInActionPerformed
        String pinEntered = new String(jPasswordField1.getPassword());
        if (!parent.checkValidEmployeePin(pinEntered)){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Invalid PIN. Please try again." ,"Invalid PIN", JOptionPane.ERROR_MESSAGE);
            jPasswordField1.setText("");
        }else{
            Employee currentEmployee = parent.getEmployee(pinEntered);
            //Check already clocked in
            if (currentEmployee.checkAlreadyClockedIn() == true){
                JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Can't clock in. Please clock out before clocking in again." ,"Error", JOptionPane.ERROR_MESSAGE);
                jPasswordField1.setText("");
            }else {
                //Confirm joptionpane
                int input = JOptionPane.showConfirmDialog(null, "Are you clocking in as " + 
                          currentEmployee.getFullName(), "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                jPasswordField1.setText("");
                //If yes
                if (input == 0){
                    String clockIn = currentEmployee.clockIn();
                    JOptionPane.showMessageDialog(null, clockIn, "Successfully clocked in", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            }
        }
    }//GEN-LAST:event_jButtonKeyInActionPerformed

    private void jButtonKeyOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKeyOutActionPerformed
        String pinEntered = new String(jPasswordField1.getPassword());
        if (!parent.checkValidEmployeePin(pinEntered)){
            JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Invalid PIN. Please try again." ,"Invalid PIN", JOptionPane.ERROR_MESSAGE);
            jPasswordField1.setText("");
        }else{
            Employee currentEmployee = parent.getEmployee(pinEntered);
            //Check already clocked out
            if (currentEmployee.checkAlreadyClockedIn() == false){
                JOptionPane.showMessageDialog(new javax.swing.JFrame(),
                    "Can't clock out. Please clock in before clocking out again." ,"Error", JOptionPane.ERROR_MESSAGE);
                jPasswordField1.setText("");
            }else {
                //Confirm joptionpane
                int input = JOptionPane.showConfirmDialog(null, "Are you clocking out as " + 
                          currentEmployee.getFullName(), "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION);
                jPasswordField1.setText("");
                //If yes
                if (input == 0){
                    String clockOut = currentEmployee.clockOut();
                    JOptionPane.showMessageDialog(null, clockOut, "Successfully clocked out", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }
            }
        }
        
    }//GEN-LAST:event_jButtonKeyOutActionPerformed

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

    private void jButtonKey8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey8ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "8");
    }//GEN-LAST:event_jButtonKey8ActionPerformed

    private void jButtonKey9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey9ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "9");
    }//GEN-LAST:event_jButtonKey9ActionPerformed

    private void jButtonKey0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKey0ActionPerformed
        String pwd = new String(jPasswordField1.getPassword());
        jPasswordField1.setText(pwd + "0");
    }//GEN-LAST:event_jButtonKey0ActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        jPasswordField1.setText("");
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonViewTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonViewTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonViewTimeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ClockInOutJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClockInOutJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClockInOutJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClockInOutJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClockInOutJDialog dialog = new ClockInOutJDialog(new GUI(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
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
    private javax.swing.JButton jButtonKeyIn;
    private javax.swing.JButton jButtonKeyOut;
    private javax.swing.JButton jButtonViewTime;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPasswordField jPasswordField1;
    // End of variables declaration//GEN-END:variables
}
