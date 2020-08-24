/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Gestion.MailGestion;
import Gestion.UsuarioGestion;
import Model.Mail;
import Model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Eduardo
 */
@Named(value = "usuarioController")
@SessionScoped
public class UsuarioController extends Usuario implements Serializable {

    
    public boolean respaldo(){
    return this.getRol()==3;
    }
    
    /**
     * Creates a new instance of UsuarioController
     */
    public UsuarioController() {
    }

    public String valida() {
        Usuario usuario = UsuarioGestion.validaUsuario(this.getCodigo(), this.getPassword1());

        if (usuario != null) {
            this.setNombre(usuario.getNombre());
            this.setRol(usuario.getRol());
            this.setApellido(usuario.getApellido());
            this.setCorreo(usuario.getCorreo());
            this.setPassword2(usuario.getPassword2());
            this.setActivo(usuario.isActivo());

            return "principal.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "ERROR DE USUARIO");
            FacesContext.getCurrentInstance().addMessage("loginForm:mensaje", mensaje);

            return "index.xhtml";
        }

    }

    public String cambioPassword() {
        Usuario usuario = UsuarioGestion.validaUsuario(this.getCodigo(), this.getPassword1());

        if (usuario != null && this.getPassword2() != this.getPassword1()) {
            usuario.setPassword1(this.getPassword1());
            usuario.setPassword2(this.getPassword2());

            if (UsuarioGestion.updatePassword(usuario)) {
                return "index.xhtml";

            } else {
                FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error", "Error De Actualizacion");
                FacesContext.getCurrentInstance().addMessage("passwordForm:password1", mensaje);

                return "OlvidoPrueba.xhtml";
            }

        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Error De Actualización las contraseñas deben ser diferentes");
            FacesContext.getCurrentInstance().addMessage("passwordForm:password2", mensaje);

            return "OlvidoPrueba.xhtml";

        }

    }

    public void insert() {

        if (UsuarioGestion.insertar(this)) {

            this.send();

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "SUCCES", "Usuario Agregado Correctamente");
            PrimeFaces.current().dialog().showMessageDynamic(mensaje);

            this.setCodigo("");
            this.setCorreo("");
            this.setNombre("");
            this.setPassword1("");
            this.setApellido("");

        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posiblemente un usuario con ese codigo ya este registrado");
            FacesContext.getCurrentInstance().addMessage("registrarUsuario:codigo", mensaje);

        }

    }

    public void send() {
        Transport t;
        Mail m = MailGestion.pw(this.getCodigo());
        // Mail mail= UsuarioGestion.pw(this.getCodigo());
        if (m != null) {

            //  this.setToMail(m.getToMail());
            // this.setMessage(m.getMessage());
            try {
                t = MailGestion.sendMail(this.getCorreo(), "Su Password es: " + this.getPassword1() + "\n                  Su Codigo De Usuario es: " + this.getCodigo(), "Gracias Por Registrarse, Sus Datos de Acceso Son los Siguientes:");

                t.close();

                System.out.println("paso1");
            } catch (MessagingException ex) {
                Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Usuario No Encontrado");
            FacesContext.getCurrentInstance().addMessage("FORGETpasswordForm:mensaje", mensaje);

        }

    }

    private int id; //El id para buscar articulos
    private String tiraJson = "xxxx"; //área para pasar un objetoJson completo private String salida; 
    private final String URI = "https://apis.gometa.org/cedulas";
    private String salida; //para mostrar las respuestas de los servicios web

    public void hacerGet() {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(URI + "/" + this.getCodigo() + "&key=byhkkLzzGpK1VZF");
        JsonObject response = target.request(
                MediaType.APPLICATION_JSON).get(JsonObject.class);

        if (response.asJsonObject().toString().indexOf("cedula") > 0) { // por alguan razon no se puede acceder al campo resultcount en el arreglo del jason para verificar si hay resultados es por eso que se utiliza "cedula"

            JsonArray j = response.getJsonArray("results"); // 
            response = j.getJsonObject(0);

            this.setApellido(response.getString("lastname")); // 
            this.setNombre(response.getString("firstname"));
            this.setCorreo(response.getString("firstname1").charAt(0) + response.getString("lastname1") + response.getString("cedula").substring(4) + "@ufide.ac.cr");

            
            // para el manejo de errores
            
        } else if (response.asJsonObject().toString().indexOf("error") > 0) { 

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", response.getString("error"));
            FacesContext.getCurrentInstance().addMessage("registrarUsuario:codigo", mensaje);
            this.setApellido("");
            this.setNombre("");
            this.setCorreo("");

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Valide el codigo ingresado");
            FacesContext.getCurrentInstance().addMessage("registrarUsuario:codigo", mensaje);
            this.setApellido("");
            this.setNombre("");
            this.setCorreo("");

        }

    }
    
    public String navegaRegistrarUsuario(){
    this.borraDatos();
    return "/Usuario/RegistrarUsuario.xhtml";
    
    
    }
    
     public String navegaRecuperarPw(){
    this.borraDatos();
    return "/Usuario/RecuperarPw.xhtml";
    
    }
     
     public String cerrarSesion(){
    this.borraDatos();
    return "/index.xhtml";
    
    }
     
      public String navegaCambioPw(){
   this.borraDatos();
    return "/Usuario/CambioPw.xhtml";
    
    }
    
      public void borraDatos(){
       this.setApellido("");
    this.setCorreo("");
    this.setPassword1("");
    this.setPassword2("");
    this.setNombre("");
    this.setCodigo("");
    
      
      }
    
      public List<Usuario> getUsuarios() {
        return UsuarioGestion.getUsuarios();
    }
    
      
      
      public void modifica(String id){
          Usuario usuario=UsuarioGestion.getUsuario(id);
        if (usuario!=null) {
            
            this.setCodigo(usuario.getCodigo());
            this.setNombre(usuario.getNombre());
            this.setApellido(usuario.getApellido());
            this.setCorreo(usuario.getCorreo());
            this.setPassword1(usuario.getPassword1());
             this.setPassword2(usuario.getPassword2());
            this.setActivo(usuario.isActivo());
            this.setRol(usuario.getRol());
        }
        
      if(UsuarioGestion.modificar(this)){
      FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Succses", "Modificado Correctamente");
            FacesContext.getCurrentInstance().addMessage("usuarioForm:mensaje", mensaje);
}else{
       FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Error", "No se Modifico");
            FacesContext.getCurrentInstance().addMessage("usuarioForm:mensaje", mensaje);

      
      
      }
      
      }
      
   
}

/**
 *
 * @author Eduardo
 *
 *
 * Estructura del JSON , HAY UN ARRAY LLAMADO RESULTS { "results" : [ {
 * "lastname" : "FARULLA BARRANTES", "firstname1" : "EDUARDO", "rawcedula" :
 * "11398036234", ç"cedula" : "113980362", "lastname2" : "BARRANTES",
 * "guess_type" : "FISICA", "admin" : "01", "lastname1" : "FARULLA", "type"
 * :"F", "fullname" : "EDUARDO STEVEN FARULLA BARRANTES", "temp" : null,
 * "firstname" : "EDUARDO STEVEN", "class" : "F", "firstname2" : "STEVEN" } ],
 * "license" : "https://en.wikipedia.org/wiki/Beerware", "database_date"
 * :"20191028", "resultcount" : 1, "query" : "113980362" }
 *
 *
 *
 *
 *
 *
 *
 */
        
        
        
        
    


