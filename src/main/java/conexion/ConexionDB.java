/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

/**
 *
 * @author Eduar Medrano
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    //La url tiene IP en lugar de localhost porque tengo sql en otra maquina
    private static  String url = "jdbc:sqlserver://localhost:1433;databaseName=Taller;encrypt=true;trustServerCertificate=true";
    private static final String user = "admintaller"; 
    private static final String pass = "Itca123*25";
        
    public static Connection conectar(){
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión establecida correctamente.");
        } catch (SQLException e){
            System.out.println("Error de conexión: " + e.getMessage());
        } catch (ClassNotFoundException o) {
            System.out.println("Error al cargar driver de conexion: "+ o.getMessage());
        }
        return conn;
    }

    public static Connection getConnection() {
        return conectar();
    }
    
}
