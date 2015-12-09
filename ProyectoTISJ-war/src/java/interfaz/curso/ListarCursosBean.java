
package interfaz.curso;


import Asignatura.Asignatura;
import Asignatura.Curso.Curso;
import Asignatura.Curso.FacadeCurso;
import Asignatura.FacadeAsignatura;
import Usuario.Estudiante.FacadeEstudiante;
import Usuario.Usuario;
import interfaz.Login;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private FacadeAsignatura fAsig;
    @EJB
    private FacadeEstudiante fEst;
    @Inject
    private Login login;
    
    private List<Curso> Cursos;
    private String Parametro;
    
    private List<String> AniosCursos;
    
    private Map<String, Integer> AsignaturasCursos;
    
    //  Constructores
    public ListarCursosBean(){}
    
    //  Getters
    public List<Curso> getCursos() {return Cursos;}
    public String getParametro(){return this.Parametro;}
    public List<String> getAniosCursos() {return AniosCursos;}
    public Map<String, Integer> getAsignaturasCursos() {return AsignaturasCursos;}
    
    //  Setters
    public void setCursos(List<Curso> Cursos) {this.Cursos = Cursos;}
    public void setParametro(String Parametro){this.Parametro = Parametro;}
    public void setAniosCursos(List<String> AniosCursos) {this.AniosCursos = AniosCursos;}
    public void setAsignaturasCursos(Map<String, Integer> AsignaturasCursos) {this.AsignaturasCursos = AsignaturasCursos;}
    
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
        this.AniosCursos = new ArrayList<>();
        this.AniosCursos = fCurso.getAniosCursos();
        this.AniosCursos.add("Todos");
        
        AsignaturasCursos = new HashMap<>();
        AsignaturasCursos.put("Todos",0);
        List<Asignatura> ListAsignaturaCurso =  fAsig.ListarAsignaturasCurso();
        for (int i = 0; i < ListAsignaturaCurso.size(); i++) {
            AsignaturasCursos.put(ListAsignaturaCurso.get(i).getNombreAsignatura(),ListAsignaturaCurso.get(i).getIdAsignatura());
        }
    }
    
    public void filtro(String nameDocente, String nameAsignatura, String anioFilter, int semestreFilter, int idAsignatura){
        int anio = 0;
        if (!anioFilter.equalsIgnoreCase("Todos")){
            anio = Integer.valueOf(anioFilter);
        }
        Cursos = fCurso.filtrarCursos(nameDocente, nameAsignatura, anio, semestreFilter, idAsignatura);
    }
}
