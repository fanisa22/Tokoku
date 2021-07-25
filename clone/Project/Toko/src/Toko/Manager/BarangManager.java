/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko.Manager;

import Toko.Helper.CheckConnection;
import Toko.Helper.ConnectionHelper;
import Toko.Model.Barang;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;

/**
 *
 * @author fanisa
 */
public class BarangManager {
    
    public static List<Barang> showAllBarang() throws SQLException {
        List<Barang> barangList = new ArrayList<>();
        
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery("Select * from barang");
            while (rs.next()) {
                Barang barang = new Barang();
                barang.setId_barang(Integer.parseInt(rs.getString("id_barang")));
                barang.setNama_barang(rs.getString("nama_barang"));
                barang.setHarga(rs.getString("harga"));
                barang.setJumlah(rs.getString("jumlah"));
                barang.setKategori(rs.getString("kategori"));
                barang.setTanggal_masuk(rs.getString("tanggal_masuk"));
                
                barangList.add(barang);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return barangList;
    }
    

    
    public static Barang showBarang(int id) throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        Barang barang = new Barang();
        
        PreparedStatement pstmn = conn.prepareStatement("Select * from barang where id_barang = ?");
        pstmn.setInt(1, id);
        ResultSet rs = pstmn.executeQuery();
        
        while (rs.next()) {
            barang.setId_barang(Integer.parseInt(rs.getString("id_barang")));
            barang.setNama_barang(rs.getString("nama_barang"));
            barang.setHarga(rs.getString("harga"));
            barang.setJumlah(rs.getString("jumlah"));
            barang.setKategori(rs.getString("kategori"));
            barang.setTanggal_masuk(rs.getString("tanggal_masuk"));
        }
        return barang;
    }
    
    public static void updateBarang(Barang barang) throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement pstmn = conn.prepareStatement("UPDATE barang SET nama_barang = ?," 
                + "harga = ?, jumlah = ?, kategori = ?, tanggal_masuk = ?  WHERE id_barang =?");
        pstmn.setString(1, barang.getNama_barang());
        pstmn.setString(2, barang.getHarga());
        pstmn.setString(3, barang.getJumlah());
        pstmn.setString(4, barang.getKategori());
        pstmn.setString(5, barang.getTanggal_masuk());
        pstmn.setInt(6, barang.getId_barang());
        pstmn.execute();
    }

}
