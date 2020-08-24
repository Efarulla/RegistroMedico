package Controller;

import Gestion.ProveedorGestion;
import Gestion.UsuarioGestion;
import Model.Proveedor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "proveedorController")
@SessionScoped
public class ProveedorController extends Proveedor implements Serializable {

    public ProveedorController() {
    }

    public String insertP() {
        if (ProveedorGestion.insertarProveedor(this)) {

            return "principal.xhtml";
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Error");
            FacesContext.getCurrentInstance().addMessage("registrarProveedorForm:codigo_P", mensaje);

            return "registrarProveedor.xhtml";

        }
    }

    public String modificaP() {
        //Si puedo modificar el proveedor
        if (ProveedorGestion.modificarP(this)) {
            return "list.xhtml";
        } else {  //si no pudo modificarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "Posible codigo duplicado...");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:codigo_proveedor", mensaje);
            return "edita.xhtml";
        }
    }

    public String eliminaP() {
        //Si puedo eliminar el atleta...
        if (ProveedorGestion.eliminarP(this)) {
            return "list.xhtml";
        } else {  //si no pudo eliminarlo
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Error", "No puede eliminar un proveedor ...");
            FacesContext.getCurrentInstance().addMessage("editaProveedorForm:codigo_proveedor", mensaje);
            return "edita.xhtml";
        }
    }

    public String editaP(int codigo_Proveedor) {
        Proveedor proveedor = ProveedorGestion.getProveedor(codigo_Proveedor);
        if (proveedor != null) {
            this.setCodigo_Proveedor(proveedor.getCodigo_Proveedor());
            this.setNombre_P(proveedor.getNombre_P());
            this.setProvincia_P(proveedor.getProvincia_P());
            this.setTelefono_P(proveedor.getTelefono_P());
            this.setCorreo_P(proveedor.getCorreo_P());

            return "ModificaProveedor.xhtml";
        } else {
            return "list.xhtml";
        }
    }

    public List<Proveedor> getProveedores() {
        return ProveedorGestion.getProveedores();
    }

    private boolean noImprimir = true; //para apagar el boton de imprimir...

    public boolean isNoImprimir() {
        return noImprimir;
    }

    public void setNoImprimir(boolean noImprimir) {
        this.noImprimir = noImprimir;
    }

    public void buscaProveedor(int codigo_Proveedor) {
        Proveedor proveedor = ProveedorGestion.getProveedor(codigo_Proveedor);
        if (proveedor != null) {
            this.setCodigo_Proveedor(proveedor.getCodigo_Proveedor());
            this.setNombre_P(proveedor.getNombre_P());
            this.setProvincia_P(proveedor.getProvincia_P());
            this.setTelefono_P(proveedor.getTelefono_P());
            this.setCorreo_P(proveedor.getCorreo_P());

            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setNombre_P("");

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Proveedor no encontrado...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("buscaProveedorForm:codigo", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }

    public void buscaModificar() {
        Proveedor proveedor = ProveedorGestion.getProveedor(this.getCodigo_Proveedor());
        if (proveedor != null) {
            this.setCodigo_Proveedor(proveedor.getCodigo_Proveedor());
            this.setNombre_P(proveedor.getNombre_P());
            this.setProvincia_P(proveedor.getProvincia_P());
            this.setTelefono_P(proveedor.getTelefono_P());
            this.setCorreo_P(proveedor.getCorreo_P());

            noImprimir = false; //para encender el boton de imprimir...
        } else {  //No lo encontró....
            this.setNombre_P("");

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Error", "Proveedor no encontrado...");
            FacesContext
                    .getCurrentInstance()
                    .addMessage("buscaProveedorForm:codigo", mensaje);//Cambiar por el form correcto
            noImprimir = true; //para apagar el boton de imprimir...
        }
    }

    public String navegaProveedor() {
        this.borrarDatos();
        return "/Proveedor/Proveedor.xhtml";
    }

    public String navegaEditaProveedor() {
        this.borrarDatos();
        return "/Proveedor/EditaProveedor.xhtml";
    }

    public void borrarDatos() {
        this.setCodigo_Proveedor(0);
        this.setCorreo_P("");
        this.setDireccion_P("");
        this.setDireccion_P("");
        this.setNombre_P("");
        this.setProvincia_P("");
        this.setTelefono_P(0);

    }

}
