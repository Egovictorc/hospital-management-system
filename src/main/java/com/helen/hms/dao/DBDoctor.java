package com.helen.hms.dao;

import com.helen.hms.Main;
import com.helen.hms.service.UtilityClass;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBDoctor extends DB<Doctor> {
    final static private String URL = "jdbc:mysql://localhost:3306/", USERNAME = "root", PASSWORD = "root", DATABASE = "hms";

    public List<Staff> getAllStaff() {
        // RETRIEVE ALL STAFF
        List<Staff> staff = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Staff s = new Staff(rs.getInt("id"), rs.getString(2), rs.getString(3));
                staff.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return staff;
    }

    @Override
    public Doctor getById(int id) {
        // RETRIEVE A SINGLE STAFF BY ID
        String sql = "Select * from doctor where id=?"+id;
        Doctor d = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                d = new Doctor(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), Enum.valueOf(Qualification.class, rs.getString("qualification")), rs.getLong("phone"), Enum.valueOf(Gender.class,  rs.getString("gender")), Enum.valueOf(Specialty.class,  rs.getString("specialty")));
            }
            System.out.println(d);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }

    @Override
    public int updatePerson(Doctor d) {
        int rows = 0;
        try {
            String sql = "update doctor set firstName =?, lastName= ?, username = ?, password= ?, phone= ?, gender=?, qualification=?, specialty=? where id=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, d.getFirstName());
            ptmt.setString(2, d.getLastName());
            ptmt.setString(3, d.getUsername());
            ptmt.setString(4, d.getPassword());
            ptmt.setLong(5, d.getPhone());
            ptmt.setString(6, String.valueOf(d.getGender()));
            ptmt.setString(7, String.valueOf(d.getQualification()));
            ptmt.setString(8, String.valueOf(d.getSpecialty()));
            ptmt.setLong(9, d.getId());
            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        }
        return rows;
    }

    @Override
    public Doctor getByUsername(String username) {
        // RETRIEVE A SINGLE STAFF BY ID
        String sql = "Select * from doctor where username=?";
        Doctor d = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username);

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                //d = new Doctor(rs.getLong(1), rs.getString(2), rs.getString(3), Enum.valueOf(Department.class, rs.getString("department")));
            }
            System.out.println(d);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return d;
    }


    @Override
    public int registerPerson(Doctor d) {
        int rows = 0;
        try {
            String sql = "INSERT INTO Doctor (firstName, lastName, Username, Password, Phone, Gender, Qualification, Specialty) values(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, d.getFirstName());
            ptmt.setString(2, d.getLastName());
            ptmt.setString(3, d.getUsername());
            ptmt.setString(4, d.getPassword());
            ptmt.setLong(5, d.getPhone());
            ptmt.setString(6, d.getGender().toString());
            ptmt.setString(7, d.getQualification().toString());
            ptmt.setString(8, d.getSpecialty().toString());
            rows = ptmt.executeUpdate();

            Main.setRoot("doctor");
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering Doctor", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int removePerson(Doctor d) {
        int rows = 0;
        try {
            String sql = "DELETE from doctor where id= ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setLong(1, d.getId());
            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        }
        return rows;
    }

    public static void main(String[] args) throws SQLException {
        conn = DB.connect();
        String sql = "Select * from staff";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();

        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(1));
        }
    }
}
