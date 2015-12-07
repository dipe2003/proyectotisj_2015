package interfaz.curso;

import Asignatura.Curso.Clase.Clase;
import Asignatura.Curso.Clase.FacadeClase;
import Asignatura.Curso.Curso;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.FacadeEstudiante;
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
public class ListarClasesBean implements Serializable{
    @EJB
    private FacadeCurso fCurso;
    @EJB
    private FacadeEstudiante fEst;
    @EJB
    private FacadeClase fClase;
    @Inject
    private Login login;
    
    private List<Clase> Clases;
    private Map<Integer, String> InasistenciasClases;
    private String opt;
    private String NombreCurso;
    
    
    //  Constructores
    public ListarClasesBean(){}
    
    //  Getters
    public List<Clase> getClases(){return Clases;}
    public String getOpt() {return this.opt;}
    public String getNombreCurso(){return this.NombreCurso;}
    public int getTotalClases(){return this.Clases.size();}
    public Map<Integer, String> getInasistenciasClases() {return InasistenciasClases;}
    
    //  Setters
    public void setClases(List<Clase> Clases){this.Clases = Clases;}
    public void setOpt(String opt) {this.opt = opt;}
    public void setInasistenciasClases(Map<Integer, String> InasistenciasClases) {this.InasistenciasClases = InasistenciasClases;}
    
    
    /**
     * Recibe el parametro el id del curso (opt) y trae desde la base de datos los datos correspondientes: curso y las clases dicatadas.
     */
    @PostConstruct
    public void Init(){
        this.Clases = new ArrayList<>();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        
        try{
            this.opt = request.getParameter("opt");
        }catch(NullPointerException ex){
            this.opt = "no";
        }finally{
            if (this.opt == null) this.opt = "no";
        }
        if(!opt.equals("no")){
            Curso curso = fCurso.BuscarCurso(Integer.parseInt(opt));
            try{
                this.NombreCurso = curso.getAsignaturaCurso().getNombreAsignatura() + " " + curso.getAnioCurso();
                this.Clases = fClase.ListarClases(Integer.parseInt(opt));
                List<Estudiante> estudiantes = fEst.ListarEstudiantesCurso(curso.getIdCurso());
                InasistenciasClases = new HashMap<>();
                for (int i = 0; i < Clases.size(); i++) {
                    Clase clase = Clases.get(i);
                    String Inasistencias="";
                    for (int j = 0; j < estudiantes.size(); j++) {
                        Estudiante est = estudiantes.get(j);
                        if(clase.getAsistenciaEstudiante(est.getIdUsuario())!=1){
                            Inasistencias += est.getNombreCompleto();
                            if(j+1<estudiantes.size()) {
                                Inasistencias +=", ";
                            }else{
                                Inasistencias += ". ";
                            }
                        }
                    }
                    if(!Inasistencias.isEmpty()) InasistenciasClases.put(clase.getIdClase(), Inasistencias);
                }
            }catch(NullPointerException ex){}
        }
    }
    
    
}
