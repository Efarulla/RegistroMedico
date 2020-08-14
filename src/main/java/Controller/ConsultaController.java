
package Controller;

import Gestion.ConsultaGestion;
import Gestion.Reporte;
import Model.Atleta;
import Model.Consulta;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Eduardo
 */
@Named(value = "consultaController")
@SessionScoped
public class ConsultaController extends Consulta implements Serializable {

    /**
     * Creates a new instance of ConsultaController
     */
    public ConsultaController() {
    }
    
    public String insertar(){
        
    if(ConsultaGestion.insertar(this)){
           

        
        return "principal.xhtml";
    
    
    }else{
    return"Consulta.xhtml";
    }
    
    
    
    
    }
    
    public String dev(int n){
    
    this.setCodigoAtleta(n);
    return "Consulta.xhtml";
    
    
    }
    
    
     public void imprimirReceta() {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("Parameter1",1);
        String realPath="/reportes/Receta.jasper";
        String nombreArchivoSalida="attachement; filename=reporte.pdf";
        Reporte.descargarPdf(realPath, parametros, nombreArchivoSalida);
        
        
    }
    
    
}
