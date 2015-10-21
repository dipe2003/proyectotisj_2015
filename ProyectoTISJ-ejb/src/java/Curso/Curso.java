
package Curso;

import Asignatura.Asignatura;
import Curso.Clase.Clase;
import Docente.Docente;
import Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @OneToMany
    private List<Clase> ClasesCurso;
    
    //  Constructores
    
    public Curso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso, String ContratoDocenteCurso) {
        this.SemestreCurso = SemestreCurso;
        this.AnioCurso = AnioCurso;
        this.DocenteCurso = DocenteCurso;
        this.AsignaturaCurso = AsignaturaCurso;
        this.EstudiantesCurso = new ArrayList<>();
        this.ClasesCurso = new ArrayList<>();
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
    
    //  Setters
    public void setIdCurso(int IdCurso) {this.IdCurso = IdCurso;}
    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setDocenteCurso(Docente DocenteCurso) {this.DocenteCurso = DocenteCurso;}
    public void setAsignaturaCurso(Asignatura AsignaturaCurso) {this.AsignaturaCurso = AsignaturaCurso;}
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setEstudiantesCurso(List<Estudiante> EstudiantesCurso){this.EstudiantesCurso = EstudiantesCurso;}
    public void setClasesCurso(List<Clase> ClasesCurso) {this.ClasesCurso = ClasesCurso;}
    
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
        if (!ClaseCurso.getCursoClase().equals(this)) {
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
}
