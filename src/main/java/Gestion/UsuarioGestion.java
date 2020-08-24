/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Model.Conexion;
import Model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioGestion {

    private static final String SQL_SELECT_USUARIO = "select * from usuario where id_Usuario=?";

    public static Usuario validaUsuario(String idUsuario, String password) {
        Usuario usuario = null;
        try {
            PreparedStatement statement = Conexion.getConexion().prepareCall(SQL_SELECT_USUARIO);
            statement.setString(1, idUsuario);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next() && rs.getString(6).equals(password) && rs.getBoolean(8)) {
                usuario = new Usuario(
                        idUsuario,
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getBoolean(8)
                );

            }

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;

    }

    private static final String SQL_UPDATE_USUARIO_PASSWORD1 = "update usuario set password_1=?,password_2=? where id_Usuario=?";

    public static boolean updatePassword(Usuario usuario) {

        try {
            PreparedStatement statement = Conexion.getConexion().prepareStatement(SQL_UPDATE_USUARIO_PASSWORD1);
            statement.setString(1, usuario.getPassword2());
            statement.setString(2, usuario.getPassword1());
            statement.setString(3, usuario.getCodigo());

            return statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    private static final String SQL_INSERT_USUARIO = "INSERT INTO USUARIO (id_usuario,ROL,NOMBRE_U,APELLIDO_U,CORREO_U,PASSWORD_1,PASSWORD_2,ACTIVO)VALUES(?,?,?,?,?,?,?,?)";

    public static boolean insertar(Usuario usuario) {

        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_USUARIO);

            sentencia.setString(1, usuario.getCodigo());
            sentencia.setInt(2, usuario.getRol());
            sentencia.setString(3, usuario.getNombre());
            sentencia.setString(4, usuario.getApellido());
            sentencia.setString(5, usuario.getCorreo());
            sentencia.setString(6, usuario.getPassword1());
            sentencia.setString(7, usuario.getPassword2());
            sentencia.setBoolean(8, true);

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_USUARIOS = "select * from usuario";

    public static ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_USUARIOS);
            ResultSet datos = sentencia.executeQuery();
            while (datos.next()) {
                lista.add(
                        new Usuario(
                                datos.getString(1),
                                datos.getInt(2),
                                datos.getString(3),
                                datos.getString(4),
                                datos.getString(5),
                                datos.getString(6),
                                datos.getString(7),
                                datos.getBoolean(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private static final String SQL_DELETE_USUARIO = "delete from usuario where id_usuario=?";

    public static boolean eliminarConCodigo(String id_usuario) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_DELETE_USUARIO);
            sentencia.setString(1, id_usuario);
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_UPDATE_USUARIO
            = "update usuario set rol=?, nombre_u=?, apellido_u=?, correo_u=?, password_1=?,password_2=?,activo=?  where id_usuario=?";

    public static boolean modificar(Usuario usuario) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_USUARIO);

            sentencia.setInt(1, usuario.getRol());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3, usuario.getApellido());
            sentencia.setString(4, usuario.getCorreo());
            sentencia.setString(5, usuario.getPassword1());
            sentencia.setString(6, usuario.getPassword2());
            sentencia.setObject(7, usuario.isActivo());
            sentencia.setString(8, usuario.getCodigo());

            System.out.println( "Codigo Usuario : "+usuario.getCodigo());
            System.out.println(usuario.getPassword1());
                   
            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Usuario getUsuario(String id) {
        Usuario usuario = null;
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_SELECT_USUARIO);
            sentencia.setString(1, id);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                usuario = new Usuario(
                        datos.getString(1),
                        datos.getInt(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getString(5),
                        datos.getString(6),
                        datos.getString(7),
                        datos.getBoolean(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

}
