
package Controller;
import Gestion.MedicamentoGestion;
import Gestion.ProveedorGestion;
import Model.Medicamento;
import Model.Proveedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "medicamentoController")
@SessionScoped
public class MedicamentoController extends Medicamento implements Serializable {

    public MedicamentoController() {
    }

    public String insertM() {

        if (MedicamentoGestion.insertarMedicamento(this)) {

            return "principal.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Error");
            FacesContext.getCurrentInstance().addMessage("registrarMedicamentoForm:codigo_medicamento", mensaje);

            return "registrarMedicamento.xhtml";

        }
    }


    public String modificaM(String codigo_medicamento) {
        //Si puedo modificar el medicamento
        if (MedicamentoGestion.modificarMedicamento(this)) {
            return "ModificarMedicamento.xhtml";
        } else {  //si no pudo modificarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible codigo duplicado...");
            FacesContext.getCurrentInstance().addMessage("editaMedicamentoForm:codigo_medicamento", mensaje);
            return "edita.xhtml";
        }
    }

    public String eliminaM() {
        //Si puedo eliminar el medicamento...
        if (MedicamentoGestion.eliminarMedicamento(this)) {
            return "list.xhtml";
        } else {  //si no pudo eliminarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No puede eliminar un medicamento ...");
            FacesContext.getCurrentInstance().addMessage("editaMedicamentoForm:codigo_medicamento", mensaje);
            return "edita.xhtml";
        }
    }

    public String editaM(String codigo_medicamento) {
        Medicamento medicamento = MedicamentoGestion.getMedicamento(codigo_medicamento);
        if (medicamento != null) {
            
            this.setCodigo_medicamento(medicamento.getCodigo_medicamento());
            this.setNombre_md(medicamento.getNombre_md());
            this.setDescripcion_md(medicamento.getDescripcion_md());
            this.setCantidad_md(medicamento.getCantidad_md());

            return "edita.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    public List<Medicamento> getMedicamentos() {
        return MedicamentoGestion.getMedicamentos();
    }

    private boolean noImprimir = true; //para apagar el boton de imprimir...

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaM(String codigo_medicamento) {
        Medicamento medicamento =MedicamentoGestion.getMedicamento(codigo_medicamento);
        if (medicamento != null) {
            
            this.setCodigo_medicamento(medicamento.getCodigo_medicamento());
            this.setNombre_md(medicamento.getNombre_md());
            this.setDescripcion_md(medicamento.getDescripcion_md());
            this.setCantidad_md(medicamento.getCantidad_md());

            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setNombre_md("");
          
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Medicamento no encontrado...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("buscaMedicamentoForm:codigo_medicamento", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }
    
   public String navegaMedicamento(){
   this.borrarDatosMedicamento();
   return "/Medicamento/Medicamento.xhtml";
   }
    public String navegaModificarMedicamento(){
   this.borrarDatosMedicamento();
   return "/Medicamento/ModificarMedicamento.xhtml";
   }
   
 public void borrarDatosMedicamento(){
   this.setCantidad_md(0);
 this.setCodigo_medicamento(0);
 this.setDescripcion_md("");
 this.setNombre_md("");
   }
    
}

