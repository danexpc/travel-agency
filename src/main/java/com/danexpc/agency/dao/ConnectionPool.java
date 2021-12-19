package com.danexpc.agency.dao;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ConnectionPool {

    private static ConnectionPool instance;

    private DataSource ds;

    private ConnectionPool() {
    }

    @SneakyThrows
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        Connection con = null;

        try {
            if (ds == null) {
                Context cxt = new InitialContext();

                ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/agency");
            }

            con = ds.getConnection();
        } catch (NamingException e) {
            log.error("Error with context.", e);
        }

        return con;
    }
}
