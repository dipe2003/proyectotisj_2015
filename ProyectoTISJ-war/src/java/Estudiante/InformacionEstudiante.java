
package Estudiante;

import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
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
public class InformacionEstudiante implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    @EJB
    private FacadeCurso fCur;
    
    private Estudiante estudiante;
    
    private List<Curso> cursos;
    
    private List<Resultado> Resultados;
    
    public List<Curso> getCursos() {return cursos;}
    public Estudiante getEstudiante() {return estudiante;}
    
    public void setEstudiante(Estudiante estudiante) {this.estudiante = estudiante;}
    public void setCursos(List<Curso> cursos) {this.cursos = cursos;}
    
    //  Constructores
    public InformacionEstudiante(){}
    
    
    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        int idEstudiante = Integer.valueOf(request.getParameter("id"));
        estudiante = fEst.BuscarEstudiante(idEstudiante);
        cursos = fCur.ListarCursosEstudiante(idEstudiante);
    }
    
    
}
