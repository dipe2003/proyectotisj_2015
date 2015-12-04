
package interfaz.curso;

import Docente.*;
import Estudiante.*;
import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.Curso.Curso;
import Asignatura.Curso.FacadeCurso;
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
public class InformacionCurso implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    @EJB
    private FacadeDocente fDoc;
    
    @EJB
    private FacadeCurso fCur;
    
    private Curso curso;
    
    private Docente docente;
    
    private List<Estudiante> Estudiantes;
    
    public Curso getCurso() {return curso;}
    public Docente getDocente() {return docente;}
    public List<Estudiante> getEstudiantes() {return Estudiantes;}

    public void setEstudiantes(List<Estudiante> Estudiantes) {this.Estudiantes = Estudiantes;}
    public void setCurso(Curso curso) {this.curso = curso;}
    public void setDocente(Docente docente) {this.docente = docente;}
    
//  Constructores
    public InformacionCurso(){}
    
    
    @PostConstruct
    public void Init(){
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
            int idCurso = Integer.valueOf(request.getParameter("id"));
            Estudiantes = fEst.ListarEstudiantesCurso(idCurso);
            docente = fDoc.getDocenteCurso(idCurso);
            curso = fCur.BuscarCurso(idCurso);
    }
    
    
}
