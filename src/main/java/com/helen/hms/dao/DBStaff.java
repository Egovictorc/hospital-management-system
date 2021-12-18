package com.helen.hms.dao;

import com.helen.hms.exceptions.UserAlreadyExistException;
import com.helen.hms.service.PersonFactory;
import com.helen.hms.service.UtilityClass;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBStaff extends DB<Staff> {
// Overview: manages database connectivity for Staff objects

    public List<Staff> getAllStaff() {
        //EFFECTS: returns all staff from database
        List<Staff> staff = new ArrayList<>();
        String sql = "SELECT * FROM staff";

        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Staff s = new Staff(rs.getInt(1), rs.getString(2), rs.getString(3));
                staff.add(s);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return staff;
    }

 /*   public Staff getStaffById(long id) {
        // RETRIEVE A SINGLE STAFF BY ID
        String sql = "Select * from staff where id=?";
        Staff s = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setLong(1, id);

            ResultSet rs = ptmt.executeQuery();
            s = new Staff(rs.getInt("id"), rs.getString("username"), rs.getString("password"), Enum.valueOf(Department.class, rs.getString("department")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }*/

    @Override
    public int registerPerson(Staff s) {
        //MODIFIES: Staff table in the database
        // EFFECTS: add staff object to database, returns an integer representing the number of records inserted

        String sql = "Select * from staff where username= ?";
        int rows = 0;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, s.getUsername());
            ResultSet rs = ptmt.executeQuery();

            if (rs.next()) {
                System.out.println("fetch size "+ rs.getFetchSize());
                throw new UserAlreadyExistException("User already exists");
            } else {
                String sql_ins = "INSERT INTO staff (username, password, department) values(?, ?, ?)";
                ptmt = conn.prepareStatement(sql_ins);
                ptmt.setString(1, s.getUsername());
                ptmt.setString(2, s.getPassword());
                ptmt.setString(3, String.valueOf(s.getDepartment()));
                rows = ptmt.executeUpdate();
                return rows;
            }
        } catch (SQLException | UserAlreadyExistException e) {
            UtilityClass.showAlert(e.getClass().getSimpleName(), e.getMessage());
        } finally {
            return rows;
        }
    }

    @Override
    int removePerson(Staff staff) {
        //MODIFIES: Staff table in the database
        // EFFECTS: remove staff object from the database, returns an integer representing the number of records deleted

        int rows = 0;
        try {
            String sql = "DELETE from staff where id= ?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setInt(1, staff.getId());
            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error registering staff", e.getMessage());
        }
        return rows;
    }

    @Override
    public Staff getByUsername(String username) {
        // EFFECTS: returns a single staff object with the provided username from the database

        String sql = "Select * from staff where username= ?";
        Staff s = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, username);

            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                //s = new Staff(rs.getInt("id"), rs.getString(2), rs.getString(3), Enum.valueOf(Department.class, rs.getString("department")));
                s = new Staff(rs.getInt("id"), rs.getLong("phone"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("address"), Enum.valueOf(Department.class, rs.getString("department")), Enum.valueOf(Gender.class, rs.getString("gender")));
                System.out.println("updated Staff " + s);
            }

        } catch (SQLException e) {
            UtilityClass.showAlert("Error updating record", e.getMessage());
        }
        return s;
    }

    @Override
    Staff getById(int id) {
        // EFFECTS: returns a single Staff with the provided id from the database

        String sql = "Select * from staff where id=?" + id;
        Staff s = null;
        try {
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setLong(1, id);

            ResultSet rs = ptmt.executeQuery();
            s = new Staff(rs.getInt("id"), rs.getString("username"), rs.getString("password"), Enum.valueOf(Department.class, rs.getString("department")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public int updatePerson(Staff s) {
        // MODIFIES: Staff table in the database
        // EFFECTS: return 1 if staff table was successfully updated else return zero

        int rows = 0;
        try {
            String sql = "update staff set firstName =?, lastName=?, email=?, phone=?, address=?, gender=?, department=? where id =?";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.setString(1, s.getFirstName());
            ptmt.setString(2, s.getLastName());
            ptmt.setString(3, s.getEmail());
            ptmt.setLong(4, s.getPhone());
            ptmt.setString(5, s.getAddress());
            ptmt.setString(6, s.getGender().toString());
            ptmt.setString(7, s.getDepartment().toString());
            ptmt.setInt(8, PersonFactory.getSelectedStaff().getId());

            rows = ptmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            UtilityClass.showAlert("Error updating   staff", e.getMessage());
        }
        return rows;
    }

}
