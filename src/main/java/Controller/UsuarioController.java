/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Gestion.UsuarioGestion;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Eduardo
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }
    
    
    public String valida(){
    Usuario usuario=UsuarioGestion.validaUsuario(this.getCodigo(), this.getPassword1());
        
    if(usuario!=null){
      this.setNombre(usuario.getNombre());
      this.setRol(usuario.getRol());
      this.setApellido(usuario.getApellido());
      this.setCorreo(usuario.getCorreo());
      this.setPassword2(usuario.getPassword2());
    this.setActivo(usuario.isActivo());
     
    return "principal.xhtml";
    }else{
     FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","ERROR DE USUARIO");
            FacesContext.getCurrentInstance().addMessage("loginForm:mensaje", mensaje);
           
   return "index.xhtml";
    }
    
    
    }
    
    
    
    public String cambioPassword(){
        Usuario usuario=UsuarioGestion.validaUsuario(this.getCodigo(), this.getPassword1());
        
        if(usuario!=null && this.getPassword2()!=this.getPassword1()){
            usuario.setPassword1(this.getPassword1());
             usuario.setPassword2(this.getPassword2());
           
             if(UsuarioGestion.updatePassword(usuario)){
             return "index.xhtml";
             
             }else{
                  FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Error De Actualizacion");
            FacesContext.getCurrentInstance().addMessage("passwordForm:password1", mensaje);
             
             return "OlvidoPrueba.xhtml";
             }
            
            }else{
              FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Error De Actualización las contraseñas deben ser diferentes");
            FacesContext.getCurrentInstance().addMessage("passwordForm:password2", mensaje);
             
             return "OlvidoPrueba.xhtml";
        
        
        
        }
        
         
     
     }
       
     public String insert(){
     
     if(UsuarioGestion.insertar(this)){
     
     return "principal.xhtml";
     }else{
      FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Error De Actualización las contraseñas deben ser diferentes");
            FacesContext.getCurrentInstance().addMessage("registrarUsuario:id", mensaje);
             
             return "registrarUsuario.xhtml";
        
     }
     
     
     
     }
    
    
   

}
    

