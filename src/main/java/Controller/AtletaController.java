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
import Model.Atleta;
import Gestion.AtletaGestion;
import Gestion.Reporte;
import java.util.List;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Santiago
 */
@Named(value = "atletaController")
@SessionScoped
public class AtletaController extends Atleta implements Serializable {

    public AtletaController() {
    }

    public void inserta() {
        //Si puedo insertar el atleta...
        if (AtletaGestion.insertar(this)) {
          this.setApellido("");
          this.setCedula(0);
          this.setCodigo(0);
          this.setDireccion("");
          this.setNombre("");
          this.setTelefono(0);
          this.setPeso(0.00);
          this.setCorreo("");
        
          
            
             FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "SUCCES", "Usuario Agregado Correctamente");
            PrimeFaces.current().dialog().showMessageDynamic(mensaje);
        
            
           
        
        
        
        } else { 
            
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error", "Usuario No Agregado");
            PrimeFaces.current().dialog().showMessageDynamic(mensaje);
           
        }
    }

    public String modifica() {
        //Si puedo modificar el atleta...
        if (AtletaGestion.modificar(this)) {
            return "list.xhtml";
        } else {  //si no pudo modificarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible indentificación duplicada...");
            FacesContext.getCurrentInstance().addMessage("editaAtletaForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String elimina() {
        //Si puedo eliminar el atleta...
        if (AtletaGestion.eliminar(this)) {
            return "list.xhtml";
        } else {  //si no pudo eliminarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No puede eliminar un atleta si ...");
            FacesContext.getCurrentInstance().addMessage("editaAtletaForm:identificacion", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(Integer codigo) {
        Atleta atleta = AtletaGestion.getAtleta(codigo);
        if (atleta != null) {
            this.setCodigo(atleta.getCodigo());
            this.setCedula(atleta.getCedula());
            this.setNombre(atleta.getNombre());
            this.setApellido(atleta.getApellido());
            this.setFechaNaci(atleta.getFechaNaci());
            this.setPeso(atleta.getPeso());
            this.setCorreo(atleta.getCorreo());
            this.setDireccion(atleta.getDireccion());
            this.setTelefono(atleta.getTelefono());
            return "edita.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    public List<Atleta> getAtletas() {
        return AtletaGestion.getAtletas();
    }

    private boolean noImprimir = true; //para apagar el boton de imprimir...

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaAtleta(Integer codigo) {
        Atleta atleta = AtletaGestion.getAtleta(codigo);
        if (atleta != null) {
            this.setCodigo(atleta.getCodigo());
            this.setCedula(atleta.getCedula());
            this.setNombre(atleta.getNombre());
            this.setApellido(atleta.getApellido());
            this.setFechaNaci(atleta.getFechaNaci());
            this.setPeso(atleta.getPeso());
            this.setCorreo(atleta.getCorreo());
            this.setDireccion(atleta.getDireccion());
            this.setTelefono(atleta.getTelefono());
            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setNombre("");
            this.setApellido("");
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Atleta no encontrado...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("certificacionNotasForm:identificacion", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }
    
  public void reporteAtleta() {

      
        String realPath="/Reportes/reportesAtleta.jasper";
        String nombreArchivoSalida="attachement; filename=reporteAtletas.pdf";
        Reporte.descargarPdf(realPath,null,nombreArchivoSalida);
        
}
  public String navegaAtleta(){
      this.borrarDatosAtleta();
  return "/Atleta/Atleta.xhtml";
  }
  
  public String navegaedita(){
      this.borrarDatosAtleta();
  return "/Atleta/edita.xhtml";
  }
  
  public String navegaReportes(){
     
  return "/Reportes/Reportes.xhtml";
  }
  
   public String navegaRespaldo(){
     
  return "/Respaldo/Respaldo.xhtml";
  }
  
  
  public void borrarDatosAtleta(){
  this.setApellido("");
  this.setCedula(0);
  this.setCodigo(0);
  this.setCorreo("");
  this.setDireccion("");
  this.setFechaNaci(null);
  this.setNombre("");
  this.setPeso(0.0);
  this.setTelefono(0);
  }

}
