

import Gestion.UsuarioGestion;
import Model.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;


@Named("dtEditView")
@SessionScoped
public class EditView  implements Serializable {
     
  private  Usuario usuario;
    @Inject
 
     
    @PostConstruct
    public void init() {
        
      
    }
 
    
    public void onRowEdit(Usuario user) {
  
 
 
        
   
        if(UsuarioGestion.modificar(user)){
            
            System.out.println("Base de datos ok");
            
            
        } else {
            System.out.println("hecho");
            FacesMessage msg = new FacesMessage("Car Edited","Edicion Incorrecta");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        
        }
    }
     
    public void onRowCancel(RowEditEvent<Usuario> event) {
       
        
        
        
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getCodigo());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    
    public List<Usuario> getUsuarios() {
        return UsuarioGestion.getUsuarios();
    } 
    
}