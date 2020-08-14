
package Model;

import java.sql.Date;

public class Expediente {
    
    private int codigo_expediente;
    private Date fecha_e;
    private int codigo_consulta;

    public Expediente() {
    }

    public Expediente(int codigo_expediente, Date fecha_e, int codigo_consulta) {
        this.codigo_expediente = codigo_expediente;
        this.fecha_e = fecha_e;
        this.codigo_consulta = codigo_consulta;
    }

    public int getCodigo_expediente() {
        return codigo_expediente;
    }

    public void setCodigo_expediente(int codigo_expediente) {
        this.codigo_expediente = codigo_expediente;
    }

    public Date getFecha_e() {
        return fecha_e;
    }

    public void setFecha_e(Date fecha_e) {
        this.fecha_e = fecha_e;
    }

    public int getCodigo_consulta() {
        return codigo_consulta;
    }

    public void setCodigo_consulta(int codigo_consulta) {
        this.codigo_consulta = codigo_consulta;
    }
    
}
