package ru.drogunov.springcource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;

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
    
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
