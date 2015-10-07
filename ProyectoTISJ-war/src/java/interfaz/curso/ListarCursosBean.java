
package interfaz.curso;


import Curso.Curso;
import Curso.FacadeCurso;
import Docente.Docente;
import java.io.Serializable;
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
    
    private List<Curso> Cursos;

    public ListarCursosBean(){}

    public List<Curso> getCursos() {return Cursos;}
    public void setCursos(List<Curso> Cursos) {this.Cursos = Cursos;}

    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Docente  Doc = (Docente)request.getSession().getAttribute("Usuario");
        this.Cursos = fCurso.ListarCurso(Doc.getIdUsuario());
    }

    
}
