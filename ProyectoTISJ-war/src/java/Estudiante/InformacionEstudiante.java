
package Estudiante;

import interfaz.asignatura.*;
import Asignatura.Asignatura;
import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Resultado.FacadeResultado;
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
import java.util.Map;
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
    
    @EJB
    private FacadeResultado fRes;
    
    private Estudiante estudiante;
    
    private List<Curso> cursos;
    
    private List<ResultadoCursos> ResultadosCursos;
    
    public List<ResultadoCursos> getResultadosCursos() {return ResultadosCursos;}
    public List<Curso> getCursos() {return cursos;}
    public Estudiante getEstudiante() {return estudiante;}
    
    public void setEstudiante(Estudiante estudiante) {this.estudiante = estudiante;}
    public void setCursos(List<Curso> cursos) {this.cursos = cursos;}
    public void setResultadosCursos(List<ResultadoCursos> ResultadosCursos) {this.ResultadosCursos = ResultadosCursos;}
    
    //  Constructores
    public InformacionEstudiante(){}
    
    
    @PostConstruct
    public void Init(){
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        int idEstudiante = Integer.valueOf(request.getParameter("id"));
        estudiante = fEst.BuscarEstudiante(idEstudiante);
        cursos = fCur.ListarCursosEstudiante(idEstudiante);
        List<Resultado> Resultados = fRes.ListarResultadosEstudiante(idEstudiante);
        ResultadosCursos = new ArrayList<>();
        for (Resultado item : Resultados){
            int idCurso = item.getEvaluacionResultado().getCursoEvaluacion().getIdCurso();
            int evaluacion = item.getResultadoEvaluacion();
            String NombreAsignatura = item.getEvaluacionResultado().getCursoEvaluacion().getAsignaturaCurso().getNombreAsignatura();
            int SemestreCurso = item.getEvaluacionResultado().getCursoEvaluacion().getSemestreCurso();
            int AnioCurso = item.getEvaluacionResultado().getCursoEvaluacion().getAnioCurso();
            ResultadoCursos RCRegistrado = getResultadoCursosByCurso(idCurso,ResultadosCursos);
            int inasistencias = fCur.GetInanistenciasEstudianteCurso(idEstudiante, idCurso);
            if (RCRegistrado == null){
                ResultadosCursos.add(new ResultadoCursos(idCurso,evaluacion ,NombreAsignatura,SemestreCurso, AnioCurso, inasistencias));
            }else{
                RCRegistrado.resultado += evaluacion;
            }
        }
    }
    
    private List<ResultadoCursos> getResultadoCursosBySemestre(int Semestre){
        List <ResultadoCursos> result = new ArrayList<>();
        for (ResultadoCursos item : ResultadosCursos){
            if (item.Semestre == Semestre) result.add(item);
        }
        return result;
    }
    
    private ResultadoCursos getResultadoCursosByCurso(int idCurso, List<ResultadoCursos> cursos){
        for (ResultadoCursos item : ResultadosCursos){
            if (item.idCurso == idCurso) return item;
        }
        return null;
    }
    
    public class ResultadoCursos{
        int idCurso;
        int resultado;
        String Curso;
        int Semestre;
        int Anio;
        int Inasistencias;

        public int getInasistencias() {return Inasistencias;}
        public int getIdCurso() {return idCurso;}
        public int getResultado() {return resultado;}
        public String getCurso() {return Curso;}
        public int getSemestre() {return Semestre;}
        public int getAnio() {return Anio;}
        
        public void setAnio(int Anio) {this.Anio = Anio;}
        public void setIdCurso(int idCurso) {this.idCurso = idCurso;}
        public void setSemestre(int Semestre) {this.Semestre = Semestre;}
        public void setCurso(String Curso) {this.Curso = Curso;}
        public void setResultado(int resultado) {this.resultado = resultado;}
        public void setInasistencias(int Inasistencias) {this.Inasistencias = Inasistencias;}

        public ResultadoCursos(int idCurso, int resultado, String Curso, int Semestre, int Anio, int Inasistencias) {
            this.idCurso = idCurso;
            this.resultado = resultado;
            this.Curso = Curso;
            this.Semestre = Semestre;
            this.Anio = Anio;
            this.Inasistencias = Inasistencias;
        }
        
    }
    
}
