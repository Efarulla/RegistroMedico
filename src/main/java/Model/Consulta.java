
package Model;

import java.util.Date;

/**
 *
 * @author Eduardo
 */
public class Consulta {
   
    private int codigo;
    private String alergia;
    private String sintoma;
    private String padecimiento;
    private String observacion;
    private Date fecha;
    private int codigoAtleta;
    private int codigoMedico;
    private int codigoReceta;

    public Consulta(int codigo, String alergia, String sintoma, String padecimiento, String observacion, Date fecha, int codigoAtleta, int codigoMedico, int codigoReceta) {
        this.codigo = codigo;
        this.alergia = alergia;
        this.sintoma = sintoma;
        this.padecimiento = padecimiento;
        this.observacion = observacion;
        this.fecha = fecha;
        this.codigoAtleta = codigoAtleta;
        this.codigoMedico = codigoMedico;
        this.codigoReceta = codigoReceta;
    }

    public Consulta() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getSintoma() {
        return sintoma;
    }

    public void setSintoma(String sintoma) {
        this.sintoma = sintoma;
    }

    public String getPadecimiento() {
        return padecimiento;
    }

    public void setPadecimiento(String padecimiento) {
        this.padecimiento = padecimiento;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigoAtleta() {
        return codigoAtleta;
    }

    public void setCodigoAtleta(int codigoAtleta) {
        this.codigoAtleta = codigoAtleta;
    }

    public int getCodigoMedico() {
        return codigoMedico;
    }

    public void setCodigoMedico(int codigoMedico) {
        this.codigoMedico = codigoMedico;
    }

    public int getCodigoReceta() {
        return codigoReceta;
    }

    public void setCodigoReceta(int codigoReceta) {
        this.codigoReceta = codigoReceta;
    }
    
    
    
    
    
    
    
    
    
    
    
    
}
