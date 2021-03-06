
package Asignatura.Curso;

import Asignatura.Asignatura;
import Asignatura.Curso.Clase.Clase;
import Usuario.Docente.Docente;
import Asignatura.Curso.Encuesta.Encuesta;
import Usuario.Estudiante.Estudiante;
import Asignatura.Curso.Evaluacion.Evaluacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Curso implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdCurso;
    private int SemestreCurso;
    private int AnioCurso;
    private String ContratoDocenteCurso;
    
    @OneToOne
    private Docente DocenteCurso;
    
    @OneToOne
    private Asignatura AsignaturaCurso;
    
    @ManyToMany(mappedBy="CursosEstudiante")
    private List<Estudiante> EstudiantesCurso;
    
    @OneToMany(mappedBy = "CursoClase", cascade = CascadeType.REMOVE)
    private List<Clase> ClasesCurso;
    
    @OneToMany(mappedBy = "CursoEvaluacion")
    private List<Evaluacion> EvaluacionesCurso;
    
    @OneToOne
    @JoinColumn(name="IdEncuesta")
    private Encuesta EncuestaCurso;
    
    //  Constructores    
    public Curso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso, String ContratoDocenteCurso) {
        this.SemestreCurso = SemestreCurso;
        this.AnioCurso = AnioCurso;
        this.DocenteCurso = DocenteCurso;
        this.AsignaturaCurso = AsignaturaCurso;
        this.EstudiantesCurso = new ArrayList<>();
        this.ClasesCurso = new ArrayList<>();
        this.EvaluacionesCurso = new ArrayList<>();
        this.ContratoDocenteCurso = ContratoDocenteCurso;
    }
    
    public Curso() {}
    
    //  Getters
    public int getIdCurso() {return IdCurso;}    
    public int getSemestreCurso() {return SemestreCurso;}    
    public int getAnioCurso() {return AnioCurso;}    
    public Docente getDocenteCurso() {return DocenteCurso;}
    public Asignatura getAsignaturaCurso() {return AsignaturaCurso;}    
    public String getContratoDocenteCurso() {return ContratoDocenteCurso;}   
    public List<Estudiante> getEstudiantesCurso(){return this.EstudiantesCurso;}
    public List<Clase> getClasesCurso() {return ClasesCurso;}
    public List<Evaluacion> getEvaluacionesCurso() {return EvaluacionesCurso;}
    public Encuesta getEncuestaCurso() {return EncuestaCurso;}
    
    //  Setters
    public void setIdCurso(int IdCurso) {this.IdCurso = IdCurso;}
    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setDocenteCurso(Docente DocenteCurso) {
        this.DocenteCurso = DocenteCurso;
        if(!DocenteCurso.getCursosDocente().contains(this)){
            DocenteCurso.addCursoDocente(this);
        }
    }
    public void setAsignaturaCurso(Asignatura AsignaturaCurso) {
        this.AsignaturaCurso = AsignaturaCurso;
        if(!AsignaturaCurso.getCursosAsignatura().contains(this)){
            AsignaturaCurso.addCursoAsignatura(this);
        }
    }
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setEstudiantesCurso(List<Estudiante> EstudiantesCurso){this.EstudiantesCurso = EstudiantesCurso;}
    public void setClasesCurso(List<Clase> ClasesCurso) {this.ClasesCurso = ClasesCurso;}
    public void setEvaluacionesCurso(List<Evaluacion> EvaluacionesCurso) {this.EvaluacionesCurso = EvaluacionesCurso;}
    public void setEncuestaCurso(Encuesta EncuestaCurso) {
        if(EncuestaCurso == null && this.EncuestaCurso != null){
            this.EncuestaCurso.setCursoEncuesta(null);
        }
        this.EncuestaCurso = EncuestaCurso;
        if(EncuestaCurso != null && !EncuestaCurso.getCursoEncuesta().equals(this)){
            EncuestaCurso.setCursoEncuesta(this);
        }
    }
    
    //  Estudiantes    
    public void addEstudianteCurso(Estudiante EstudianteCurso){
        this.EstudiantesCurso.add(EstudianteCurso);
        if (!EstudianteCurso.getCursosEstudiante().contains(this)) {
            EstudianteCurso.getCursosEstudiante().add(this);
        }
    }
    public void removeEstudianteCurso(Estudiante EstudianteCurso){this.EstudiantesCurso.remove(EstudianteCurso);}
    public void removeEstudianteCurso(int IdEstudianteCurso){
        for (int i = 0; i < this.EstudiantesCurso.size(); i++) {
            if(this.EstudiantesCurso.get(i).getIdUsuario()==IdEstudianteCurso) this.EstudiantesCurso.remove(i);
        }
    }
    
    //  Asistencias
    public void addClaseCurso(Clase ClaseCurso){
        this.ClasesCurso.add(ClaseCurso);
        if (ClaseCurso.getCursoClase()==null || !ClaseCurso.getCursoClase().equals(this)) {
            ClaseCurso.setCursoClase(this);
        }
    }
    
    /**
     * Devuelve el total de clases dictadas de este curso.
     * @return 
     */
    public int getTotalClases(){
        return this.ClasesCurso.size();
    }
    
    /**
     * Devuelve el total de asistencias del estudiante especificado por su id.
     * @param IdEstudiante
     * @return 
     */
    public int getAsistenciasEstudiante(int IdEstudiante){
        int asistencia = 0;
        for (int i = 0; i < this.ClasesCurso.size(); i++) {
            asistencia += this.ClasesCurso.get(i).getAsistenciaEstudiante(IdEstudiante);
        }
        return asistencia;
    }
    
    /**
     * Devuelve el total de inasistencias del estudiante especificado por su id.
     * @param IdEstudiante
     * @return 
     */
    public int getInasistenciasEstudiante(int IdEstudiante){
        return this.ClasesCurso.size() - getAsistenciasEstudiante(IdEstudiante);
    }
    
    //  Evaluaciones
    public void addEvaluacionCurso(Evaluacion EvaluacionCurso){
        this.EvaluacionesCurso.add(EvaluacionCurso);
        if (EvaluacionCurso.getCursoEvaluacion()==null || !EvaluacionCurso.getCursoEvaluacion().equals(this)) {
            EvaluacionCurso.setCursoEvaluacion(this);
        }
    }
    
    public void removeEvaluacionCurso(Evaluacion EvaluacionCurso){
        this.EvaluacionesCurso.remove(EvaluacionCurso);
        if(EvaluacionCurso.getCursoEvaluacion() != null && EvaluacionCurso.getCursoEvaluacion().equals(this)){
            EvaluacionCurso.setCursoEvaluacion(null);
        }
    }
  
}
