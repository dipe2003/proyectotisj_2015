
package interfaz.curso;


import Curso.Curso;
import Curso.FacadeCurso;
import Docente.Docente;
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
public class ListarCursosBean implements Serializable{    
    @EJB
    private FacadeCurso fCurso;
    
    private List<beanCurso> Cursos;

    public ListarCursosBean(){}

    public List<beanCurso> getCursos() {return Cursos;}
    public void setCursos(List<beanCurso> Cursos) {this.Cursos = Cursos;}

    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Docente  Doc = (Docente)request.getSession().getAttribute("Usuario");
        List<Curso> cursos = fCurso.ListarCurso(Doc.getIdUsuario());
        this.Cursos = new ArrayList<>();
        for (int i = 0; i < cursos.size(); i++) {
            this.Cursos.add(getBeanCurso(cursos.get(i)));
        }
    }
    
    /**
     * Devuelve un beanCurso con los datos del curso especificado.
     */
    private beanCurso getBeanCurso(Curso curso){
        beanCurso bcurso = new beanCurso(curso.getIdCurso(), curso.getAsignaturaCurso().getNombreAsignatura(), curso.getDocenteCurso().getNombreCompleto());
        return bcurso;
    }
    
    /**
     * Clase para mostrar la informacion de cada curso
     */
    public static class beanCurso{
        private int id;
        private String NombreAsignatura;
        private String NombreDocente;
        
        //Constructor

        public beanCurso(int idCurso, String NombreAsignatura, String NombreDocente) {
            this.id = idCurso;
            this.NombreAsignatura = NombreAsignatura;
            this.NombreDocente = NombreDocente;
        }

        public beanCurso() {
        }
        
        // Setters & Getters

        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getNombreAsignatura() {return NombreAsignatura;}
        public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
        public String getNombreDocente() {return NombreDocente;}
        public void setNombreDocente(String NombreDocente) {this.NombreDocente = NombreDocente;}
        
    }

    
}
