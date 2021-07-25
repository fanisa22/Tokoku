/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Toko.Manager;

import Toko.Helper.CheckConnection;
import Toko.Helper.ConnectionHelper;
import Toko.Model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fanisa
 */
public class PelangganManager {
    
    public static List<Pelanggan> showAllPelanggan() throws SQLException {
        List<Pelanggan> pelangganList = new ArrayList<>();
        
        try {
            Connection conn = ConnectionHelper.getConnection();
            Statement stmn = conn.createStatement();
            ResultSet rs = stmn.executeQuery("Select * from pelanggan");
            while (rs.next()) {
                Pelanggan pelanggan = new Pelanggan();
                pelanggan.setId_pelanggan(Integer.parseInt(rs.getString("id_pelanggan")));
                pelanggan.setNama_pelanggan(rs.getString("nama_pelanggan"));
                pelanggan.setAlamat(rs.getString("alamat"));
                pelanggan.setNo_hp(rs.getString("no_hp"));
                
                pelangganList.add(pelanggan);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pelangganList;
    }
    
    public static void insertPelanggan(Pelanggan pelanggan) throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement pstmn = conn.prepareStatement("insert into pelanggan (nama_pelanggan, "
                + "alamat, no_hp) values (?, ?, ?)");
        pstmn.setString(1, pelanggan.getNama_pelanggan());
        pstmn.setString(2, pelanggan.getAlamat());
        pstmn.setString(3, pelanggan.getNo_hp());
        pstmn.execute();
    }
    
    public static Pelanggan showPelanggan(int id) throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        Pelanggan pelanggan = new Pelanggan();
        
        PreparedStatement pstmn = conn.prepareStatement("Select * from pelanggan where id_pelanggan = ?");
        pstmn.setInt(1, id);
        ResultSet rs = pstmn.executeQuery();
        
        while (rs.next()) {
            pelanggan.setId_pelanggan(Integer.parseInt(rs.getString("id_pelanggan")));
            pelanggan.setNama_pelanggan(rs.getString("nama_pelanggan"));
            pelanggan.setAlamat(rs.getString("alamat"));
            pelanggan.setNo_hp(rs.getString("no_hp"));
        }
        return pelanggan;
    }
    
    public static void updatePelanggan(Pelanggan pelanggan) throws SQLException {
        Connection conn = ConnectionHelper.getConnection();
        PreparedStatement pstmn = conn.prepareStatement("UPDATE pelanggan SET nama_pelanggan = ?," 
                + "alamat = ?, no_hp = ? WHERE id_pelanggan =?");
        pstmn.setString(1, pelanggan.getNama_pelanggan());
        pstmn.setString(2, pelanggan.getAlamat());
        pstmn.setString(3, pelanggan.getNo_hp());
        pstmn.setInt(4, pelanggan.getId_pelanggan());
        pstmn.execute();
    }
}
