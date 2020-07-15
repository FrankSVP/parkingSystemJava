/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author FrankS
 */
public class CConexion {
    
      Connection conexion;
      
    public Connection ConexionBD() {
       
        try {
            // We register the PostgreSQL driver
            // Registramos el driver de PostgresSQL
            try { 
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException ex) {
                System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
            }
            conexion = null;
            // Database connect
            // Conectamos con la base de datos
            conexion = DriverManager.getConnection(
                    "jdbc:postgresql://localhost/ParkinDB",
                    "postgres", "root");
 
            boolean valid = conexion.isValid(50000);
            System.out.println(valid ? "TEST OK" : "TEST FAIL");
             
        } catch (java.sql.SQLException sqle) {
            System.out.println("Error: " + sqle);
             
        }
          return conexion;
    } 
}
