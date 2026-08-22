package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = System.getenv("MYFINANCE_DB_URL");
    private static final String USER = System.getenv("MYFINANCE_DB_USER");
    private static final String PASSWORD = System.getenv("MYFINANCE_DB_PASSWORD");

    private static String requireEnv(String name, String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalStateException("Variável de ambiente ausente: " + name);
        }
        return value;
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                requireEnv("MYFINANCE_DB_URL", URL),
                requireEnv("MYFINANCE_DB_USER", USER),
                requireEnv("MYFINANCE_DB_PASSWORD", PASSWORD)
        );
    }
}
