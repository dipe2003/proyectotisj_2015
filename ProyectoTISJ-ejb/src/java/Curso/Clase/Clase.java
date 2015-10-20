
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
    @OneToMany
    private List<Estudiante> AsistenciasClase;
    @ManyToOne
    private Curso CursoClase;
    private String TemaClase;
    
    //  Constructores
    public Clase() {}    
    public Clase(Date FechaClase, List<Estudiante> AsistenciasClase, String TemaClase) {
        this.FechaClase = FechaClase;
        this.AsistenciasClase = AsistenciasClase;
        this.TemaClase = TemaClase;
    }
    public Clase(Date FechaClase, String TemaClase) {
        this.FechaClase = FechaClase;
        this.AsistenciasClase = new ArrayList<>();
        this.TemaClase = TemaClase;
    }
    
    //  Getters
    public int getIdClase() {return IdClase;}
    public Date getFechaClase() {return FechaClase;}
    public List<Estudiante> getAsistenciasClase() {return AsistenciasClase;}
    public Curso getCursoClase() {return CursoClase;}
    public String getTemaClase() {return TemaClase;}
    
    //  Setters
    public void setIdClase(int IdClase) {this.IdClase = IdClase;}
    public void setFechaClase(Date FechaClase) {this.FechaClase = FechaClase;}
    public void setAsistenciasClase(List<Estudiante> AsistenciasClase) {this.AsistenciasClase = AsistenciasClase;}
    public void setCursoClase(Curso CursoClase) {
        this.CursoClase = CursoClase;
        if (!CursoClase.getAsistenciasCurso().contains(this)) {
            CursoClase.getAsistenciasCurso().add(this);
        }
    }
    public void setTemaClase(String TemaClase) {this.TemaClase = TemaClase;}
    
    //  Asistencias
    public void addEstudianteClase(Estudiante EstudianteClase){
        this.AsistenciasClase.add(EstudianteClase);
    }
    public void removeEstudianteClase(Estudiante EstudianteClase){
        this.AsistenciasClase.remove(EstudianteClase);
    }
    public void removeEstudianteClase(int IdEstudianteClase){
        for (int i = 0; i < this.AsistenciasClase.size(); i++) {
            if (this.AsistenciasClase.get(i).getIdUsuario()==IdEstudianteClase) this.AsistenciasClase.remove(i);
        }
    }
    
}
