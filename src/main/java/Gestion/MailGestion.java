/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gestion;

import Model.Conexion;
import Model.Mail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Niyaz-laptop
 */
public class MailGestion {

    public MailGestion() {
    }
    
    
    
    
    
    public static  Transport sendMail(String toMail,String message,String cuerpo) throws AddressException, MessagingException{
    
        Properties props=System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        
        
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.fallback", "false");
                
                Session mailSession=Session.getDefaultInstance(props, null);
               // mailSession.setDebug(true);
                
                Message mailMessage=new MimeMessage(mailSession);
                
                mailMessage.setFrom(new InternetAddress("info@info.com"));
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
                mailMessage.setContent(message,"text/html");
                mailMessage.setSubject(cuerpo);
                
                Transport transport=mailSession.getTransport("smtp");
                transport.connect("smtp.gmail.com", "cuentaparapruebainfo@gmail.com","QWERTY13579");
                transport.sendMessage(mailMessage, mailMessage.getAllRecipients());   
                
            return transport;    
    }
    
    
       
     private static final String SQL_SELECT_MAIL= "select * from usuario where ID_USUARIO=?";
    
    public static Mail pw(String codigo) {
       Mail mail=null;
        try {
            PreparedStatement statement = Conexion.getConexion().prepareCall(SQL_SELECT_MAIL);
            statement.setString(1, codigo);
            ResultSet rs = statement.executeQuery();
            if (rs != null && rs.next()) {
               mail = new Mail();
                       mail.setCodigo(codigo);
                       mail.setMessage(rs.getString(6));
                       mail.setToMail(rs.getString(5));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioGestion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mail;
        
    }
    
    
    
    
}
