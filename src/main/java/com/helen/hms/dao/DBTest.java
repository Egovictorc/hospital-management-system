package com.helen.hms.dao;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBTest {
    Connection conn = null;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        conn = DB.connect();
        assertNotNull(conn);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void connect() {
    }

    @org.junit.jupiter.api.Test
    void getAllStaff() {

        //assertEquals(DB.getAllStaff().size(), 0);
    }

    @org.junit.jupiter.api.Test
    void getStaffById() {
    }
}