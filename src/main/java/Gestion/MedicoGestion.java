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
import java.sql.ResultSet;
import java.util.ArrayList;
import Model.Medico;

/**
 *
 * @author Santiago
 */
public class MedicoGestion {

    private static final String SQL_INSERT_MEDICO = "insert into medico values (?,?,?,?,?,?,?,?,?,?)";

    public static boolean insertar(Medico medico) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_MEDICO);
            sentencia.setInt(1, medico.getCodigo());
            sentencia.setInt(2, medico.getCedula());
            sentencia.setString(3, medico.getNombre());
            sentencia.setString(4, medico.getApellido());
            sentencia.setObject(5, medico.getFechaNaci());
            sentencia.setString(6, medico.getCorreo());
            sentencia.setString(7, medico.getDireccion());
            sentencia.setInt(8, medico.getTelefono());
            sentencia.setDouble(9, medico.getSalario());
            sentencia.setString(10, medico.getEspecialidad());
            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(MedicoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_UPDATE_MEDICO
            = "update medico set cedula=?, nombre=?, apellido=?, fechaNaci=?, correo=?, direccion=?, telefono=?, salario=?, especialidad=? where codigo=?";

    public static boolean modificar(Medico medico) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_MEDICO);

            sentencia.setInt(1, medico.getCodigo());
            sentencia.setInt(2, medico.getCedula());
            sentencia.setString(3, medico.getNombre());
            sentencia.setString(4, medico.getApellido());
            sentencia.setObject(5, medico.getFechaNaci());
            sentencia.setString(6, medico.getCorreo());
            sentencia.setString(7, medico.getDireccion());
            sentencia.setInt(8, medico.getTelefono());
            sentencia.setDouble(9, medico.getSalario());
            sentencia.setString(10, medico.getEspecialidad());

            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(MedicoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_DELETE_MEDICO = "delete from medico where codigo=?";

    //Retorna true si logra ELIMINAR el atleta... false si no lo logra...
    public static boolean eliminar(Medico medico) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_DELETE_MEDICO);
            sentencia.setInt(1, medico.getCodigo());
            int fila = sentencia.executeUpdate();
            return fila > 0; //retorna true si hay un número de fila >0...
        } catch (SQLException ex) {
            Logger.getLogger(MedicoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_SELECT_MEDICO = "select * from medico where codigo=?";

    //Retorna un Objecto Medico si lo encuentra... y null si no lo encuentra..
    public static Medico getMedico(Integer codigo) {
        Medico medico = null;
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_SELECT_MEDICO);
            sentencia.setInt(1, codigo);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                medico = new Medico(
                        datos.getInt(1), //codigo
                        datos.getInt(2), //cedula
                        datos.getString(3), //nombre
                        datos.getString(4), //apellidos
                        datos.getDate(5), //fecha nacimiento
                        datos.getString(6), //correo
                        datos.getString(7), //direccion
                        datos.getInt(8), //telefono
                        datos.getInt(9), //salario
                        datos.getString(10)); //especialidad
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medico;
    }

    private static final String SQL_SELECT_MEDICOS = "select * from medico";

    public static ArrayList<Medico> getMedicos() {
        ArrayList<Medico> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_MEDICOS);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()) {
                lista.add(
                        new Medico(
                                datos.getInt(1), //codigo
                        datos.getInt(2), //cedula
                        datos.getString(3), //nombre
                        datos.getString(4), //apellidos
                        datos.getDate(5), //fecha nacimiento
                        datos.getString(6), //correo
                        datos.getString(7), //direccion
                        datos.getInt(8), //telefono
                        datos.getInt(9), //salario
                        datos.getString(10))); //especialidad                                      
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

}
