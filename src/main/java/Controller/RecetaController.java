/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import Model.Receta;
import Gestion.RecetaGestion;
import java.util.List;

/**
 *
 * @author Santiago
 */
@Named(value = "recetaController")
@SessionScoped
public class RecetaController extends Receta implements Serializable {

    public RecetaController() {
    }

    public void inserta() {
      
        if (RecetaGestion.insertar(this)) {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succes", "Insertado");
            FacesContext.getCurrentInstance().addMessage("RecetaForm:dosis", mensaje);
            
            
        } else {  //si no pudo insertarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible codigo duplicado...");
            FacesContext.getCurrentInstance().addMessage("RecetaForm:dosis", mensaje);
           
        }
    }

    public String modifica() {
        //Si puedo modificar el atleta...
        if (RecetaGestion.modificar(this)) {
            return "list.xhtml";
        } else {  //si no pudo modificarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible codigo duplicado...");
            FacesContext.getCurrentInstance().addMessage("editaRecetaForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String elimina() {
        //Si puedo eliminar el atleta...
        if (RecetaGestion.eliminar(this)) {
            return "list.xhtml";
        } else {  //si no pudo eliminarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No puede eliminar una receta si ...");
            FacesContext.getCurrentInstance().addMessage("editaRecetaForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(Integer codigo) {
        Receta receta = RecetaGestion.getReceta(codigo);
        if (receta != null) {
            this.setCodigo(receta.getCodigo());
            this.setDosis(receta.getDosis());
            this.setDescripcion(receta.getDescripcion());
            this.setCantidad(receta.getCantidad());
            this.setFecha(receta.getFecha());
            return "edita.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    public List<Receta> getRecetas() {
        return RecetaGestion.getRecetas();
    }

    private boolean noImprimir = true; //para apagar el boton de imprimir...

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaReceta(Integer codigo) {
        Receta receta = RecetaGestion.getReceta(codigo);
        if (receta != null) {
            this.setCodigo(receta.getCodigo());
            this.setDosis(receta.getDosis());
            this.setDescripcion(receta.getDescripcion());
            this.setCantidad(receta.getCantidad());
            this.setFecha(receta.getFecha());
            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setCodigo(0);//revisar si se debe poner como 0
            this.setDescripcion("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Receta no encontrada...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("certificacionNotasForm:identificacion", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }

}
