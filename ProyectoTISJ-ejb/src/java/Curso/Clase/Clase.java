
package Curso.Clase;

import Curso.Curso;
import Estudiante.Estudiante;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Clase implements Serializable{
    @Id@GeneratedValue(strategy = GenerationType.AUTO)
    private int IdClase;
    @Column(unique = true)@Temporal(TemporalType.DATE)
    private Date FechaClase;
   @ManyToMany(mappedBy="ClasesEstudiante")
    private List<Estudiante> EstudiantesClase;
    @ManyToOne
    private Curso CursoClase;
    private String TemaClase;
    
    //  Constructores
    public Clase() {}
    public Clase(Date FechaClase, List<Estudiante> AsistenciasClase, String TemaClase, Curso CursoClase) {
        this.FechaClase = FechaClase;
        this.EstudiantesClase = AsistenciasClase;
        this.TemaClase = TemaClase;
        this.CursoClase = CursoClase;
    }
    public Clase(Date FechaClase, String TemaClase, Curso CursoClase) {
        this.FechaClase = FechaClase;
        this.EstudiantesClase = new ArrayList<>();
        this.TemaClase = TemaClase;
        this.CursoClase = CursoClase;
        this.EstudiantesClase = new ArrayList<>();
    }
    
    //  Getters
    public int getIdClase() {return IdClase;}
    public Date getFechaClase() {return FechaClase;}
    public List<Estudiante> getEstudiantesClase() {return EstudiantesClase;}
    public Curso getCursoClase() {return CursoClase;}
    public String getTemaClase() {return TemaClase;}
    
    //  Setters
    public void setIdClase(int IdClase) {this.IdClase = IdClase;}
    public void setFechaClase(Date FechaClase) {this.FechaClase = FechaClase;}
    public void setEstudiantesClase(List<Estudiante> EstudiantesClase) {this.EstudiantesClase = EstudiantesClase;}
    public void setCursoClase(Curso CursoClase) {
        this.CursoClase = CursoClase;
        if (!CursoClase.getClasesCurso().contains(this)) {
            CursoClase.getClasesCurso().add(this);
        }
    }
    public void setTemaClase(String TemaClase) {this.TemaClase = TemaClase;}
    
    //  Asistencias
    public void addEstudianteClase(Estudiante EstudianteClase){
        this.EstudiantesClase.add(EstudianteClase);
        if (!EstudianteClase.getClasesEstudiante().contains(this)) {
            EstudianteClase.getClasesEstudiante().add(this);
        }
    }
    public void removeEstudianteClase(Estudiante EstudianteClase){
        this.EstudiantesClase.remove(EstudianteClase);
    }
    public void removeEstudianteClase(int IdEstudianteClase){
        for (int i = 0; i < this.EstudiantesClase.size(); i++) {
            if (this.EstudiantesClase.get(i).getIdUsuario()==IdEstudianteClase) this.EstudiantesClase.remove(i);
        }
    }
    
}
