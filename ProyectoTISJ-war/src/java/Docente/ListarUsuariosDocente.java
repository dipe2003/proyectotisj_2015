
package Docente;

import Estudiante.*;
import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.FacadeAsignatura;
import Usuario.Docente.Docente;
import Usuario.Docente.FacadeDocente;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarUsuariosDocente implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    private List<Estudiante> Estudiantes;

    public List<Estudiante> getEstudiantes() {return Estudiantes;}

    public void setEstudiantes(List<Estudiante> Estudiantes) {this.Estudiantes = Estudiantes;}
    
    //  Constructores
    public ListarUsuariosDocente(){}
    
    
    @PostConstruct
    public void Init(){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            Usuario usr = (Usuario) request.getSession().getAttribute("Usuario");
            Estudiantes = fEst.ListarAlumnosDocente(usr.getIdUsuario());
    }
    
    
}
