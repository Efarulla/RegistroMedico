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
import java.io.StringWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 *
 * @author Santiago
 */
public class AtletaGestion {

    private static final String SQL_INSERT_ATLETA = "INSERT INTO ATLETA (CEDULA_A,NOMBRE_A,APELLIDO_A,FECHA_NACI_A,PESO_A,CORREO_A,DIRECCION_A,TELEFONO_A) \n"
            + "VALUES (?,?,?,?,?,?,?,?)";

    public static boolean insertar(Atleta atleta) {
        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_INSERT_ATLETA);

            sentencia.setInt(1, atleta.getCedula());
            sentencia.setString(2, atleta.getNombre());
            sentencia.setString(3, atleta.getApellido());
            sentencia.setObject(4, atleta.getFechaNaci());
            sentencia.setDouble(5, atleta.getPeso());
            sentencia.setString(6, atleta.getCorreo());
            sentencia.setString(7, atleta.getDireccion());
            sentencia.setInt(8, atleta.getTelefono());
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;  //devuelve falso... llegado a este punto...
    }

    private static final String SQL_UPDATE_ATLETA
            = "update atleta set cedula_a=?, nombre_A=?, apellido_a=?, fecha_Naci_a=?, peso_a=?, correo_a=?, direccion_a=?, telefono_a=? where codigo_atleta=?";

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

    public static String getJson() {
        Atleta atleta = new Atleta();
        String tiraJson = "";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            PreparedStatement sentencia
                    = Conexion.getConexion().prepareStatement(SQL_SELECT_ATLETAS);

            ResultSet rs = sentencia.executeQuery();
            while (rs.next()) {
                atleta.setCodigo(rs.getInt(1));
                atleta.setCedula(rs.getInt(2));
                atleta.setNombre(rs.getString(3));
                atleta.setApellido(rs.getString(4));
                atleta.setFechaNaci(rs.getDate(5));
                atleta.setPeso(rs.getDouble(6));
                atleta.setCorreo(rs.getString(7));
                atleta.setDireccion(rs.getString(8));
                atleta.setTelefono(rs.getInt(9));
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                JsonObject objetoJson = jsonObjectBuilder.
                        add("codigo", atleta.getCodigo()).
                        add("cedula", atleta.getCedula()).
                        add("nombre", atleta.getNombre()).
                        add("apellido", atleta.getApellido()).
                        add("fechaNaci", dateFormat.format(atleta.getFechaNaci())).
                        add("peso", atleta.getPeso()).
                        add("corre", atleta.getCorreo()).
                        add("direccion", atleta.getDireccion()).
                        add("telefono", atleta.getTelefono()).build();

                StringWriter salida = new StringWriter();
                JsonWriter jsonWriter = Json.createWriter(salida);
                jsonWriter.writeObject(objetoJson);
                if (tiraJson == null) {
                    tiraJson = salida.toString() + "\n";
                }else{
                tiraJson = tiraJson + salida.toString() + "\n";
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AtletaGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tiraJson;
    }

}
