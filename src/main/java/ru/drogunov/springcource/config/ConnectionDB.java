package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class ConnectionDB implements AutoCloseable {
    private final Connection connection;
    
    @Autowired
    public ConnectionDB(Connection connection) {
        this.connection = connection;
    }
    
    public Connection get() {
        return connection;
    }
    
    public ResultSet executeQuery(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void close() throws Exception {
        connection.close();
    }
    
    public void executeUpdate(String query) {
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
