
package Model;


public class Medicamento {
    
    private int codigo_medicamento;
    private String nombre_md;
    private String descripcion_md;
    private int cantidad_md;

    public Medicamento() {
    }

    public Medicamento(int codigo_medicamento, String nombre_md, String descripcion_md, int cantidad_md) {
        this.codigo_medicamento = codigo_medicamento;
        this.nombre_md = nombre_md;
        this.descripcion_md = descripcion_md;
        this.cantidad_md = cantidad_md;
    }

    public int getCodigo_medicamento() {
        return codigo_medicamento;
    }

    public void setCodigo_medicamento(int codigo_medicamento) {
        this.codigo_medicamento = codigo_medicamento;
    }

    public String getNombre_md() {
        return nombre_md;
    }

    public void setNombre_md(String nombre_md) {
        this.nombre_md = nombre_md;
    }

    public String getDescripcion_md() {
        return descripcion_md;
    }

    public void setDescripcion_md(String descripcion_md) {
        this.descripcion_md = descripcion_md;
    }

    public int getCantidad_md() {
        return cantidad_md;
    }

    public void setCantidad_md(int cantidad_md) {
        this.cantidad_md = cantidad_md;
    }
    
}

