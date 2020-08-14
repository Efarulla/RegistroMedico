package Gestion;

import Model.Conexion;
import Model.Expediente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ExpedienteGestion {

    private static final String SQL_INSERT_EXPEDIENTE = "insert into expediente values (?,?,?)";

    public static boolean insertarExpediente(Expediente expediente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_EXPEDIENTE);
            sentencia.setInt(1, expediente.getCodigo_expediente());
            sentencia.setDate(2, expediente.getFecha_e());
            sentencia.setInt(3, expediente.getCodigo_consulta());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_EXPEDIENTE = "select * from expediente where codigo_expediente=?";

    public static Expediente getExpediente(String codigo_expediente) {
        Expediente expediente = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_EXPEDIENTE);
            sentencia.setString(1, codigo_expediente);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                expediente = new Expediente(
                        datos.getInt(1),
                        datos.getDate(2),
                        datos.getInt(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return expediente;
    }
    private static final String SQL_UPDATE_EXPEDIENTE = "update expediente set fecha_e=?, codigo_consulta=?," + "where codigo_expediente=?";
           

    public static boolean modificarExpediente(Expediente expediente) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_UPDATE_EXPEDIENTE);
            sentencia.setInt(1, expediente.getCodigo_expediente());
            sentencia.setDate(2, expediente.getFecha_e());
            sentencia.setInt(3, expediente.getCodigo_consulta());
            
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    private static final String SQL_DELETE_EXPEDIENTE= "delete from expediente where codigo_expediente=?";

    public static boolean eliminarExpediente(Expediente expediente) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETE_EXPEDIENTE);
            sentencia.setInt(1,expediente.getCodigo_expediente() );
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_EXPEDIENTES = "select * from expediente where codigo_expediente=?";
    

    public static ArrayList<Expediente> getExpedientes() {
        ArrayList<Expediente> lista = new ArrayList<>();

        try {
            PreparedStatement consultar = Conexion.getConexion().prepareStatement(SQL_SELECT_EXPEDIENTES);
            ResultSet rs = consultar.executeQuery();
            while (rs.next()) {
                lista.add(new Expediente(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getInt(3)));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
}
