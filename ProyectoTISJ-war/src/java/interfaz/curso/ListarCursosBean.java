
package interfaz.curso;


import Curso.Curso;
import Curso.FacadeCurso;
import Estudiante.FacadeEstudiante;
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
    
    private List<InnerCurso> Cursos;
    private String Parametro;
    
    //  Constructores
    public ListarCursosBean(){}
    
    //  Getters
    public List<InnerCurso> getCursos() {return Cursos;}
    public String getParametro(){return this.Parametro;}
    
    //  Setters
    public void setCursos(List<InnerCurso> Cursos) {this.Cursos = Cursos;}
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
        List<Curso> cursos = new ArrayList<>();
        
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
                    cursos = fCurso.ListarCursosDocente(usr.getIdUsuario());
                    break;
                    
                case "Estudiante":
                    cursos = fCurso.ListarCursosEstudiante(usr.getIdUsuario());
                    break;
                    
                default:
                    cursos = fCurso.ListarCurso();
                    break;
            }
        }
        for (int i = 0; i < cursos.size(); i++) {
            this.Cursos.add(getInnerCurso(cursos.get(i)));
        }
    }
    
    /**
     * Devuelve un beanCurso con los datos del curso especificado.
     */
    private InnerCurso getInnerCurso(Curso curso){
        InnerCurso bcurso = new InnerCurso(curso.getIdCurso(), curso.getAsignaturaCurso().getNombreAsignatura(),
                curso.getDocenteCurso().getNombreCompleto(),curso.getAnioCurso(), curso.getSemestreCurso());
        return bcurso;
    }
    
    
    /**
     * Clase para mostrar la informacion de cada curso
     */
    public static class InnerCurso{
        private int id;
        private String NombreAsignatura;
        private String NombreDocente;
        private int AnioCurso;
        private int SemestreCurso;
        
        //Constructor
        
        public InnerCurso(int idCurso, String NombreAsignatura, String NombreDocente, int AnioCurso, int CreditosAsignatura) {
            this.id = idCurso;
            this.NombreAsignatura = NombreAsignatura;
            this.NombreDocente = NombreDocente;
            this.AnioCurso = AnioCurso;
            this.SemestreCurso = CreditosAsignatura;
        }
        
        public InnerCurso() {}
        
        // Setters & Getters
        
        public int getId() {return id;}
        public void setId(int id) {this.id = id;}
        public String getNombreAsignatura() {return NombreAsignatura;}
        public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}
        public String getNombreDocente() {return NombreDocente;}
        public void setNombreDocente(String NombreDocente) {this.NombreDocente = NombreDocente;}
        public int getAnioCurso() {return AnioCurso;}
        public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
        public int getSemestreCurso() {return SemestreCurso;}
        public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
        
    }
    

}
