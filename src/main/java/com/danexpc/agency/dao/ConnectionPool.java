package com.danexpc.agency.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public void commitAndClose(Connection con, PreparedStatement pst, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                // todo log
            }
        }
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                // todo log
            }
            try {
                con.close();
            } catch (SQLException ex) {
                // todo log
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                // todo log
            }
        }
    }

    public void commitAndClose(Connection con, PreparedStatement pst) {
        if (con != null) {
            try {
                con.commit();
            } catch (SQLException ex) {
                // todo log
            }
            try {
                con.close();
            } catch (SQLException ex) {
                // todo log
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException ex) {
                // todo log
            }
        }
    }

    public void rollback(Connection con) {
        if (con == null) return;
        try {
            con.rollback();
        } catch (SQLException ex) {
            // todo log
        }
    }
}
