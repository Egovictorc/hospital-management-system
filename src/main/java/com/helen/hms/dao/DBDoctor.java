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
    // Overview: manages database connectivity for doctor objects

    public List<Staff> getAllStaff() {
        //EFFECTS: returns all staff from database
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
        // EFFECTS: returns a single doctor with the provided id from the database

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
        // MODIFIES: doctor table in the database
        // EFFECTS: return 1 if doctor table was successfully updated else return zero

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
        // EFFECTS: returns a single doctor object with the provided username from the database

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
        //MODIFIES: Doctor table in the database
        // EFFECTS: add doctor object to database, returns an integer representing the number of records inserted

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
        //MODIFIES: Doctor table in the database
        // EFFECTS: remove doctor object from the database, returns an integer representing the number of records deleted

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
}
