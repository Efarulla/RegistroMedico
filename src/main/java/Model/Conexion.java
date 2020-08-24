package Model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static final String DBURL = "jdbc:derby://localhost:1527/RegistroMedico";
    private static final String DBCLASS = "org.apache.derby.jdbc.ClientDriver";
    private static final String USER = "root";
    private static final String PW = "root";
    private static Connection conn;
    private static Conexion conexion;

    private Conexion() {

        try {
            Class.forName(DBCLASS).getDeclaredConstructor().newInstance();
            conn = DriverManager.getConnection(DBURL,USER,PW);

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static synchronized Connection getConexion() {
        if (conexion == null) {
            conexion = new Conexion();

        }

        return conn;
    }
    
    

}
