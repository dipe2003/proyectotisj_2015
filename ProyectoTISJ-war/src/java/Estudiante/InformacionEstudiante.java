
package Estudiante;

import Asignatura.Curso.Curso;
import Asignatura.Curso.Evaluacion.Evaluacion;
import Asignatura.Curso.Evaluacion.Examen.Examen;
import Asignatura.Curso.Evaluacion.Laboratorio.Laboratorio;
import Asignatura.Curso.Evaluacion.Parcial.Parcial;
import Asignatura.Curso.Evaluacion.Resultado.FacadeResultado;
import Asignatura.Curso.Evaluacion.Resultado.Resultado;
import Asignatura.Curso.FacadeCurso;
import Usuario.Estudiante.Estudiante;
import Usuario.Estudiante.Estudios.Estudio;
import Usuario.Estudiante.FacadeEstudiante;
import interfaz.Login;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
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
    
    @Inject
    private Login login;
    
    private Estudiante estudiante;
    
    private List<Curso> cursos;
    
    private List<ResultadoCursos> ResultadosCursos;
    
    public List<ResultadoCursos> getResultadosCursos() {return ResultadosCursos;}
    public List<Curso> getCursos() {return cursos;}
    public Estudiante getEstudiante() {return estudiante;}
    public String getEstudiosCursados(){
        String EstudiosCursados = "";
        List<Estudio> estudios = estudiante.getEstudiosCursadosEstudiante();
        for(int i = 0; i < estudios.size(); i++){
            EstudiosCursados += estudios.get(i).getTipoDeEstudio().getTipoDeEstudio() + ": " + estudios.get(i).getOrientacionEstudio();
            if(i+1<estudios.size()) {
                EstudiosCursados += ", ";
            }else{
                EstudiosCursados += ".";
            }
        }
        return EstudiosCursados;
    }
    
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
        if ((idEstudiante != login.getIdUsuarioLogueado())&&(login.getRolSeleccionado().equalsIgnoreCase("Estudiante"))){
            try {
                context.getExternalContext().redirect("./../Error/Error401.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(InformacionEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
                ResultadosCursos.add(new ResultadoCursos(idCurso,NombreAsignatura,SemestreCurso, AnioCurso, inasistencias));
                AssignarResultado(ResultadosCursos, idCurso, item);
            }else{
                AssignarResultado(ResultadosCursos, idCurso, item);
            }
        }
        for (Curso item : cursos){
            if (getResultadoCursosByCurso(item.getIdCurso(),ResultadosCursos)==null){
                int id = item.getIdCurso();
                String nombre = item.getAsignaturaCurso().getNombreAsignatura();
                int semestre = item.getSemestreCurso();
                int anio = item.getAnioCurso();
                int insasistencias =  fCur.GetInanistenciasEstudianteCurso(idEstudiante, id);
                ResultadosCursos.add(new ResultadoCursos(id,nombre,semestre, anio, insasistencias));
            }
        }
    }
    
    private void AssignarResultado(List<ResultadoCursos> lista, int idCurso, Resultado result){
        Evaluacion eval = result.getEvaluacionResultado();
        for(ResultadoCursos item : lista){
            if (item.getIdCurso() == idCurso){
                if (eval instanceof Parcial) {
                    item.resultadoParcial += result.getResultadoEvaluacion();
                }else if (eval instanceof Examen) {
                    item.resultadoExamen += result.getResultadoEvaluacion();
                }else if (eval instanceof Laboratorio) {
                    if (result.getResultadoEvaluacion() == 101){
                        item.resultadoLaboratorio += 50;
                    }else if(result.getResultadoEvaluacion() == 102){
                        item.resultadoLaboratorio += 10;
                    }else if(result.getResultadoEvaluacion() == 103){
                        item.resultadoLaboratorio += 5;
                    }
                    
                }
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
        int resultadoParcial;
        int resultadoExamen;
        int resultadoLaboratorio;
        String Curso;
        int Semestre;
        int Anio;
        int Inasistencias;

        public int getResultadoParcial() {return resultadoParcial;}
        public void setResultadoParcial(int resultadoParcial) {this.resultadoParcial = resultadoParcial;}
        public int getResultadoExamen() {return resultadoExamen;}
        public void setResultadoExamen(int resultadoExamen) {this.resultadoExamen = resultadoExamen;}
        public int getResultadoLaboratorio() {return resultadoLaboratorio;}
        public void setResultadoLaboratorio(int resultadoLaboratorio) {this.resultadoLaboratorio = resultadoLaboratorio;}
        
        public int getInasistencias() {return Inasistencias;}
        public int getIdCurso() {return idCurso;}
        public String getCurso() {return Curso;}
        public int getSemestre() {return Semestre;}
        public int getAnio() {return Anio;}
        
        public void setAnio(int Anio) {this.Anio = Anio;}
        public void setIdCurso(int idCurso) {this.idCurso = idCurso;}
        public void setSemestre(int Semestre) {this.Semestre = Semestre;}
        public void setCurso(String Curso) {this.Curso = Curso;}
        public void setInasistencias(int Inasistencias) {this.Inasistencias = Inasistencias;}

        public ResultadoCursos(int idCurso, String Curso, int Semestre, int Anio, int Inasistencias) {
            this.idCurso = idCurso;
            this.Curso = Curso;
            this.Semestre = Semestre;
            this.Anio = Anio;
            this.Inasistencias = Inasistencias;
            this.resultadoExamen = 0;
            this.resultadoParcial = 0;
            this.resultadoLaboratorio = 0;
        }

     
        
    }
    
}
