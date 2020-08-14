/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Conexion;
import Model.Receta;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class RecetaGestion {

    private static final String SQL_INSERT_RECETA = "INSERT INTO RECETA (DOSIS_R,DESCRIPCION_R,CANTIDAD_R,FECHA_R)\n" +
"VALUES(?,?,?,?)";

    public static boolean insertar(Receta receta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_RECETA);
              sentencia.setString(1, receta.getDosis());
            sentencia.setString(2, receta.getDescripcion());
            sentencia.setInt(3, receta.getCantidad());
            sentencia.setObject(4, receta.getFecha());

            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(RecetaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_UPDATE_RECETA
            = "update receta set dosis=?, descripcion=?, cantidad=?  where codigo=?";

    public static boolean modificar(Receta receta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_RECETA);

            sentencia.setInt(1, receta.getCodigo());
            sentencia.setString(2, receta.getDosis());
            sentencia.setString(3, receta.getDescripcion());
            sentencia.setInt(4, receta.getCantidad());
            sentencia.setObject(5, receta.getFecha());

            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(RecetaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_DELETE_RECETA = "delete from receta where codigo=?";

    //Retorna true si logra ELIMINAR la receta... false si no lo logra...
    public static boolean eliminar(Receta receta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_DELETE_RECETA);
            sentencia.setInt(1, receta.getCodigo());
            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(RecetaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_SELECT_RECETA = "select * from receta where codigo=?";

    //Retorna un Objecto Receta si lo encuentra... y null si no lo encuentra..
    public static Receta getReceta(Integer codigo) {
        Receta receta = null;
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_SELECT_RECETA);
            sentencia.setInt(1, codigo);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                receta = new Receta(
                        datos.getInt(1), //codigo
                        datos.getString(2), //dosis
                        datos.getString(3), //descripcion
                        datos.getInt(4), //cantidad
                        datos.getDate(5)); //fecha
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecetaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receta;
    }

    private static final String SQL_SELECT_RECETAS = "select * from receta";

    public static ArrayList<Receta> getRecetas() {
        ArrayList<Receta> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_RECETAS);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()) {
                lista.add(
                        new Receta(
                                datos.getInt(1), //codigo
                                datos.getString(2), //dosis
                                datos.getString(3), //descripcion
                                datos.getInt(4), //cantidad
                                datos.getDate(5))); //fecha                                     
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecetaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
