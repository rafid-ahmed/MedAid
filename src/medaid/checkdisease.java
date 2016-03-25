/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medaid;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author Sazib
 */
public class checkdisease extends javax.swing.JFrame {

    /**
     * Creates new form checkdisease
     */
    public checkdisease() throws SQLException {
        initComponents();
        myConn = Conn.getConnection();
        model2 = new DefaultListModel();
        bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/head.png")));
        getSymptoms("Scalp");
        
        jComboBox1.addItemListener(
            new ItemListener(){
                public void itemStateChanged(ItemEvent e) {
                    if(e.getItem().equals("Male")){
                         bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/head.png")));
                        //"/bodyPartIcon/find.png"
                        //Male
                         jComboBox3.setSelectedIndex(0);
                    }else{
                        bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/head_female.png")));
                        jComboBox3.setSelectedIndex(0);
                    }
                }
            }
        );
        
        jComboBox3.addItemListener(
            new ItemListener(){
                public void itemStateChanged(ItemEvent e) {
                    try{
                        if(e.getItem().equals("Head")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                                bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/head.png")));
                            }else{
                                 bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/head_female.png")));
                            }
                            String[] sub = {"Scalp","Eyes","Nose","Mouth"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            getSymptoms("Scalp");
                        }else if(e.getItem().equals("Neck")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                                   bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/neck.png")));//Male Neck
                            }else{
                                  bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/neck_female.png"))); //Female Neck
                            }
                            String[] sub = {"Neck"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            //getSymptoms("Neck");
                        }else if(e.getItem().equals("Chest")){
                            
                            if(jComboBox1.getSelectedItem().equals("Male")){
                               bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/chest.png")));
                                //Male Chest
                                String[] sub = {"Chest","Sternum"};
                                jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            }else{
                                bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/chest_female.png")));//Female Chest
                                String[] sub = {"Chest","Sternum","Lateral chest"};
                                jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            }
                            //getSymptoms("Chest");
                        }else if(e.getItem().equals("Arm")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                              bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/arms.png")));  //Male Arm
                            }else{
                              bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/arms_female.png")));  //Female Arm
                            }
                            String[] sub = {"Shoulder","Armpit","Bicep","Tricep","Tricep","Elbow","Forearm","Wrist","Palm","Fingers"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            //getSymptoms("Shoulder");
                        }else if(e.getItem().equals("Abdomen")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                              bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/abdomen.png")));  //Male Abdomen
                            }else{
                              bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/abdomen_female.png")));  //Female Abdomen
                            }
                            String[] sub = {"Upper Abdomen","Lower Abdomen"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            //getSymptoms("Upper Abdomen");
                        }else if(e.getItem().equals("Pelvis")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                         bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/pelvis.png")));  //Male Abdomen
       //Male Pelvis
                            }else{
                           bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/pelvis_female.png")));  //Male Abdomen
    //Female Pelvis
                            }
                            String[] sub = {"Hip","Pelvis","Groin","Genitals"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            //getSymptoms("Hip");
                        }else if(e.getItem().equals("Legs")){
                            if(jComboBox1.getSelectedItem().equals("Male")){
                               bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/leg.png")));  //Male Legs
                            }else{
                                bodyShow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bodyPartIcon/legs_female.png"))); //Female Legs
                            }
                            String[] sub = {"Thigh","Knee","Shin","Ankle","Foot","Toes"};
                            jComboBox2.setModel(new DefaultComboBoxModel(sub));
                            //getSymptoms("Thigh");
                        }
                    }catch(SQLException ex){
                        Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
        
        jComboBox2.addItemListener(
            new ItemListener(){
                public void itemStateChanged(ItemEvent e) {
                    try {
                        getSymptoms(e.getItem().toString());
                    } catch (SQLException ex) {
                        Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        );
    }

    public static void getSymptoms(String s) throws SQLException{
        PreparedStatement myStmt1 = (PreparedStatement) myConn.prepareStatement("SELECT symptom_range FROM medaid.body_info where body_part=?");
        myStmt1.setString(1, s);
        ResultSet myRs1 = myStmt1.executeQuery();
        ResultSet myRs2;  
        String[] value = {};
        while(myRs1.next())
            value= myRs1.getString("symptom_range").split("-");
        int start = Integer.parseInt(value[0]);
        int end = Integer.parseInt(value[1]);
        String[] data = new String[end-start+1];
        int j=0;
        list = new ArrayList<String>();
        PreparedStatement myStmt2 = (PreparedStatement) myConn.prepareStatement("select symptom_name from medaid.symptom_info where symptom_id=?");
        model1 = new DefaultListModel();
        for(int i=start; i<=end; i++){
            myStmt2.setInt(1, i);
            myRs2 = myStmt2.executeQuery();
            myRs2.next();
            s = myRs2.getString("symptom_name");
            list.add(s);
            if(!model2.contains(s))
                model1.addElement(s);
            j++;
        }
        sort(model1);
        jList1.setModel(model1);
        jList2.setModel(model2);
    }
    
    public static void getDisease(List<String> s, DefaultListModel d) throws SQLException{
        CallableStatement myStmt;
        myStmt = myConn.prepareCall("{call get_disease(?)}");
        int i = 0;

        Set<String> mySet1 = new HashSet<String>();
        Set<String> mySet2 = new HashSet<String>();
        Set<String> mySet3 = new HashSet<String>();
        Set<String> mySet4 = new HashSet<String>();
        Iterator it = s.iterator();
        Iterator it2;
        String str;
        while(it.hasNext()){
            myStmt.setString(1, (String) it.next());
            myStmt.execute();
            ResultSet myRs1 = myStmt.getResultSet();

            while(myRs1.next())
                    mySet2.add(myRs1.getString("disease_name"));

            if(i==0){
                mySet1.addAll(mySet2);
            }
            else{
                mySet3.addAll(mySet1);
                
                mySet1.retainAll(mySet2);
                
                
            }
            mySet4.addAll(mySet2);
            mySet2.clear();
            i++;
        }
        Iterator itr = mySet1.iterator();
        while(itr.hasNext()) 
            d.addElement(itr.next());
        
        it2 = mySet3.iterator();
        while(it2.hasNext()){
            str = (String) it2.next();
            if(!d.contains(str))
                d.addElement(str);
        }

        it2 = mySet4.iterator();
        while(it2.hasNext()){
            str = (String) it2.next();
            if(!d.contains(str))
                d.addElement(str);
        }
        
        
        jList3.setModel(d);
      
    }
    
    public static void sort(DefaultListModel d){
        List list = Collections.list(d.elements()); // get a collection of the elements in the model
        Collections.sort(list); // sort
        d.clear(); // remove all elements
        for(Object o:list){ d.addElement(o); }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jComboBox3 = new javax.swing.JComboBox();
        bodyShow = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Symptom Checker - MedAid+");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/find.png"))); // NOI18N
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 600, 170, 60));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 204));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 204, 153));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 150, 250, 40));

        jComboBox2.setBackground(new java.awt.Color(255, 255, 204));
        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 204, 153));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Scalp", "Eyes", "Nose", "Mouth" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 250, 40));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nurse.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 20, -1, -1));

        jButton2.setBackground(new java.awt.Color(0, 204, 153));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add button for symptom.png"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 90, 50));

        jButton3.setBackground(new java.awt.Color(0, 153, 153));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/remove for symptom.png"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, 90, 50));

        jList3.setBackground(new java.awt.Color(0, 204, 153));
        jList3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList3.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jList3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 280, 470));

        jList1.setBackground(new java.awt.Color(0, 204, 153));
        jList1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList1.setForeground(new java.awt.Color(255, 255, 204));
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 240, 330));

        jList2.setBackground(new java.awt.Color(0, 204, 153));
        jList2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jList2.setForeground(new java.awt.Color(255, 255, 204));
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 320, 240, 330));

        jComboBox3.setBackground(new java.awt.Color(255, 255, 204));
        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox3.setForeground(new java.awt.Color(0, 204, 153));
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Head", "Neck", "Chest", "Arm", "Abdomen", "Pelvis", "Legs" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 200, 250, 40));
        getContentPane().add(bodyShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 144, 160, 160));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/symptomcheker2.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(!jList3.isSelectionEmpty()){
            try {
                diseaseInfo d=new diseaseInfo((String) jList3.getSelectedValue());
                d.setVisible(true);

                // TODO add your handling code here:
            } catch (SQLException ex) {
                Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Object[] b = jList1.getSelectedValues();
        for(int j=0; j<b.length; j++)
            model2.addElement(b[j]);
        jList2.setModel(model2);

        int[] in = jList1.getSelectedIndices();
        for(int j=in.length-1; j>=0; j--)
            model1.remove(in[j]);
        jList1.setModel(model1);


        List<String> s = new ArrayList<String>();
        Enumeration en = model2.elements();
        model3 = new DefaultListModel();
        while(en.hasMoreElements())
            s.add((String)en.nextElement());
        try {
            getDisease(s, model3);
        } catch (SQLException ex) {
            Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Object[] b = jList2.getSelectedValues();
        for(int j=0; j<b.length; j++){
            if(list.contains(b[j]))
                model1.addElement(b[j]);
        }
        sort(model1);
        jList1.setModel(model1);

        int[] in = jList2.getSelectedIndices();
        for(int j=in.length-1; j>=0; j--)
            model2.remove(in[j]);
        jList2.setModel(model2);

        List<String> s = new ArrayList<String>();
        Enumeration en = model2.elements();
        model3 = new DefaultListModel();
        while(en.hasMoreElements())
            s.add((String)en.nextElement());
        try {
            getDisease(s, model3);
        } catch (SQLException ex) {
            Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(checkdisease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(checkdisease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(checkdisease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(checkdisease.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new checkdisease().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(checkdisease.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    static List<String> list;
    static DefaultListModel model1;
    static DefaultListModel model2;
    static DefaultListModel model3;
    static Connection myConn;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bodyShow;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private static javax.swing.JList jList1;
    private static javax.swing.JList jList2;
    private static javax.swing.JList jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
