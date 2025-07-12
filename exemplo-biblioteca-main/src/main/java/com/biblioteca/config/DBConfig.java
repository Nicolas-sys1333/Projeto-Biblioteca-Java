package  com.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfig {

  private static final String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

  private static final String USER = "sa";

  private static final String PASSWORD = "";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static void createTables() {
    try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
      String sql = """
          CREATE TABLE IF NOT EXISTS Livro (
            id INT AUTO_INCREMENT PRIMARY KEY,
            titulo VARCHAR(255) NOT NULL,
            autor VARCHAR(255) NOT NULL,
            num_paginas INT NOT NULL
          )""";
      stmt.executeUpdate(sql);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}