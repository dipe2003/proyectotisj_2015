
package Curso;

import Asignatura.Asignatura;
import Docente.Docente;
import Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @OneToMany
    private List<Estudiante> EstudiantesCurso;
    
    //  Constructores
    
    public Curso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso, String ContratoDocenteCurso) {
        this.SemestreCurso = SemestreCurso;
        this.AnioCurso = AnioCurso;
        this.DocenteCurso = DocenteCurso;
        this.AsignaturaCurso = AsignaturaCurso;
        this.EstudiantesCurso = new ArrayList<>();
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
    
    //  Setters
    
    public void setIdCurso(int IdCurso) {this.IdCurso = IdCurso;}
    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}
    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}
    public void setDocenteCurso(Docente DocenteCurso) {this.DocenteCurso = DocenteCurso;}
    public void setAsignaturaCurso(Asignatura AsignaturaCurso) {this.AsignaturaCurso = AsignaturaCurso;}
    public void setContratoDocenteCurso(String ContratoDocenteCurso) {this.ContratoDocenteCurso = ContratoDocenteCurso;}
    public void setEstudiantesCurso(List<Estudiante> EstudiantesCurso){this.EstudiantesCurso = EstudiantesCurso;}
    
    //  Listas
    
    public void addEstudianteCurso(Estudiante EstudianteCurso){this.EstudiantesCurso.add(EstudianteCurso);}
    public void removeEstudianteCurso(Estudiante EstudianteCurso){this.EstudiantesCurso.remove(EstudianteCurso);}
    
    
}
