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
import Model.Atleta;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Santiago
 */
public class AtletaGestion {

    private static final String SQL_INSERT_ATLETA = "insert into atleta values (?,?,?,?,?,?,?,?,?)";

    public static boolean insertar(Atleta atleta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_ATLETA);
            sentencia.setInt(1, atleta.getCodigo());
            sentencia.setInt(2, atleta.getCedula());
            sentencia.setString(3, atleta.getNombre());
            sentencia.setString(4, atleta.getApellido());
            sentencia.setObject(5, atleta.getFechaNaci());
            sentencia.setDouble(6, atleta.getPeso());
            sentencia.setString(7, atleta.getCorreo());
            sentencia.setString(8, atleta.getDireccion());
            sentencia.setInt(9, atleta.getTelefono());
            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_UPDATE_ATLETA
            = "update atleta set cedula_a=?, nombre_A=?, apellido_a=?, fechaNaci_a=?, peso_a=?, correo_a=?, direccion_a=?, telefono_a=? where codigo_atleta=?";

    public static boolean modificar(Atleta atleta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_ATLETA);

            sentencia.setInt(1, atleta.getCedula());
            sentencia.setString(2, atleta.getNombre());
            sentencia.setString(3, atleta.getApellido());
            sentencia.setObject(4, atleta.getFechaNaci());
            sentencia.setDouble(5, atleta.getPeso());
            sentencia.setString(6, atleta.getCorreo());
            sentencia.setString(7, atleta.getDireccion());
            sentencia.setInt(8, atleta.getTelefono());
            sentencia.setInt(9, atleta.getCodigo());

            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_DELETE_ATLETA = "delete from atleta where codigo_atleta=?";

    //Retorna true si logra ELIMINAR el atleta... false si no lo logra...
    public static boolean eliminar(Atleta atleta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_DELETE_ATLETA);
            sentencia.setInt(1, atleta.getCodigo());
            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_SELECT_ATLETA = "select * from atleta where codigo_atleta=?";

    //Retorna un Objecto Atleta si lo encuentra... y null si no lo encuentra..
    public static Atleta getAtleta(int codigo) {
        Atleta atleta = null;
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_SELECT_ATLETA);
            sentencia.setInt(1, codigo);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                atleta = new Atleta(
                        datos.getInt(1), //codigo
                        datos.getInt(2), //cedula
                        datos.getString(3), //nombre
                        datos.getString(4), //apellidos
                        datos.getDate(5), //fecha nacimiento
                        datos.getDouble(6), //peso
                        datos.getString(7), //correo
                        datos.getString(8), //direccion
                        datos.getInt(9)); //telefono
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return atleta;
    }

    private static final String SQL_SELECT_ATLETAS = "select * from atleta";

    public static ArrayList<Atleta> getAtletas() {
        ArrayList<Atleta> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_ATLETAS);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()) {
                lista.add(
                        new Atleta(
                                datos.getInt(1), //codigo
                                datos.getInt(2), //cedula
                                datos.getString(3), //nombre
                                datos.getString(4), //apellidos
                                datos.getDate(5), //fecha nacimiento
                                datos.getDouble(6), //peso
                                datos.getString(7), //correo
                                datos.getString(8), //direccion
                                datos.getInt(9))); //telefono                                      
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
 public static boolean eliminarConCodigo(int codigo) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_DELETE_ATLETA);
            sentencia.setInt(1, codigo);
            int fila = sentencia.executeUpdate();
            return fila > 0; 
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }

}
