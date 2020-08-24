package Gestion;

import Model.Conexion;
import Model.Medicamento;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicamentoGestion {

    private static final String SQL_INSERT_MEDICAMENTO = "INSERT INTO MEDICAMENTO (NOMBRE_MD,DESCRIPCION_MD,CANTIDAD_MD)\n" +
"VALUES (?,?,?)";

    public static boolean insertarMedicamento(Medicamento medicamento) {
        try {

            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_INSERT_MEDICAMENTO);
            //sentencia.setInt(1, medicamento.getCodigo_medicamento());
            sentencia.setString(1, medicamento.getNombre_md());
            sentencia.setString(2, medicamento.getDescripcion_md());
            sentencia.setInt(3, medicamento.getCantidad_md());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_MEDICAMENTO = "select * from medicamento where codigo_medicamento=?";

    public static Medicamento getMedicamento(String codigo_medicamento) {
        Medicamento medicamento = null;
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_SELECT_MEDICAMENTO);
            sentencia.setString(1, codigo_medicamento);
            ResultSet datos = sentencia.executeQuery();
            if (datos.next()) {
                medicamento = new Medicamento(
                        datos.getInt(1),
                        datos.getString(2),
                        datos.getString(3),
                        datos.getInt(4));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicamento;
    }
    private static final String SQL_UPDATE_MEDICAMENTO = "update medicamento set nombre_md=?,"
            + "descripcion_md=?, cantidad_md=?,where codigo_medicamento?";

    public static boolean modificarMedicamento(Medicamento medicamento) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_UPDATE_MEDICAMENTO);
            sentencia.setInt(1, medicamento.getCodigo_medicamento());
            sentencia.setString(2, medicamento.getNombre_md());
            sentencia.setString(3, medicamento.getDescripcion_md());
            sentencia.setInt(4, medicamento.getCantidad_md());

            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    private static final String SQL_DELETE_MEDICAMENTO = "delete from medicamento where codigo_medicamento=?";

    public static boolean eliminarMedicamento(Medicamento medicamento) {
        try {
            PreparedStatement sentencia = Conexion.getConexion().prepareStatement(SQL_DELETE_MEDICAMENTO);
            sentencia.setInt(1, medicamento.getCodigo_medicamento());
            int fila = sentencia.executeUpdate();
            return fila > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static final String SQL_SELECT_MEDICAMENTOS = "Select * from medicamento ";

    public static ArrayList<Medicamento> getMedicamentos() {
        ArrayList<Medicamento> lista2 = new ArrayList<>();

        try {
            PreparedStatement consultar = Conexion.getConexion().prepareStatement(SQL_SELECT_MEDICAMENTOS);
            ResultSet rs = consultar.executeQuery();
            while (rs.next()) {
                lista2.add(
                        new Medicamento(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
                        

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista2;
    }
}
