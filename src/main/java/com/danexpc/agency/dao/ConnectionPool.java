package com.danexpc.agency.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final ConnectionPool instance;

    static {
        try {
            instance = new ConnectionPool();
        } catch (NamingException e) {
            throw new IllegalStateException("Failed to create a connection pool", e);
        }
    }

    private final DataSource ds;

    private ConnectionPool() throws NamingException {
        Context cxt = new InitialContext();
        ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/agency");
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
