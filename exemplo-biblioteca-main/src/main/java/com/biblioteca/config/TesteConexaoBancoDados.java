package com.biblioteca.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoBancoDados {

    public static void main(String[] args) {
        testarConexao();
    }

    public static void testarConexao() {
        Connection conn = null;
        try {
            // Carregar o driver JDBC específico do banco de dados (opcional, dependendo da versão do JDBC)
            // Class.forName("com.mysql.jdbc.Driver");

            // Estabelecer a conexão com o banco de dados
            conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");

            // Se chegou até aqui, a conexão foi bem-sucedida
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            // Lidar com exceções de SQL (por exemplo, erros de autenticação, banco de dados não encontrado, etc.)
            System.out.println("Erro ao tentar conectar ao banco de dados:");
            e.printStackTrace();
        } finally {
            // Fechar a conexão (se estiver aberta)
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Lidar com exceções ao fechar a conexão
                    e.printStackTrace();
                }
            }
        }
    }
}
