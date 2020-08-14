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
import Model.Medico;
import Gestion.MedicoGestion;
import java.util.List;

/**
 *
 * @author Santiago
 */
@Named(value = "medicoController")
@SessionScoped
public class MedicoController extends Medico implements Serializable {

    public MedicoController() {
    }

    public String inserta() {
        //Si puedo insertar el medico...
        if (MedicoGestion.insertar(this)) {
            return "list.xhtml";
        } else {  //si no pudo insertarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible indentificación duplicada...");
            FacesContext.getCurrentInstance().addMessage("editaMedicoForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String modifica() {
        //Si puedo modificar el atleta...
        if (MedicoGestion.modificar(this)) {
            return "list.xhtml";
        } else {  //si no pudo modificarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible indentificación duplicada...");
            FacesContext.getCurrentInstance().addMessage("editaMedicoForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String elimina() {
        //Si puedo eliminar el atleta...
        if (MedicoGestion.eliminar(this)) {
            return "list.xhtml";
        } else {  //si no pudo eliminarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No puede eliminar un medico si ...");
            FacesContext.getCurrentInstance().addMessage("editaMedicoForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(Integer codigo) {
        Medico medico = MedicoGestion.getMedico(codigo);
        if (medico != null) {
            this.setCodigo(medico.getCodigo());
            this.setCedula(medico.getCedula());
            this.setNombre(medico.getNombre());
            this.setApellido(medico.getApellido());
            this.setFechaNaci(medico.getFechaNaci());
            this.setCorreo(medico.getCorreo());
            this.setDireccion(medico.getDireccion());
            this.setTelefono(medico.getTelefono());
            this.setSalario(medico.getSalario());
            this.setEspecialidad(medico.getEspecialidad());
            return "edita.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    public List<Medico> getMedicos() {
        return MedicoGestion.getMedicos();
    }

    private boolean noImprimir = true; //para apagar el boton de imprimir...

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaMedico(Integer codigo) {
        Medico medico = MedicoGestion.getMedico(codigo);
        if (medico != null) {
            this.setCodigo(medico.getCodigo());
            this.setCedula(medico.getCedula());
            this.setNombre(medico.getNombre());
            this.setApellido(medico.getApellido());
            this.setFechaNaci(medico.getFechaNaci());
            this.setCorreo(medico.getCorreo());
            this.setDireccion(medico.getDireccion());
            this.setTelefono(medico.getTelefono());
            this.setSalario(medico.getSalario());
            this.setEspecialidad(medico.getEspecialidad());
            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setNombre("");
            this.setApellido("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Medico no encontrado...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("certificacionNotasForm:identificacion", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }

}
