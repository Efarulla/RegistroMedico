package Gestion;

import Model.Conexion;
import Model.Consulta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class ConsultaGestion {

    private static final String SQL_SELECT_CONSULTA = "select * from consulta where codigo_atleta=?";

    public static ArrayList<Consulta> getConsultas(int codigo) {
        ArrayList<Consulta> consultas = new ArrayList<>();
        try {
            PreparedStatement statement = Conexion.getConexion().prepareStatement(SQL_SELECT_CONSULTA);
            statement.setInt(1, codigo);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {

                consultas.add(new Consulta(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        codigo,
                        rs.getInt(8),
                        rs.getInt(9)
                ));

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return consultas;
    }

    private static final String SQL_INSERT_CONSULTA = "INSERT INTO CONSULTA (ALERGIA_C,SINTOMA_C,PADECIMIENTO_C,OBSERVACION_C,FECHA_C,CODIGO_ATLETA,CODIGO_MEDICO,CODIGO_RECETA) VALUES(?,?,?,?,?,?,?,?)";

    //Retorna true si logra insertar el estudiante... false si no lo logra...
    public static boolean insertar(Consulta consulta) {

        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_CONSULTA);
           
            sentencia.setString(1, consulta.getAlergia());
            sentencia.setString(2, consulta.getSintoma());
            sentencia.setString(3, consulta.getPadecimiento());
            sentencia.setString(4, consulta.getObservacion());
            sentencia.setObject(5, consulta.getFecha());
            sentencia.setInt(6, consulta.getCodigoAtleta());
            sentencia.setInt(7, consulta.getCodigoMedico());
            sentencia.setInt(8, consulta.getCodigoReceta());

            return sentencia.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
