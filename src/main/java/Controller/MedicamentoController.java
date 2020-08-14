
package Controller;
import Gestion.MedicamentoGestion;
import Model.Medicamento;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;


@Named(value = "medicamentoController")
@SessionScoped
public class MedicamentoController extends Medicamento implements Serializable {

    
    public MedicamentoController() {
    }
    public List<Medicamento> getMedicamentos(){
        return MedicamentoGestion.getMedicamentos();
        
    }
}
