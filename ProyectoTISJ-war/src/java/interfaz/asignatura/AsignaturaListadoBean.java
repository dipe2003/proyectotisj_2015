
package interfaz.asignatura;

import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AsignaturaListadoBean implements Serializable{
    
    @EJB
    private FacadeAsignatura fAsig;
    
    private List<Asignatura> Asignaturas;
    private Asignatura AsignaturaSeleccionada;

    public List<Asignatura> getAsignaturas() {return Asignaturas;}

    public void setAsignaturas(List<Asignatura> Asignaturas) {this.Asignaturas = Asignaturas;}

    public Asignatura getAsignaturaSeleccionada() {return AsignaturaSeleccionada;}

    public void setAsignaturaSeleccionada(Asignatura AsignaturaSeleccionada) {this.AsignaturaSeleccionada = AsignaturaSeleccionada;}
    
    @PostConstruct
    public void Init(){
        this.Asignaturas = fAsig.ListarAsignaturas();
    }
    
    
}
