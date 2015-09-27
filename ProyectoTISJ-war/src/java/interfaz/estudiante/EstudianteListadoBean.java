
package interfaz.estudiante;


import Estudiante.FacadeEstudiante;
import Estudiante.Estudiante;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class EstudianteListadoBean implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    private List<Estudiante> Estudiantes;
    private Estudiante EstudianteSeleccionado;

    public List<Estudiante> getEstudiantes() {return this.Estudiantes;}

    public void setEstudiantes(List<Estudiante> Estudiantes) {this.Estudiantes = Estudiantes;}

    public Estudiante getEstudianteSeleccionado() {return this.EstudianteSeleccionado;}

    public void setEstudianteSeleccionado(Estudiante EstudianteSeleccionado) {this.EstudianteSeleccionado = EstudianteSeleccionado;}
    
    @PostConstruct
    public void Init(){
        this.Estudiantes = fEst.ListarEstudiates();
    }
    
    
}
