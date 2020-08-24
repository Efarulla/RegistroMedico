/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Gestion.MailGestion;
import Model.Mail;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.Transport;

/**
 *
 * @author Eduardo
 */
@Named(value = "mailController")
@SessionScoped
public class MailController extends Mail implements Serializable {

    /**
     * Creates a new instance of ControllerMail
     */
    public MailController() {
    }
    
    
      public String send() {
          Transport t;
            Mail m= MailGestion.pw(this.getCodigo());
            // Mail mail= UsuarioGestion.pw(this.getCodigo());
             if(m!=null){
                
                 this.setToMail(m.getToMail());
                 this.setMessage(m.getMessage());
                 
              try {
                  t=MailGestion.sendMail(this.getToMail(),this.getMessage(),this.getCuerpo());
                  
                 
                  
                  
                  t.close();
          
                  System.out.println("paso1");
        } catch (MessagingException ex) {
            Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "index.xhtml";
       
        }else{
                 
     FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
            "Error","Usuario No Encontrado");
            FacesContext.getCurrentInstance().addMessage("FORGETpasswordForm:mensaje", mensaje);
           
             
             
             
             }
    return "pw.xhtml";
             
             }
          
          
              
        
       
      
      
      
}
