/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko.Helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanisa
 */
public class CheckConnection {
    
    public static void main (String[] args) {
        isConnected();
        showBarang();
    }
    
    private static boolean isConnected() {
        try {
            ConnectionHelper.getConnection();
            System.out.println("Database Connected!");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Failed to connect database");
            return false;
        }
    }
    
    public static void showBarang() {
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery("Select * from barang");
            
            while (rs.next()) {
                System.out.println("ID Barang : "+rs.getString("id_barang")+", Nama Barang : "+rs.getString("nama_barang")
                        +", Supplier : "+rs.getString("supplier")+", Harga : "+rs.getString("harga")
                        +", Kategori : "+rs.getString("kategori")+", Jumlah : "+rs.getString("jumlah"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
