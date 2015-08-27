
package Curso;

import Asignatura.Asignatura;
import Docente.Docente;
import Estudiante.Estudiante;
import java.io.Serializable;
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
    
    @OneToOne
    private Docente DocenteCurso;
    
    @OneToOne
    private Asignatura AsignaturaCurso;
    
    @OneToMany
    private List<Estudiante> EstudiantesCurso;

    public Curso(int SemestreCurso, int AnioCurso, Docente DocenteCurso, Asignatura AsignaturaCurso) {
        this.SemestreCurso = SemestreCurso;
        this.AnioCurso = AnioCurso;
        this.DocenteCurso = DocenteCurso;
        this.AsignaturaCurso = AsignaturaCurso;
    }

    public Curso() {}

    public int getIdCurso() {return IdCurso;}

    public void setIdCurso(int IdCurso) {this.IdCurso = IdCurso;}

    public int getSemestreCurso() {return SemestreCurso;}

    public void setSemestreCurso(int SemestreCurso) {this.SemestreCurso = SemestreCurso;}

    public int getAnioCurso() {return AnioCurso;}

    public void setAnioCurso(int AnioCurso) {this.AnioCurso = AnioCurso;}

    public Docente getDocenteCurso() {return DocenteCurso;}

    public void setDocenteCurso(Docente DocenteCurso) {this.DocenteCurso = DocenteCurso;}

    public Asignatura getAsignaturaCurso() {return AsignaturaCurso;}

    public void setAsignaturaCurso(Asignatura AsignaturaCurso) {this.AsignaturaCurso = AsignaturaCurso;}
    
    public void addEstudianteCurso(Estudiante EstudianteCurso){this.EstudiantesCurso.add(EstudianteCurso);}
    
    public void removeEstudianteCurso(Estudiante EstudianteCurso){this.EstudiantesCurso.remove(EstudianteCurso);}
    
    public void setEstudiantesCurso(List<Estudiante> EstudiantesCurso){this.EstudiantesCurso = EstudiantesCurso;}
    
    public List<Estudiante> getEstudiantesCurso(){return this.EstudiantesCurso;}
}
