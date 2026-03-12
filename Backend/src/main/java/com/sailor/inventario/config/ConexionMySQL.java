package com.sailor.inventario.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConexionMySQL {

    private static final Dotenv dotenv = Dotenv.load();

    private static final String url = dotenv.get("db_URL");
    private static final String USER = dotenv.get("db_USER");
    private static final String PASSWORD = dotenv.get("db_PASS");

    public static Connection getConnection() {

        Connection conexion = null;

        try {

            conexion = DriverManager.getConnection(url, USER, PASSWORD);
            System.out.println("Conexión establecida con éxito a la base de datos.");

        } catch (SQLException e) {

            System.out.println("Error al conectar con la base de datos: " + e.getMessage());

        }

        return conexion;

    }
}