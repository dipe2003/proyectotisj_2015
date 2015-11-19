
package interfaz.curso;


import Asignatura.Curso.Curso;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.Usuario;
import interfaz.Login;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped
public class ListarCursosBean implements Serializable{
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeEstudiante fEst;
    @Inject
    private Login login;
    
    private List<Curso> Cursos;
    private String Parametro;
    
    //  Constructores
    public ListarCursosBean(){}
    
    //  Getters
    public List<Curso> getCursos() {return Cursos;}
    public String getParametro(){return this.Parametro;}
    
    //  Setters
    public void setCursos(List<Curso> Cursos) {this.Cursos = Cursos;}
    public void setParametro(String Parametro){this.Parametro = Parametro;}
    
    /**
     * Inicializa las listas del bean.
     * Si el usuario logueado es docente solo se piden los cursos del ese docente.
     * Si el usuario logueado es estudiante trae todos los cursos en que esta inscripto el estudiante.
     * Si el usuario es administrador o administrativo trae todos los cursos registrados.
     */
    @PostConstruct
    public void Init(){
        this.Cursos = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Usuario usr = (Usuario) request.getSession().getAttribute("Usuario");
        
        try{
            this.Parametro = request.getParameter("parametro");
        }catch(NullPointerException ex){
            this.Parametro = "no";
        }
        if (this.Parametro!=null && !this.Parametro.isEmpty()) {
            // cargar las listas segun el parametro
        }else{
            switch(login.getRolSeleccionado()){
                case "Docente":
                    Cursos = fCurso.ListarCursosDocente(usr.getIdUsuario());
                    break;
                    
                case "Estudiante":
                    Cursos = fCurso.ListarCursosEstudiante(usr.getIdUsuario());
                    break;
                    
                default:
                    Cursos = fCurso.ListarCurso();
                    break;
            }
        }
    }
     
    public void filtro(String nameDocente, String nameAsignatura, int anioFilter, int semestreFilter){
        
    }
}
