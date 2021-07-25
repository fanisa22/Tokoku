/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko.UI;

import Toko.Helper.ConnectionHelper;
import Toko.Manager.BarangManager;
import Toko.Model.Barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author fanisa
 */
public class addBarang extends javax.swing.JFrame {
    
    String formTitle = "";
    int barangID = 0;
    Barang barangGlobal;

    /**
     * Creates new form addBarang
     */
    public addBarang( ) {
        initComponents();
        setLocationRelativeTo(null);
//        tanggal();
    }
    
    addBarang (String title, int id_barang) {
        initComponents();
        setLocationRelativeTo(null);
        addBarangLable.setText(title);
        formTitle = title;
        barangID = id_barang;
        try{
            setBarangToComponent(barangID);
        }catch (SQLException ex){
            Logger.getLogger(addBarang.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void setBarangToComponent (int barangID) throws SQLException {
        
        Barang barang = BarangManager.showBarang(barangID);
        String tanggal = ((JTextField)Tanggal.getDateEditor().getUiComponent()).getText();
        barang.setId_barang(barangID);
        nameField.setText(barang.getNama_barang());
        priceField.setText(barang.getHarga());
        quantityField.setText(barang.getJumlah());
        kategoriComboBox.setSelectedItem(barang.getKategori());
//        Tanggal.setDate(barang.getTanggal_masuk());


        
    }
//
    
        public void insertBarang() {
            try {
               Connection conn = ConnectionHelper.getConnection();
               Statement stm = conn.createStatement();
               String tanggal = ((JTextField)Tanggal.getDateEditor().getUiComponent()).getText();
               String query = "INSERT INTO barang (nama_barang, harga, jumlah, kategori, tanggal_masuk) "
                       + "VALUES ('"+ nameField.getText()+
                                  "', '"+ priceField.getText()+
                                  "', '"+ quantityField.getText()+
                                  "', '"+ kategoriComboBox.getSelectedItem()+
                                  "', '"+tanggal+
                                  "')";       
               stm.executeUpdate(query);
               JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan", "Yeay Success", JOptionPane.INFORMATION_MESSAGE);
           } catch (Exception e) {
               System.out.println("Error:" + e.getMessage());
           }
        }
        
        public void updateBarang(Barang barang)throws SQLException {
            Connection conn = ConnectionHelper.getConnection();
            String tanggal = ((JTextField)Tanggal.getDateEditor().getUiComponent()).getText();
        PreparedStatement pstmn = conn.prepareStatement("UPDATE barang SET nama_barang = ?," 
                + "harga = ?, jumlah = ?, kategori = ?, tanggal_masuk = ?  WHERE id_barang =?");
        pstmn.setString(1, barang.getNama_barang());
        pstmn.setString(2, barang.getHarga());
        pstmn.setString(3, barang.getJumlah());
        pstmn.setString(4, barang.getKategori());
        pstmn.setString(5,tanggal);
        pstmn.setInt(6, barang.getId_barang());
        pstmn.execute();
        }
        
    private Boolean validateInput() throws SQLException {
        boolean isCompleted = false;
        
        Barang barang = new Barang();
        
        if (nameField.getText().isEmpty()) {
            isCompleted = false;
            nameField.requestFocus();
        } else {
            barang.setNama_barang(nameField.getText());
            isCompleted = true;
        }
        
        if (priceField.getText().isEmpty()) {
            isCompleted = false;
            priceField.requestFocus();
        } else {
            barang.setHarga(priceField.getText());
            isCompleted = true;
        }
        
        if (quantityField.getText().isEmpty()) {
            isCompleted = false;
            quantityField.requestFocus();
        } else {
            barang.setJumlah(quantityField.getText());
            isCompleted = true;
        }
        
        barang.setKategori(String.valueOf(kategoriComboBox.getSelectedItem()));
        
        if (formTitle.equalsIgnoreCase("Update Barang")) {
            barang.setId_barang(barangID);
        }
        
        barangGlobal = barang;
        
        if (!isCompleted) {
            showMessage("Data harus dilengkapi!", 2);
            return false;
        } else {
            dispose();
            return true;
        }
    }
    
    public void showMessage (String message, int type) {
        if (type == 1) {
            JOptionPane.showMessageDialog(this, message, "Sukses", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
//    public void tanggal(){
//        Date now = new Date();
//        Tanggal.setDate(now);
//    }
    
    public static Date getTanggalFromTable(JTable table, int kolom) {
        JTable Tanggal = table;
        String tanggal_masuk = String.valueOf(Tanggal.getValueAt(Tanggal.getSelectedRow(),kolom));
        Date tanggal = null;
        try {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal_masuk);
        } catch (Exception ex) {
            Logger.getLogger(DaftarBarang.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tanggal;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        addBarangLable = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        quantityField = new javax.swing.JTextField();
        kategoriComboBox = new javax.swing.JComboBox<>();
        submitBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Tanggal = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 102));

        addBarangLable.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        addBarangLable.setText("Tambah Barang");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Nama                :");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("Harga                :");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel4.setText("Jumlah               :");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("Kategori             :");

        kategoriComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sembako", "Makanan Ringan", "Minuman", "Gas Elpiji" }));

        submitBtn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        submitBtn.setText("Simpan");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });

        cancelBtn.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cancelBtn.setText("Batal");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Tanggal Masuk   :");

        Tanggal.setDateFormatString("YYYY-MM-dd");
        Tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TanggalPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantityField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(priceField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nameField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(kategoriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 222, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(addBarangLable)
                                .addGap(215, 215, 215))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cancelBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(submitBtn)
                                .addContainerGap())))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(submitBtn)
                            .addComponent(cancelBtn))
                        .addGap(29, 29, 29))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addBarangLable)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(quantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(kategoriComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(89, 89, 89))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBtnActionPerformed
        // TODO add your handling code here:
        if (formTitle.equalsIgnoreCase("Update Barang")) {
            try {
                if (validateInput()) {
                    updateBarang(barangGlobal);
                    DaftarBarang.loadBarang();
                }
            } catch (SQLException ex) {
                Logger.getLogger(addBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                if (validateInput()) {
                   insertBarang();
                   new DaftarBarang().setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(addBarang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_submitBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void TanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TanggalPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TanggalPropertyChange

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
            java.util.logging.Logger.getLogger(addBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Tanggal;
    private javax.swing.JLabel addBarangLable;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> kategoriComboBox;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField priceField;
    private javax.swing.JTextField quantityField;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
