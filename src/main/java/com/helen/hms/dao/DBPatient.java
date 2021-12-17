package com.helen.hms.dao;

import com.helen.hms.Main;
import com.helen.hms.service.UtilityClass;

import java.io.IOException;
import java.sql.*;

public class DBPatient extends DB<Patient> {

    @Override
    public Patient getById(int id) {
        // RETRIEVE A SINGLE PATIENT BY ID
        String sql = "Select * from patient where id=?"+id;
        Patient p = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                p = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getDate(5).toLocalDate(), rs.getBoolean(6), rs.getInt(6));
            }
            System.out.println(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public int updatePerson(Patient p) {
        int rows = 0;
        try {
            String sql = "update patient set firstName =?, lastName= ?, Sickness = ?, discharged= ?, doctor_id = ? where id=?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, p.getFirstName());
            ptmt.setString(2, p.getLastName());
            ptmt.setString(3, p.getSickness());
            ptmt.setBoolean(4, p.isDischarged());
            ptmt.setInt(5, p.getDoctorId());
            ptmt.setInt(6, p.getId());
            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        }
        return rows;
    }

    @Override
    public Patient getByUsername(String username) {
        // RETRIEVE A SINGLE STAFF BY ID
        String sql = "Select * from patient where firstname=?";
        Patient p = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username);

            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                //p = new Patient(rs.getLong(1), rs.getString(2), rs.getString(3), Enum.valueOf(Department.class, rs.getString("department")));
            }
            System.out.println(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }


    @Override
    public int registerPerson(Patient p) {
        int rows = 0;
        try {
            String sql = "INSERT INTO patient (firstName, lastName, Sickness, discharged, doctor_id) values(?, ?, ?, ?, ?)";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, p.getFirstName());
            ptmt.setString(2, p.getLastName());
            ptmt.setString(3, p.getSickness());
            ptmt.setBoolean(4, p.isDischarged());
            ptmt.setInt(5, p.getDoctorId());
            rows = ptmt.executeUpdate();

            Main.setRoot("patient");
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }

    @Override
    public int removePerson(Patient p) {
        int rows = 0;
        try {
            String sql = "DELETE from patient where id= ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, p.getId());
            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        }
        return rows;
    }
}
