
package interfaz.curso;

import Docente.*;
import Estudiante.*;
import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.Curso.Clase.Clase;
import Asignatura.Curso.Clase.FacadeClase;
import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Evaluacion;
import Asignatura.Curso.Evaluacion.Examen.Examen;
import Asignatura.Curso.Evaluacion.FacadeEvaluacion;
import Asignatura.Curso.Evaluacion.Laboratorio.Laboratorio;
import Asignatura.Curso.Evaluacion.Parcial.Parcial;
import Asignatura.Curso.Evaluacion.Resultado.FacadeResultado;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
import Asignatura.Curso.FacadeCurso;
import Asignatura.FacadeAsignatura;
import Usuario.Docente.Docente;
import Usuario.Docente.FacadeDocente;
import Usuario.Estudiante.Estudiante;
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
public class InformacionCurso implements Serializable{
    
    @EJB
    private FacadeEstudiante fEst;
    
    @EJB
    private FacadeDocente fDoc;
    
    @EJB
    private FacadeCurso fCur;
    
    @EJB
    private FacadeClase fClase;
    
    @EJB
    private FacadeEvaluacion fEva;
    
    @EJB
    private FacadeResultado fRes;
    
    private Curso curso;
    
    private Docente docente;
    
    private List<Estudiante> Estudiantes;
    
    private List<String> titulosEvaluaciones;
    
    private Map<Integer, List<Integer>> ResultadosEsstudiante;
    
    @Inject
    private Login login;
    
    private List<Clase> Clases;
    private Map<Integer, String> InasistenciasClases;
    private String opt;
    private String NombreCurso;
    
    public Curso getCurso() {return curso;}
    public Docente getDocente() {return docente;}
    public List<Estudiante> getEstudiantes() {return Estudiantes;}
    public List<Clase> getClases(){return Clases;}
    public String getOpt() {return this.opt;}
    public String getNombreCurso(){return this.NombreCurso;}
    public int getTotalClases(){return this.Clases.size();}
    public Map<Integer, String> getInasistenciasClases() {return InasistenciasClases;}
    public List<String> getTitulosEvaluaciones() {return titulosEvaluaciones;}
    public Map<Integer, List<Integer>> getResultadosEsstudiante() {return ResultadosEsstudiante;}

    public void setEstudiantes(List<Estudiante> Estudiantes) {this.Estudiantes = Estudiantes;}
    public void setCurso(Curso curso) {this.curso = curso;}
    public void setDocente(Docente docente) {this.docente = docente;}
    public void setClases(List<Clase> Clases){this.Clases = Clases;}
    public void setOpt(String opt) {this.opt = opt;}
    public void setInasistenciasClases(Map<Integer, String> InasistenciasClases) {this.InasistenciasClases = InasistenciasClases;}
    public void setTitulosEvaluaciones(List<String> titulosEvaluaciones) {this.titulosEvaluaciones = titulosEvaluaciones;}
    public void setResultadosEsstudiante(Map<Integer, List<Integer>> ResultadosEsstudiante) {this.ResultadosEsstudiante = ResultadosEsstudiante;}
    
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
        this.Clases = new ArrayList<>();
        try{
            this.opt = request.getParameter("id");
        }catch(NullPointerException ex){
            this.opt = "no";
        }finally{
            if (this.opt == null) this.opt = "no";
        }
        if(!opt.equals("no")){
            Curso curso = fCur.BuscarCurso(Integer.parseInt(opt));
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
        int p=1;
        int e=1;
        int l=1;
        titulosEvaluaciones = new ArrayList<>();
        for (Evaluacion item : fEva.getEvaluacionesPorCurso(curso.getIdCurso())){
            if (item instanceof Parcial) {
                titulosEvaluaciones.add("Parcial"+p);
                p++;
            }else if (item instanceof Examen) {
                titulosEvaluaciones.add("Examen"+e);
                p++;
            }else if (item instanceof Laboratorio) {
                titulosEvaluaciones.add("Laboratorio"+l);
                p++;
            }
        }
        
        ResultadosEsstudiante =  new HashMap<>();
        for (Estudiante item : Estudiantes){
            List<Integer> notas = new ArrayList<>();
            List<Resultado> resultados = fRes.ListarResultadosEstudiantePorCurso(item.getIdUsuario(), idCurso);
            for (Resultado itemR: resultados){
                notas.add(itemR.getResultadoEvaluacion());
            }
            ResultadosEsstudiante.put(item.getIdUsuario(), notas);
        }
    }
    
}
