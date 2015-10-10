
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
    
    private List<beanCurso> Cursos;
    private String Parametro;
    
    //  Constructores
    public ListarCursosBean(){}
    
    //  Getters
    public List<beanCurso> getCursos() {return Cursos;}
    public String getParametro(){return this.Parametro;}
    
    //  Setters
    public void setCursos(List<beanCurso> Cursos) {this.Cursos = Cursos;}
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
            Parametro = request.getParameter("parametro");
        }catch(NullPointerException ex){
            Parametro = "";
        }
        if (Parametro!=null && !Parametro.isEmpty()) {
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
            this.Cursos.add(getBeanCurso(cursos.get(i)));
        }
    }
    
    /**
     * Devuelve un beanCurso con los datos del curso especificado.
     */
    private beanCurso getBeanCurso(Curso curso){
        beanCurso bcurso = new beanCurso(curso.getIdCurso(), curso.getAsignaturaCurso().getNombreAsignatura(),
                curso.getDocenteCurso().getNombreCompleto(),curso.getAnioCurso(), curso.getSemestreCurso(),
                getListaEstudiante(curso.getIdCurso()));
        return bcurso;
    }
    
    /**
     * Devuelve una lista de beanEstudiante que pertenecen al curso.
     * @param idCurso
     * @return
     */
    private List<beanEstudiante> getListaEstudiante(int idCurso){
        List<beanEstudiante> lista = new ArrayList<>();
        for (int i = 0; i < fEst.ListarEstudiantesCurso(idCurso).size(); i++) {
            lista.add(new beanEstudiante(fEst.ListarEstudiantesCurso(idCurso).get(i).getIdUsuario(), fEst.ListarEstudiantesCurso(idCurso).get(i).getNombreCompleto()));
        }
        return lista;
    }
    
    /**
     * Clase para mostrar la informacion de cada curso
     */
    public static class beanCurso{
        private int id;
        private String NombreAsignatura;
        private String NombreDocente;
        private int AnioCurso;
        private int SemestreCurso;
        private List<beanEstudiante> Estudiantes;
        
        //Constructor
        
        public beanCurso(int idCurso, String NombreAsignatura, String NombreDocente, int AnioCurso, int CreditosAsignatura, List<beanEstudiante> Estudiantes) {
            this.id = idCurso;
            this.NombreAsignatura = NombreAsignatura;
            this.NombreDocente = NombreDocente;
            this.AnioCurso = AnioCurso;
            this.SemestreCurso = CreditosAsignatura;
            this.Estudiantes = Estudiantes;
        }
        
        public beanCurso() {}
        
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
        public List<beanEstudiante> getEstudiantes() {return Estudiantes;}
        public void setEstudiantes(List<beanEstudiante> Estudiantes) {this.Estudiantes = Estudiantes;}
        
    }
    
    public  static class beanEstudiante{
        private int IdEstudiante;
        private String NombreCompletoEstudiante;
        
        //  Constructores
        
        public beanEstudiante() {}
        
        public beanEstudiante(int IdEstudiante, String NombreCompletoEstudiante) {
            this.IdEstudiante = IdEstudiante;
            this.NombreCompletoEstudiante = NombreCompletoEstudiante;
        }
        
        // Setters & Getters
        
        public int getIdEstudiante() {return IdEstudiante;}
        public void setIdEstudiante(int IdEstudiante) {this.IdEstudiante = IdEstudiante;}
        public String getNombreCompletoEstudiante() {return NombreCompletoEstudiante;}
        public void setNombreCompletoEstudiante(String NombreCompletoEstudiante) {this.NombreCompletoEstudiante = NombreCompletoEstudiante;}
        
    }
    
}
