
package interfaz.Docente;

import Docente.FacadeDocente;
import Docente.Docente;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class DocenteListadoBean implements Serializable{
    
    @EJB
    private FacadeDocente fDoc;
    
    private List<Docente> Docentes;
    private Docente DocenteSeleccionado;

    public List<Docente> getDocentes() {return this.Docentes;}

    public void setDocentes(List<Docente> Docentes) {this.Docentes = Docentes;}

    public Docente getDocenteSeleccionado() {return this.DocenteSeleccionado;}

    public void setDocenteSeleccionado(Docente DocenteSeleccionado) {this.DocenteSeleccionado = DocenteSeleccionado;}
    
    @PostConstruct
    public void Init(){
        this.Docentes = fDoc.ListarDocentes();
    }
    
    
}
