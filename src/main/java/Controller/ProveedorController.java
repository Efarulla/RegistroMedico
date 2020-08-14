
package Controller;

import Gestion.ProveedorGestion;
import Model.Proveedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController  extends Proveedor implements Serializable {

  
    public ProveedorController() {
    }
   
    public List<Proveedor> getProveedores(){
        return ProveedorGestion.getProveedores();
        
    }
}

