
package Model;

import java.sql.Date;


public class Proveedor {
    
    private int codigo_P;
    private String nombre_P;
    private String direccion_P;
    private String provincia_P;
    private int telefono_P;
    private String correo_P;

    public Proveedor() {
    }

    public Proveedor(int codigo_P, String nombre_P, String direccion_P, String provincia_P, int telefono_P, String correo_P) {
        this.codigo_P = codigo_P;
        this.nombre_P = nombre_P;
        this.direccion_P = direccion_P;
        this.provincia_P = provincia_P;
        this.telefono_P = telefono_P;
        this.correo_P = correo_P;
    }

    public int getCodigo_P() {
        return codigo_P;
    }

    public void setCodigo_P(int codigo_P) {
        this.codigo_P = codigo_P;
    }

    public String getNombre_P() {
        return nombre_P;
    }

    public void setNombre_P(String nombre_P) {
        this.nombre_P = nombre_P;
    }

    public String getDireccion_P() {
        return direccion_P;
    }

    public void setDireccion_P(String direccion_P) {
        this.direccion_P = direccion_P;
    }

    public String getProvincia_P() {
        return provincia_P;
    }

    public void setProvincia_P(String provincia_P) {
        this.provincia_P = provincia_P;
    }

    public int getTelefono_P() {
        return telefono_P;
    }

    public void setTelefono_P(int telefono_P) {
        this.telefono_P = telefono_P;
    }

    public String getCorreo_P() {
        return correo_P;
    }

    public void setCorreo_P(String correo_P) {
        this.correo_P = correo_P;
    }

   
}



    

