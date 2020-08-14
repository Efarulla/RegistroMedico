package Gestion;

import Model.Conexion;
import Model.Proveedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProveedorGestion {

    private static final String SQL_INSERT_PROVEEDOR = "insert into proveedor values (?,?,?,?,?,?)";

    public static boolean insertarProveedor(Proveedor proveedor) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_PROVEEDOR);
            sentencia.setInt(1, proveedor.getCodigo_P());
            sentencia.setString(2, proveedor.getNombre_P());
            sentencia.setString(3, proveedor.getDireccion_P());
            sentencia.setString(4, proveedor.getProvincia_P());
            sentencia.setInt(5, proveedor.getTelefono_P());
            sentencia.setObject(6, proveedor.getCorreo_P());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_PROVEEDOR = "select * from proveedor where codigo_proveedor=?";

    public static Proveedor getProveedor(String codigo_P) {
        Proveedor proveedor = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_PROVEEDOR);
            sentencia.setString(1, codigo_P);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                proveedor = new Proveedor(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getString(4),
                        datos.getInt(5),
                        datos.getString(6));
                       
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedor;
    }
    private static final String 
            SQL_UPDATE_PROVEEDOR = "update proveedor set codigo_p=?, direccion_p=?,"
            + "provincia_P=?, telefono_p=?, correo_p=? where nombre_p=?";  
    public static boolean modificar(Proveedor proveedor) {        
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_PROVEEDOR);
            sentencia.setInt(1, proveedor.getCodigo_P());
            sentencia.setString(2, proveedor.getNombre_P());
            sentencia.setString(3, proveedor.getDireccion_P());
            sentencia.setString(4, proveedor.getProvincia_P());
            sentencia.setInt(5, proveedor.getTelefono_P());
            sentencia.setObject(6, proveedor.getCorreo_P());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
    private static final String 
            SQL_DELETE_PROVEEDOR = "delete from proveedor where codigo_proveedor=?";
    
   
    public static boolean eliminar(Proveedor proveedor) {        
        try {
            PreparedStatement sentencia =  Conexion.getConexion().prepareStatement(SQL_DELETE_PROVEEDOR);                    
            sentencia.setInt(1,proveedor.getCodigo_P());
            int fila=sentencia.executeUpdate();
            return fila>0; 
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
    private static final String SQL_SELECT_PROVEEDORES = "Select * from proveedor where codigo_p=?";

    public static ArrayList<Proveedor> getProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();

        try {
            PreparedStatement consultar = Conexion.getConexion().prepareStatement(SQL_SELECT_PROVEEDORES);
            ResultSet rs = consultar.executeQuery();
            while (rs.next()) {
                lista.add(new Proveedor(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
