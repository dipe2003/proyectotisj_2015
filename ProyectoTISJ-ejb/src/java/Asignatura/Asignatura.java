
package Asignatura;

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
public class Asignatura implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int IdAsignatura;
    private String NombreAsignatura;
    private int SemestreAsignatura;
    private int CreditosAsignatura;
    private int AnioAsignatura;
    
    @OneToOne
    private Docente DocenteAsignatura;
    
    @OneToMany
    private List<Estudiante> EstudiantesAsignatura;
    
    public Asignatura() {
    }

    public Asignatura(String NombreAsignatura, int SemestreAsignatura, int CreditosAsignatura, 
            int AnioAsignatura) {
        this.NombreAsignatura = NombreAsignatura;
        this.SemestreAsignatura = SemestreAsignatura;
        this.CreditosAsignatura = CreditosAsignatura;
        this.AnioAsignatura = AnioAsignatura;;
    }

    public int getIdAsignatura() {
        return IdAsignatura;
    }

    public void setIdAsignatura(int IdAsignatura) {this.IdAsignatura = IdAsignatura;}

    public String getNombreAsignatura() {return NombreAsignatura;}

    public void setNombreAsignatura(String NombreAsignatura) {this.NombreAsignatura = NombreAsignatura;}

    public int getSemestreAsignatura() {return SemestreAsignatura;}

    public void setSemestreAsignatura(int SemestreAsignatura) {this.SemestreAsignatura = SemestreAsignatura;}

    public int getCreditosAsignatura() {return CreditosAsignatura;}

    public void setCreditosAsignatura(int CreditosAsignatura) {this.CreditosAsignatura = CreditosAsignatura;}

    public int getAnioAsignatura() {return AnioAsignatura;}

    public void setAnioAsignatura(int AnioAsignatura) {this.AnioAsignatura = AnioAsignatura;}

    public Docente getDocenteAsignatura() {return DocenteAsignatura;}

    public void setDocenteAsignatura(Docente DocenteAsignatura) {this.DocenteAsignatura = DocenteAsignatura;}

    public List<Estudiante> getEstudiantesAsignatura() {return EstudiantesAsignatura;}

    public void setEstudiantesAsignatura(List<Estudiante> EstudiantesAsignatura) {this.EstudiantesAsignatura = EstudiantesAsignatura;}
    
    public void addEstudianteAsignatura(Estudiante estudiante){EstudiantesAsignatura.add(estudiante);}
    
    public void removeEstudianteAsignatura(Estudiante estudiante){EstudiantesAsignatura.remove(estudiante);}
    
}
