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
                        rs.getString(2),
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
    public static boolean updatePassword(Usuario usuario){
    
        try {
            PreparedStatement statement = Conexion.getConexion().prepareStatement(SQL_UPDATE_USUARIO_PASSWORD1);
        statement.setString(1,usuario.getPassword2());
        statement.setString(2,usuario.getPassword1());
        statement.setString(3,usuario.getCodigo());
        
        return statement.executeUpdate()>0;
        
        
        
        
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
           
       return false; 
      
    }
    
    
     private static final String SQL_INSERT_USUARIO= "INSERT INTO USUARIO (ROL,NOMBRE_U,APELLIDO_U,CORREO_U,PASSWORD_1,PASSWORD_2,ACTIVO)VALUES(?,?,?,?,?,?,?)";

   
    public static boolean insertar(Usuario usuario) {
       
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_USUARIO);
            
            sentencia.setString(1,  usuario.getRol());
            sentencia.setString(2, usuario.getNombre());
            sentencia.setString(3,  usuario.getApellido());
            sentencia.setString(4,  usuario.getCorreo());
            sentencia.setString(5,  usuario.getPassword1());
            sentencia.setString(6,  usuario.getPassword2());
            sentencia.setBoolean(7, true);
            
            return sentencia.executeUpdate()>0;
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    
    
        
    
}
